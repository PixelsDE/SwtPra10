package de.thundergames.network.util;

/**
 * @author Carina
 * @see Packets the packet element that can be send
 */
public enum Packets {

  CREATEGAME(0, "CREATE-GAME"),
  JOINGAME(1, "JOIN-GAME"),
  LOGIN(2, "LOGIN"),
  JOINEDGAME(3, "JOINED-GAME"),
  MESSAGE(4, "MESSAGE"),
  DISCONNECT(5, "DISCONNECT"),
  ERROR(6, "ERROR"),
  FULL(7, "FULL"),
  INGAME(8, "INGAME"),
  NOTEXISTS(9, "NOT-EXISTS"),
  CARDS(10, "CARDS"),
  MOVEMOLE(11, "MOVE-MOLE"),
  DRAWAGAIN(12, "DRAW-AGAIN"),
  GAMEOVER(13, "GAME-OVER"),
  WINS(14, "WINS"),
  PLACEMOLE(15, "PLACE-MOLE"),
  TIMETOTHINK(16, "TIME-TO-THINK"),
  LEAVEGAME(17, "LEAVE-GAME"),
  GAMEOVERVIEW(18, "GET-GAME-OVERVIEW"),
  CREATEMAP(19, "CREATE-MAP"),
  PLAYERJOINED(20, "PLAYER-JOINED"),
  GAMESTART(21, "GAME-START"),
  MOLEPLACED(22, "MOLE-PLACED"),
  PLAYERTURN(23, "PLAYER-TURN"),
  PLAYERSUSPENDS(24, "PLAYER-SUSPENDS"),
  DRAWCARD(25, "DRAW-CARD"),
  DRAWNCARD(31, "DRAWEN-CARD"),
  INVALIDMOVE(26, "INVALID-MOVE"),
  PLAYERKICKED(27, "PLAYER-KICKED"),
  KICKPLAYER(28, "KICK-PLAYER"),
  NEXTFLOOR(29, "NEXT-FLOOR"),
  NEXTPLAYER(30, "NEXT-PLAYER"),
  CONFIGURATION(31, "CONFIGURATION"),
  GAMEID(32, "GAME-ID"),
  OCCUPIED(33, "OCCUPIED"),
  MOLES(34, "MOLES"),
  NAME(35, "NAME"),
  ;
  private final int id;
  private final String packetType;

  Packets(int id, String packetType) {
    this.id = id;
    this.packetType = packetType;
  }

  public int getId() {
    return id;
  }

  public Packets getPacketByType(String packetType) throws PacketNotExistsException {
    for (Packets packet : Packets.values()) {
      if (packet.getPacketType().equals(packetType)) {
        return packet;
      }
    }
    try {
      throw new PacketNotExistsException("No Packet with type " + packetType + " found!");
    } catch (PacketNotExistsException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getPacketType() {
    return packetType;
  }
}