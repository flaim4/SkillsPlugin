package net.flaim.skillsPlugin;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SimpleCacheBuilder<K, V> {
    private long expirationTime;
    private TimeUnit unit;
    private Function<K, V> loader;
    private BiConsumer<K, V> saver = null;
    private boolean autosave = false;

    public SimpleCacheBuilder setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public SimpleCacheBuilder setUnit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }

    public SimpleCacheBuilder setLoader(Function<K, V> loader) {
        this.loader = loader;
        return this;
    }

    public SimpleCacheBuilder setSaver(BiConsumer<K, V> saver) {
        this.saver = saver;
        this.autosave = true;
        return this;
    }

    public SimpleCache build() {
        return new SimpleCache(expirationTime, unit, loader, saver, autosave);
    }
}