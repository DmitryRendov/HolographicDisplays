/*
 * Copyright (C) filoghost and contributors
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package me.filoghost.holographicdisplays.nms.v1_13_R2;

import me.filoghost.holographicdisplays.nms.common.PacketGroup;
import net.minecraft.server.v1_13_R2.Packet;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

abstract class VersionNMSPacket implements PacketGroup {

    @Override
    public void sendTo(Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(getRawPacket());
    }

    abstract Packet<?> getRawPacket();

}
