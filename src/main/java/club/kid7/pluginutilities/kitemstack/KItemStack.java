package club.kid7.pluginutilities.kitemstack;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a chainable ItemStack for {@link ItemStack}s in {@link Bukkit}
 * <br>
 * Example Usage:<br>
 * {@code ItemStack is = new KItemStack(Material.LEATHER_HELMET).amount(2).enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.LUCK, 2).name(ChatColor.RED + "the name").lore(ChatColor.GREEN + "line 1").lore(ChatColor.BLUE + "line 2").color(Color.MAROON);}
 *
 * @author MiniDigger, jyhsu(KID)
 * @version 1.0
 */
public class KItemStack extends ItemStack {
    /**
     * Initialize the builder with the given {@link Material}
     *
     * @param material the {@link Material} to start the builder from
     * @since 1.0
     */
    public KItemStack(final Material material) {
        super(material);
    }

    /**
     * Initialize the builder with the given {@link ItemStack}
     *
     * @param is the {@link ItemStack} to start the builder from
     * @since 1.0
     */
    public KItemStack(final ItemStack is) {
        super(is);
    }

    /**
     * Changes the amount of the {@link ItemStack}
     *
     * @param amount the new amount to set
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack amount(final int amount) {
        setAmount(amount);
        return this;
    }

    /**
     * Changes the display name of the {@link ItemStack}
     *
     * @param name the new display name to set
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack name(final String name) {
        final ItemMeta meta = getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        setItemMeta(meta);
        return this;
    }

    /**
     * Adds a new line to the lore of the {@link ItemStack}
     *
     * @param text the new line to add
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack lore(final String text) {
        final ItemMeta meta = getItemMeta();
        assert meta != null;
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(text);
        meta.setLore(lore);
        setItemMeta(meta);
        return this;
    }


    /**
     * Adds an {@link Enchantment} with the given level to the {@link ItemStack}
     *
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack enchantment(final Enchantment enchantment, final int level) {
        addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Adds an {@link Enchantment} with the level 1 to the {@link ItemStack}
     *
     * @param enchantment the enchantment to add
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack enchantment(final Enchantment enchantment) {
        addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    /**
     * Changes the {@link Material} of the {@link ItemStack}
     *
     * @param material the new material to set
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack type(final Material material) {
        setType(material);
        return this;
    }

    /**
     * Clears the lore of the {@link ItemStack}
     *
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack clearLore() {
        final ItemMeta meta = getItemMeta();
        assert meta != null;
        meta.setLore(new ArrayList<>());
        setItemMeta(meta);
        return this;
    }

    /**
     * Clears the list of {@link Enchantment}s of the {@link ItemStack}
     *
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack clearEnchantments() {
        for (final Enchantment e : getEnchantments().keySet()) {
            removeEnchantment(e);
        }
        return this;
    }

    /**
     * Sets the {@link Color} of a part of leather armor
     *
     * @param color the {@link Color} to use
     * @return this builder for chaining
     * @since 1.0
     */
    public KItemStack color(Color color) {
        if (getType() != Material.LEATHER_BOOTS && getType() != Material.LEATHER_CHESTPLATE
            && getType() != Material.LEATHER_HELMET && getType() != Material.LEATHER_LEGGINGS) {
            throw new IllegalArgumentException("color() only applicable for leather armor!");
        }
        LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
        assert meta != null;
        meta.setColor(color);
        setItemMeta(meta);
        return this;
    }

    /**
     * Adds a {@link Pattern} to the {@link ItemStack}
     *
     * @param pattern the pattern to add
     * @return this builder for chaining
     * @since 1.3.1
     */
    public KItemStack pattern(Pattern pattern) {
        if (!getType().name().contains("BANNER")) {
            throw new IllegalArgumentException("pattern() only applicable for banner!");
        }
        final BannerMeta meta = (BannerMeta) getItemMeta();
        assert meta != null;
        meta.addPattern(pattern);
        setItemMeta(meta);
        return this;
    }

    /**
     * Sets {@link Pattern}s to the {@link ItemStack}
     *
     * @param list the pattern list to set
     * @return this builder for chaining
     * @since 1.3.1
     */
    public KItemStack setPatterns(List<Pattern> list) {
        if (!getType().name().contains("BANNER")) {
            throw new IllegalArgumentException("setPatterns() only applicable for banner!");
        }
        final BannerMeta meta = (BannerMeta) getItemMeta();
        assert meta != null;
        meta.setPatterns(list);
        setItemMeta(meta);
        return this;
    }

    /**
     * Clears the list of {@link Pattern}s of the {@link ItemStack}
     *
     * @return this builder for chaining
     * @since 1.3.1
     */
    public KItemStack clearPatterns() {
        if (!getType().name().contains("BANNER")) {
            throw new IllegalArgumentException("clearPatterns() only applicable for banner!");
        }
        final BannerMeta meta = (BannerMeta) getItemMeta();
        assert meta != null;
        meta.setPatterns(new ArrayList<>());
        setItemMeta(meta);
        return this;
    }
}
