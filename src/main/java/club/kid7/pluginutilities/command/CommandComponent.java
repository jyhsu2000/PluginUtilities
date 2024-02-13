package club.kid7.pluginutilities.command;

import club.kid7.pluginutilities.command.exception.CommandExecuteException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Flexible nested command system
 *
 * @author jyhsu(KID)
 * @version 1.4.0
 */
public abstract class CommandComponent implements CommandExecutor, TabCompleter {
    private CommandComponent parent = null;
    protected final Plugin plugin;
    //名稱
    private final String name;
    //介紹
    private final String description;
    //權限
    private final String permission;
    //使用方法
    private final String usage;
    //僅能由玩家執行
    private final boolean onlyFromPlayer;

    private final Map<String, CommandComponent> subCommands = Maps.newHashMap();

    public CommandComponent(Plugin plugin, String name, String description, String permission, String usage, boolean onlyFromPlayer) {
        this.plugin = plugin;
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.usage = usage;
        this.onlyFromPlayer = onlyFromPlayer;
    }

    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //有參數
        if (args.length > 0) {
            //試著找出子指令
            CommandComponent subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                //若有子指令，執行子指令
                return subCommand.onCommand(sender, command, label + " " + args[0], Arrays.copyOfRange(args, 1, args.length));
            }
        }
        //無參數或無該子指令，執行本身
        //檢查權限
        if (!hasPermission(sender)) {
            sender.sendMessage(ChatColor.RED + "Lacking permission " + permission);
            return true;
        }
        //限玩家執行
        if (onlyFromPlayer && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players in game");
            return true;
        }
        //執行指令
        try {
            executeCommand(sender, command, label, args);
        } catch (CommandExecuteException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        return true;
    }

    public final List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length > 1) {
            //試著找出子指令
            CommandComponent subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                //若有子指令，交給子指令處理
                return subCommand.onTabComplete(sender, command, label + " " + args[0], Arrays.copyOfRange(args, 1, args.length));
            }
        }
        //本身的建議
        List<String> suggestions = getSuggestions(sender, command, label, args);
        if (args.length == 1) {
            //根據權限產生子指令清單
            for (Map.Entry<String, CommandComponent> subCommandEntry : subCommands.entrySet()) {
                if (subCommandEntry.getValue().hasPermission(sender)) {
                    suggestions.add(subCommandEntry.getKey());
                }
            }
        }
        //取得部分指令
        String partialCommand = args[args.length - 1];
        //匹配部分指令
        StringUtil.copyPartialMatches(partialCommand, suggestions, completions);

        return completions;
    }

    /**
     * 註冊子指令
     *
     * @param label            標籤
     * @param commandComponent 指令實體
     */
    public final void registerSubCommand(String label, CommandComponent commandComponent) {
        Preconditions.checkArgument(!label.isEmpty(), "Label cannot be empty");
        subCommands.put(label.toLowerCase(), commandComponent);
        commandComponent.setParent(this);
    }

    public final boolean hasPermission(CommandSender sender) {
        return permission == null || permission.isEmpty() || sender.hasPermission(permission) || sender instanceof ConsoleCommandSender;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final String getUsage() {
        return usage;
    }

    public CommandComponent getParent() {
        return parent;
    }

    public void setParent(CommandComponent parent) {
        this.parent = parent;
    }

    public Map<String, CommandComponent> getSubCommands() {
        return subCommands;
    }

    /**
     * 執行指令
     *
     * @param sender  指令發送者
     * @param command 指令
     * @param label   標籤
     * @param args    參數
     * @return 是否執行成功
     */
    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) throws CommandExecuteException {
        //未實作指令，可能只是群組，顯示子指令清單
        //根據權限產生子指令清單
        for (Map.Entry<String, CommandComponent> subCommandEntry : subCommands.entrySet()) {
            if (subCommandEntry.getValue().hasPermission(sender)) {
                CommandComponent subCommand = subCommandEntry.getValue();
                sender.sendMessage(subCommand.getUsage() + ChatColor.GRAY + " - " + subCommand.getDescription());
            }
        }
        return true;
    }

    /**
     * 自動補全建議
     *
     * @param sender  指令發送者
     * @param command 指令
     * @param label   標籤
     * @param args    參數
     * @return 自動補全建議
     */
    public List<String> getSuggestions(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
