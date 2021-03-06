package club.kid7.pluginutilities.gui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomGUI {
    /**
     * 插件實例
     */
    private static final Plugin plugin = JavaPlugin.getProvidingPlugin(CustomGUI.class);
    /**
     * 啟用旗標
     */
    private static boolean enabled = false;

    /**
     * 建構子
     */
    private CustomGUI() {
    }

    /**
     * 啟用流程
     */
    public static void enable() {
        //避免重複啟用
        if (enabled) {
            return;
        }
        //監聽事件
        Bukkit.getPluginManager().registerEvents(new CustomGUIInventoryListener(), plugin);
        enabled = true;
    }

    /**
     * 禁用流程
     */
    public static void disable() {
        enabled = false;
        //關閉所有自定義選單
        CustomGUIInventory.closeAll();
    }

    /**
     * 此機制是否已經啟用
     *
     * @return 是否已經啟用
     */
    static boolean isEnabled() {
        return enabled;
    }
}
