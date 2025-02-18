package net.flaim.skillsPlugin;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class TreeType {
    private final String name;
    private final Material logType;

    public TreeType(String name, Material logType) {
        this.name = name;
        this.logType = logType;
    }

    public String getName() {
        return name;
    }

    public Material getLogType() {
        return logType;
    }

    public static List<TreeType> getAllTreeTypes() {
        List<TreeType> treeTypes = new ArrayList<>();
        treeTypes.add(new TreeType("Oak", Material.OAK_LOG));
        treeTypes.add(new TreeType("Spruce", Material.SPRUCE_LOG));
        treeTypes.add(new TreeType("Birch", Material.BIRCH_LOG));
        treeTypes.add(new TreeType("Jungle", Material.JUNGLE_LOG));
        treeTypes.add(new TreeType("Acacia", Material.ACACIA_LOG));
        treeTypes.add(new TreeType("Dark Oak", Material.DARK_OAK_LOG));
        treeTypes.add(new TreeType("Mangrove", Material.MANGROVE_LOG));
        return treeTypes;
    }
}