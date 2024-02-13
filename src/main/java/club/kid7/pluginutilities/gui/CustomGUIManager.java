package club.kid7.pluginutilities.gui;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;

public class CustomGUIManager {
    private static final HashMap<UUID, Class<? extends CustomGUIMenu>> lastOpenedMenuClassMap = Maps.newHashMap();

    /**
     * 建構子
     */
    private CustomGUIManager() {
    }

    /**
     * 對特定玩家開啟前一次開啟的自定義選單（{@link CustomGUIInventory}）
     *
     * @param player 玩家
     */
    public static void openPrevious(Player player) {
        openPrevious(player, null);
    }

    /**
     * 對特定玩家開啟前一次開啟的自定義選單（{@link CustomGUIInventory}）
     *
     * @param player           玩家
     * @param defaultMenuClass 預設選單類別
     */
    public static void openPrevious(Player player, Class<? extends CustomGUIMenu> defaultMenuClass) {
        try {
            Class<? extends CustomGUIMenu> lastOpenedMenuClass = lastOpenedMenuClassMap.get(player.getUniqueId());
            if (lastOpenedMenuClass == null) {
                lastOpenedMenuClass = defaultMenuClass;
            }
            if (lastOpenedMenuClass == null) {
                return;
            }
            CustomGUIMenu menu = lastOpenedMenuClass.getDeclaredConstructor().newInstance();
            CustomGUIInventory inventory = menu.build(player);
            if (inventory == null) {
                return;
            }
            inventory.open(player);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 對特定玩家開啟自定義選單（{@link CustomGUIInventory}）
     *
     * @param player    玩家
     * @param menuClass 選單類別
     */
    public static void open(Player player, Class<? extends CustomGUIMenu> menuClass) {
        try {
            CustomGUIMenu menu = menuClass.getDeclaredConstructor().newInstance();
            lastOpenedMenuClassMap.put(player.getUniqueId(), menuClass);
            CustomGUIInventory inventory = menu.build(player);
            if (inventory == null) {
                return;
            }
            inventory.open(player);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
