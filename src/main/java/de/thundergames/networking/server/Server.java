/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 15.11.21, 14:38 by Carina
 * Latest changes made by Carina on 15.11.21, 14:38
 * All contents of "Server" are protected by copyright.
 * The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */
package de.thundergames.networking.server;

import de.thundergames.MoleGames;
import de.thundergames.gameplay.player.Player;
import de.thundergames.networking.util.Network;
import de.thundergames.networking.util.Packet;
import de.thundergames.play.game.Game;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public class Server extends Network {

  private static final ArrayList<ServerThread> clientThreads = new ArrayList<>();
  private static final HashMap<Integer, ServerThread> threadIds = new HashMap<>();
  private static int threadID = 0;
  private static boolean keyboard = false;
  private final HashMap<String, ServerThread> connectionNames = new HashMap<>();

  public HashMap<String, ServerThread> getConnectionNames() {
    return connectionNames;
  }

  /**
   * @param port obvious the Serverport in case of empty localhost
   * @param ip obvious the ServerIp in case of empty localhost
   * @author Carina
   * @use creates a Server with a @param serverSocket and uses this one to create a ServerThread
   *     which will handle the Inputreading and got info about the Outputsending adds every
   *     ServerThread to a List and adds an Id to it and puts that into a Map
   */
  public Server(final int port, @NotNull final String ip) {
    super(port, ip);
  }

  public static boolean isKeyboard() {
    return keyboard;
  }

  public static void setKeyboard(final boolean keyboard) {
    Server.keyboard = keyboard;
  }

  public static HashMap<Integer, ServerThread> getThreadIds() {
    return threadIds;
  }

  /**
   * @author Carina
   * @use creates the Server starts it and runs the handler for the incomming client-connections
   * @see NetworkThread as the Network for the instance of the ServerThread
   * @see ServerThread as an instance that will be created here
   */
  @Override
  public void create() {
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Server listening on port " + getPort());
      while (true) {
        socket = serverSocket.accept();
        ServerThread serverThread = new ServerThread(socket, threadID);
        serverThread.start();
        MoleGames.getMoleGames().getPacketHandler().loginPacket(serverThread, threadID);
        getClientThreads().add(serverThread);
        threadIds.put(serverThread.getConnectionId(), serverThread);
        threadID++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @param game the de.thundergames.game that all clients are connected to
   * @param packet the packet that should be send
   * @use the method will send a packet to all connected clients of the de.thundergames.game
   */
  // WICHTIG: BEDENKE mach dies immer in einem anderen Thread oder der Mainthread muss sicher frei
  // sein!
  public synchronized void sendToGameClients(
      @NotNull final Game game, @NotNull final Packet packet) {
    try {
      if (!game.getClients().isEmpty()) {
        for (Player client : game.getClients()) {
          client.getServerClient().sendPacket(packet);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<ServerThread> getClientThreads() {
    return clientThreads;
  }
}
