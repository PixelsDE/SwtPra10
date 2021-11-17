/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 15.11.21, 16:08 by Carina
 * Latest changes made by Carina on 15.11.21, 16:07
 * All contents of "ClientPacketHandler" are protected by copyright.
 * The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */
package de.thundergames.gameplay.player.networking;

import de.thundergames.networking.server.PacketHandler;
import de.thundergames.networking.util.Packet;
import de.thundergames.networking.util.PacketNotExistsException;
import de.thundergames.networking.util.Packets;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

public class ClientPacketHandler {

  /**
   * @param client the client instance that will be passed to the method for handling
   * @param packet the packet that got send by the server
   * @author Carina
   * @use handles the packet that came in
   * @see PacketHandler the packetHandler by the Server as a reference
   */
  public void handlePacket(@NotNull final Client client, @NotNull final Packet packet)
      throws PacketNotExistsException {
    if (packet.getPacketType().equalsIgnoreCase(Packets.LOGIN.getPacketType())) {
      // ID : 3
      var id = packet.getValues().getInt("id");
      client.setId(id);
      System.out.println("Client ID: " + id);
      client.getClientThread().setID(id);
    } else if (packet.getPacketType().equalsIgnoreCase(Packets.JOINGAME.getPacketType())) {
      System.out.println("Client joined game with id: " + packet.getValues().getInt("gameID"));
      client.setGameID(packet.getValues().getInt("gameID"));
    } else if (packet.getPacketType().equalsIgnoreCase(Packets.MESSAGE.getPacketType())) {
      try {
        System.out.println("Server sended: " + packet.getValues().getString("message"));
      } catch (JSONException e) {
        System.out.println("Server sended: " + "no packet content!");
      }
    } else if (packet.getPacketType().equals(Packets.INVALIDMOVE.getPacketType())) {
      System.out.println("Server: Youve done an invalid move");
    } else if (packet.getPacketType().equals(Packets.PLACEMOLE.getPacketType())) {
    } else if (packet.getPacketType().equals(Packets.MOVEMOLE.getPacketType())) {
    } else if (packet.getPacketType().equals(Packets.MOLES.getPacketType())) {
      for (int i = 0; i < packet.getValues().getJSONArray("moles").toList().size(); i++) {
        client.getMoleIDs().add(packet.getValues().getJSONArray("moles").getInt(i));
      }
    } else if (packet.getPacketType().equals(Packets.NAME.getPacketType())) {
      System.out.println("test");
      client.setName(packet.getValues().getString("name"));
    } else if (packet.getPacketType().equals(Packets.NEXTPLAYER.getPacketType())) {
      System.out.println("Server sended: You are now on the turn!");
    } else if (packet.getPacketType().equals(Packets.TURNOVER.getPacketType())) {
    } else if (packet.getPacketType().equals(Packets.DRAWNCARD.getPacketType())) {
      System.out.println("Server sended: Your card value is: " + packet.getValues().getInt("card"));
    } else if (packet.getPacketType().equals(Packets.NOTEXISTS.getPacketType())) {
      System.out.println("The game you wanted to join does not exist!");
    } else {
      throw new PacketNotExistsException(
          "Packet with type: " + packet.getJsonPacket().toString() + " does not exists");
    }
  }
}
