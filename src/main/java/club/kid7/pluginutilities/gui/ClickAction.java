package club.kid7.pluginutilities.gui;

import org.bukkit.event.inventory.ClickType;

public record ClickAction(ClickType clickType, ActionHandler actionHandler) {
}
