package club.kid7.pluginutilities.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface CustomGUIItemHandler {
    /**
     * 點擊動作
     */
    void action(InventoryClickEvent event);
}
