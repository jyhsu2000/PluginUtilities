# CustomGUI

Custom GUI inventory menu

## Setup
Add statements in `onEnable` and `onDisable`
```java
public class MyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        CustomGUI.enable();
    }
    @Override
    public void onDisable() {
        CustomGUI.disable();
    }
}
```

## Define menu
Create your class `implements CustomGUIMenu`,
and create `CustomGUIInventory` in `build` function.
```java
public class MainMenu implements CustomGUIMenu {
    @Override
    public CustomGUIInventory build(final Player player) {
        //Create CustomGUIInventory instance
        CustomGUIInventory menu = new CustomGUIInventory(title, 27);
        
        //Add some clickable items
        KItemStack appleItemStack = new KItemStack(Material.APPLE).amount(32).name("Take some apples");
        menu.setClickableItem(5, appleItemStack).set(ClickType.LEFT, new CustomGUIItemHandler() {
            @Override
            public void action(InventoryClickEvent event) {
                //Do somethings when Left-Click
                player.getInventory().addItem(new ItemStack(Material.APPLE, 5));
            }
        }).set(ClickType.RIGHT, new CustomGUIItemHandler() {
            @Override
            public void action(InventoryClickEvent event) {
                //Jump to another menu when Right-Click
                CustomGUIManager.open(player, AnotherMenu.class);
            }
        });
        
        //... or normal item
        KItemStack stoneItemStack = new KItemStack(Material.STONE).amount(1).name("A normal stone");
        menu.setItem(23, stoneItemStack);
        
        //Finally, return instance of CustomGUIInventory
        return menu;
    }
}
public class AnotherMenu implements CustomGUIMenu {
    @Override
    public CustomGUIInventory build(final Player player) {
        CustomGUIInventory menu = new CustomGUIInventory(title);
        // ... (omitted) ...
        return menu;
    }
}
```

## Usage
```java
//Open gui menu defined in MainMenu class
CustomGUIManager.open(player, MainMenu.class);

//Reopen last opened gui menu by player
CustomGUIManager.openPrevious(player);

//Reopen last opened gui menu by player. If it's first time to open gui menu, open MainMenu instead.
CustomGUIManager.openPrevious(player, MainMenu.class);
```
