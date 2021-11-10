package de.thundergames;

import de.thundergames.gameplay.ai.AI;
import de.thundergames.network.client.Client;
import de.thundergames.network.server.Server;
import de.thundergames.network.util.PacketHandler;
import de.thundergames.play.game.GameLogic;
import de.thundergames.play.util.MultiGameHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author Carina
 * @use Main Class of the entire de.thundergames.game
 * @see Server as an instance that will be integrated
 * @see Client as an instance that will be integrated
 * @see AI as an instance that will be integrated
 */
public class MoleGames {

  private static MoleGames moleGames;
  private Server server;
  private MultiGameHandler gameHandler;
  private GameLogic gameLogic;
  private PacketHandler packetHandler;

  /**
   * @author Carina
   * @use MainClass start
   * @use creates a server object or AI object or client object depending on the arguments
   * @see Server
   * @see Client
   * @see AI
   */
  public static void main(@Nullable final String... args) {
    moleGames = new MoleGames();
    if (args.length == 0) {
      Client.ClientMain();
    } else {
      switch (Objects.requireNonNull(args[0])) {
        case "-c":
        case "c":
        case "-p":
        case "p":
          Client.ClientMain();
          break;
        case "-s":
        case "s":
          moleGames.server = new Server(5000, "127.0.0.1");
          moleGames.packetHandler = new PacketHandler();
          moleGames.gameHandler = new MultiGameHandler();
          moleGames.gameLogic = new GameLogic();
          moleGames.server.create();
          break;
        case "-a":
        case "a":
          assert args[3] != null;
          new AI(Objects.requireNonNull(args[1]), Integer.parseInt(Objects.requireNonNull(args[2])), Integer.parseInt(Objects.requireNonNull(args[3])));
      }
    }
  }

  public static MoleGames getMoleGames() {
    return moleGames;
  }

  public MultiGameHandler getGameHandler() {
    return gameHandler;
  }

  public Server getServer() {
    return server;
  }

  public PacketHandler getPacketHandler() {
    return packetHandler;
  }

  public GameLogic getGameLogic() {
    return gameLogic;
  }
}
