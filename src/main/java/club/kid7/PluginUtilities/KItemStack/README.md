# KItemStack

A chainable ItemStack for ItemStacks in Bukkit

## Usage example

```java
ItemStack is = new KItemStack(Material.LEATHER_HELMET)
    .amount(2)
    .data(4)
    .durability(4)
    .enchantment(Enchantment.ARROW_INFINITE)
    .enchantment(Enchantment.LUCK, 2)
    .name(ChatColor.RED + "the name")
    .lore(ChatColor.GREEN + "line 1")
    .lore(ChatColor.BLUE + "line 2")
    .color(Color.MAROON);
```
