package de.thundergames.network.server;

import de.thundergames.MoleGames;
import de.thundergames.gameplay.player.Player;
import de.thundergames.network.util.Network;
import de.thundergames.network.util.Packet;
import de.thundergames.play.game.Game;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends Network {

  private static final ArrayList<ServerThread> clientThreads = new ArrayList<>();
  private static final HashMap<Integer, ServerThread> threadIds = new HashMap<>();
  private static int threadID = 0;
  private static boolean keyboard = false;

  public static void setKeyboard(final boolean keyboard) {
    Server.keyboard = keyboard;
  }

  public static boolean isKeyboard() {
    return keyboard;
  }

  /**
   * @param port obvious the Serverport in case of empty localhost
   * @param ip   obvious the ServerIp in case of empty localhost
   * @author Carina
   * @use creates a Server with a @param serverSocket and uses this one to create a ServerThread which will handle the Inputreading and got info about the Outputsending
   * adds every ServerThread to a List and adds an Id to it and puts that into a Map
   */
  public Server(final int port, @NotNull final String ip) {
    super(port, ip);
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

  public static HashMap<Integer, ServerThread> getThreadIds() {
    return threadIds;
  }

  /**
   * @param game   the de.thundergames.game that all clients are connected to
   * @param packet the packet that should be send
   * @use the method will send a packet to all connected clients of the de.thundergames.game
   */
  //WICHTIG: BEDENKE mach dies immer in einem anderen Thread oder der Mainthread muss sicher frei sein!
  public synchronized void sendToGameClients(@NotNull final Game game, @NotNull final Packet packet) {
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
