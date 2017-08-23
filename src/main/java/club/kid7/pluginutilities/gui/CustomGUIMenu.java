package club.kid7.pluginutilities.gui;

import org.bukkit.entity.Player;

public interface CustomGUIMenu {
    /**
     * 針對特定玩家定義自定義選單內容
     *
     * @param player 玩家
     * @return 自定義選單
     */
    public CustomGUIInventory build(final Player player);
}
