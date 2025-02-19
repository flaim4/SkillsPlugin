package net.flaim.skillsPlugin;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SimpleCache<K, V> implements Cache<K, V> {
    public final Map<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
    private final long expirationTimeMs;
    private final Timer timer = new Timer(true);
    public final Function<K, V> loader;
    public final BiConsumer<K, V> saver;
    public final boolean autosave;

    public SimpleCache(long expirationTime, TimeUnit unit, Function<K, V> loader, BiConsumer<K, V> saver, boolean autosave) {
        this.expirationTimeMs = unit.toMillis(expirationTime);
        this.loader = loader;
        this.saver = saver;
        this.autosave = autosave;
        startCleanupTask();
    }

    @Override
    public V get(K key) {
        synchronized (cache) {
            CacheEntry<V> entry = cache.get(key);
            if (entry == null || isExpired(entry)) {
                V value = loader.apply(key);
                put(key, value);
                return value;
            }
            return entry.value;
        }
    }

    @Override
    public void put(K key, V value) {
        synchronized (cache) {
            cache.put(key, new CacheEntry<>(value, System.currentTimeMillis()));
        }
    }

    private boolean isExpired(CacheEntry<V> entry) {
        return System.currentTimeMillis() - entry.timestamp >= expirationTimeMs;
    }

    private void startCleanupTask() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (cache) {
                    long now = System.currentTimeMillis();
                    cache.entrySet().removeIf(entry -> {
                        if (now - entry.getValue().timestamp >= expirationTimeMs) {
                            if (autosave) saver.accept(entry.getKey(), entry.getValue().value);
                            return true;
                        }
                        return false;
                    });
                }
            }
        }, expirationTimeMs, expirationTimeMs / 2);
    }

    public static class CacheEntry<V> {
        public final V value;
        public final long timestamp;

        CacheEntry(V value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
