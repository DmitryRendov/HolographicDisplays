/*
 * Copyright (C) filoghost and contributors
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package me.filoghost.holographicdisplays.plugin.commands.subs;

import me.filoghost.fcommons.command.sub.SubCommandContext;
import me.filoghost.fcommons.command.validation.CommandException;
import me.filoghost.holographicdisplays.plugin.Colors;
import me.filoghost.holographicdisplays.plugin.commands.HologramCommandManager;
import me.filoghost.holographicdisplays.plugin.commands.Messages;
import me.filoghost.holographicdisplays.plugin.commands.HologramCommandValidate;
import me.filoghost.holographicdisplays.plugin.object.internal.InternalHologram;
import me.filoghost.holographicdisplays.plugin.object.internal.InternalHologramLine;
import me.filoghost.holographicdisplays.plugin.object.internal.InternalHologramManager;
import org.bukkit.command.CommandSender;

public class InfoCommand extends LineEditingCommand implements QuickEditCommand {

    private final HologramCommandManager commandManager;
    private final InternalHologramManager internalHologramManager;

    public InfoCommand(HologramCommandManager commandManager, InternalHologramManager internalHologramManager) {
        super("info", "details");
        setMinArgs(1);
        setUsageArgs("<hologram>");
        setDescription("Shows the lines of a hologram.");

        this.commandManager = commandManager;
        this.internalHologramManager = internalHologramManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args, SubCommandContext context) throws CommandException {
        InternalHologram hologram = HologramCommandValidate.getInternalHologram(internalHologramManager, args[0]);
        
        sender.sendMessage("");
        Messages.sendTitle(sender, "Lines of the hologram '" + hologram.getName() + "'");
        int index = 0;
        
        for (InternalHologramLine line : hologram.getLines()) {
            index++;
            sender.sendMessage(Colors.SECONDARY + Colors.BOLD + index 
                    + Colors.SECONDARY_SHADOW + ". " + Colors.SECONDARY + line.getSerializedConfigValue());
        }
        commandManager.sendQuickEditCommands(context, hologram);
    }

    @Override
    public String getActionName() {
        return "View";
    }

}