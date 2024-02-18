package club.kid7.pluginutilities.gui;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

record SlotActionKey(int slot, ClickType clickType) {
}

public class CustomGUIInventory {
    /**
     * 被玩家開啟的自定義選單與物品欄的對應
     */
    final static HashMap<Inventory, CustomGUIInventory> openedCustomGUIInventoryMap = Maps.newHashMap();
    /**
     * 物品欄
     */
    private final Inventory inventory;

    /**
     * 所有位置與點擊類型對應的動作
     */
    private final HashMap<SlotActionKey, ActionHandler> slotActionMap = Maps.newHashMap();

    /**
     * 建構子
     *
     * @param title 選單標題
     */
    public CustomGUIInventory(String title) {
        this(title, 54);
    }

    /**
     * 建構子
     *
     * @param title 選單標題
     * @param size  物品欄尺寸
     */
    public CustomGUIInventory(String title, int size) {
        if (!CustomGUI.isEnabled()) {
            throw new RuntimeException("CustomGUI is not enabled.");
        }
        inventory = Bukkit.createInventory(null, size, title);
    }

    /**
     * 開啟自定義選單
     *
     * @param humanEntity 玩家
     */
    public void open(HumanEntity humanEntity) {
        openedCustomGUIInventoryMap.put(inventory, this);
        humanEntity.openInventory(inventory);
    }

    /**
     * 設定物品
     *
     * @param slot         位置
     * @param itemStack    顯示用物品
     * @param clickActions 點擊的動作
     */
    public void setItem(int slot, ItemStack itemStack, ClickAction... clickActions) {
        inventory.setItem(slot, itemStack);
        for (ClickAction clickAction : clickActions) {
            SlotActionKey slotActionKey = new SlotActionKey(slot, clickAction.clickType());
            ActionHandler actionHandler = clickAction.actionHandler();
            slotActionMap.put(slotActionKey, actionHandler);
        }
    }

    /**
     * 執行在特定位置使用特定點擊類型的動作
     *
     * @param slot      位置
     * @param clickType 點擊類型
     */
    void action(InventoryClickEvent event, int slot, ClickType clickType) {
        SlotActionKey slotActionKey = new SlotActionKey(slot, clickType);
        ActionHandler actionHandler = slotActionMap.get(slotActionKey);
        if (actionHandler == null) {
            return;
        }
        actionHandler.action(event);
    }

    /**
     * 關閉所有自定義選單
     */
    static void closeAll() {
        //找出所有開啟選單的玩家
        Set<HumanEntity> humanEntities = new HashSet<>();
        for (Inventory inventory : openedCustomGUIInventoryMap.keySet()) {
            humanEntities.addAll(inventory.getViewers());
        }
        //逐一關閉選單
        for (HumanEntity humanEntity : humanEntities) {
            humanEntity.closeInventory();
        }
        //清空清單
        openedCustomGUIInventoryMap.clear();
    }
}
