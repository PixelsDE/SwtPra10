/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 20.01.22, 22:29 by Carina Latest changes made by Carina on 20.01.22, 22:25 All contents of "GameUtil" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.playmechanics.game;

import de.thundergames.MoleGames;
import de.thundergames.networking.server.ServerThread;
import de.thundergames.playmechanics.util.Player;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
public class GameUtil {

  private final Game game;

  /**
   * @return
   * @author Carina
   * @use checks if all holes are filled with moles
   */
  public synchronized boolean allHolesFilled() {
    for (var hole : game.getMap().getHoles()) {
      if (!game.getMap().getFieldMap().get(List.of(hole.getX(), hole.getY())).isOccupied()) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return
   * @author Carina
   * @use checks if all moles of a player are in a hole
   */
  public synchronized boolean allPlayerMolesInHoles(@NotNull final Player player) {
    if (player.getMoles().isEmpty()) {
      return false;
    }
    for (var moles : player.getMoles()) {
      if (!game.getMap()
        .getFieldMap()
        .get(List.of(moles.getPosition().getX(), moles.getPosition().getY()))
        .isHole()) {
        return false;
      }
    }
    return true;
  }

  /**
   * @author Carina
   * @use sets the next player in the game if all moles are in holes the player is not on turn
   */
  public synchronized void nextPlayer() throws InterruptedException {
    if (game.getActivePlayers().isEmpty()) {
      game.forceGameEnd();
    }
    if (game.getCurrentGameState() == GameStates.OVER
      || game.getCurrentGameState() == GameStates.PAUSED) {
      return;
    }
    // setting the new current player and if current can draw again or not
    if (game.getActivePlayers().size() - 1
      >= game.getActivePlayers().indexOf(game.getCurrentPlayer()) + 1) {
      if (game.getCurrentPlayer() != null) {
        if (!game.getCurrentPlayer().isDrawAgain()) {
          game.setCurrentPlayer(
            game.getActivePlayers()
              .get(game.getActivePlayers().indexOf(game.getCurrentPlayer()) + 1));
        } else {
          if (game.getActivePlayers().contains(game.getCurrentPlayer())) {
            game.getCurrentPlayer().setDrawAgain(false);
            System.out.println(
              "Server: Player with the name: "
                + game.getCurrentPlayer().getName()
                + " can draw again!");
          } else {
            nextPlayer();
            return;
          }
        }
      } else {
        game.setCurrentPlayer(
          game.getActivePlayers()
            .get(game.getActivePlayers().indexOf(game.getCurrentPlayer()) + 1));
      }
    } else if (!game.getActivePlayers().isEmpty()) {
      game.setCurrentPlayer(
        game.getClientPlayersMap()
          .get((ServerThread) game.getActivePlayers().get(0).getServerClient()));
    }
    game.getGameState().setCurrentPlayer(game.getCurrentPlayer());
    if (!game.getActivePlayers().contains(game.getCurrentPlayer())) {
      nextPlayer();
      return;
    }
    // selection of the new next player is done
    if (allHolesFilled()) {
      if (MoleGames.getMoleGames().getServer().isDebug()) {
        System.out.println(
          "Server: All holes are filled going to next Floor or check the winning!");
      }
      Thread.sleep(game.getSettings().getVisualizationTime());
      nextFloor();
    } else if (allMolesOfAllPlayersInHoles()) {
      if (MoleGames.getMoleGames().getServer().isDebug()) {
        System.out.println("Server: All moles of all players are in holes!");
      }
      if (game.getActivePlayers().size() >= 1) {
        Thread.sleep(game.getSettings().getVisualizationTime());
        nextFloor();
      } else {
        Thread.sleep(game.getSettings().getVisualizationTime());
        game.endGame();
      }
    } else if (allPlayerMolesInHoles(game.getCurrentPlayer())) {
      if (MoleGames.getMoleGames().getServer().isDebug()) {
        System.out.println(
          "Server: All player moles are in holes! for player: " + game.getCurrentPlayer().getName());
      }
      MoleGames.getMoleGames()
        .getServer()
        .sendToAllGameClients(
          game,
          MoleGames.getMoleGames()
            .getServer()
            .getPacketHandler()
            .playerSkippedPacket(game.getCurrentPlayer()));
      if (game.getActivePlayers().size() == 1) {
        Thread.sleep(game.getSettings().getVisualizationTime());
        nextFloor();
      } else if (game.getActivePlayers().size() > 1) {
        nextPlayer();
      } else {
        Thread.sleep(game.getSettings().getVisualizationTime());
        game.endGame();
      }
    } else {
      if (game.getCurrentPlayer().getMoles().size() < game.getSettings().getNumberOfMoles()
        && game.getGameState().getCurrentFloorID() == 0) {
        Thread.sleep(game.getSettings().getVisualizationTime());
        MoleGames.getMoleGames()
          .getServer()
          .sendToAllGameClients(
            game,
            MoleGames.getMoleGames()
              .getServer()
              .getPacketHandler()
              .playerPlacesMolePacket(game.getCurrentPlayer()));
      } else {
        if (MoleGames.getMoleGames().getGameHandler().getGameLogic().isPlayerMovePossible(game.getCurrentPlayer())) {
          Thread.sleep(game.getSettings().getVisualizationTime());
          MoleGames.getMoleGames()
            .getServer()
            .sendToAllGameClients(
              game,
              MoleGames.getMoleGames()
                .getServer()
                .getPacketHandler()
                .playersTurnPacket(
                  (ServerThread) game.getCurrentPlayer().getServerClient(), false));
        } else {
          if (game.getActivePlayers().size() > 1) {
            nextPlayer();
          } else {
            Thread.sleep(game.getSettings().getVisualizationTime());
            game.endGame();
          }
          return;
        }
      }
      game.getCurrentPlayer().getPlayerUtil().startThinkTimer();
    }
  }

  private boolean allMolesOfAllPlayersInHoles() {
    for (var player : game.getActivePlayers()) {
      if (!allPlayerMolesInHoles(player)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @author Carina
   * @use goes to the next Floor it is exists bekommt
   */
  public synchronized void nextFloor() {
    if (game.getSettings().getFloors().size() > game.getGameState().getCurrentFloorID() + 1) {
      ArrayList<Player> eliminated = eliminatedPlayerHandling();
      game.getGameUtil()
        .givePoints(
          false); // Giving the points to the players who are in the next level or just won
      game.getGameState().setCurrentFloorID(game.getGameState().getCurrentFloorID() + 1);
      game.updateGameState();
      MoleGames.getMoleGames()
        .getServer()
        .sendToAllGameClients(
          game,
          MoleGames.getMoleGames()
            .getServer()
            .getPacketHandler()
            .nextFloorPacket(game.getGameState(), eliminated));
      try {
        nextPlayer();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      eliminatedPlayerHandling();
      game.getGameUtil()
        .givePoints(
          true); // Giving the points to the players who are in the next level or just won
      game.endGame();
      game.setCurrentGameState(GameStates.OVER);
    }
  }

  private ArrayList<Player> eliminatedPlayerHandling() {
    var eliminated = new ArrayList<>(game.getActivePlayers());
    for (var player : game.getActivePlayers()) {
      for (var mole : player.getMoles()) {
        if (game.getMap()
          .getFieldMap()
          .get(List.of(mole.getPosition().getX(), mole.getPosition().getY()))
          .isHole()) {
          eliminated.remove(player);
          if (MoleGames.getMoleGames().getServer().isDebug()) {
            System.out.println(
              "Server: player with id "
                + player.getServerClient().getThreadID()
                + " is in next level!");
          }
          break;
        }
      }
    }
    for (var player : eliminated) {
      if (MoleGames.getMoleGames().getServer().isDebug()) {
        System.out.println("Player: " + player + " is eliminated!");
      }
      game.removePlayerFromGame(player);
    }
    game.getEliminatedPlayers().addAll(eliminated);
    for (var player : game.getActivePlayers()) {
      for (var mole : new HashSet<>(player.getMoles())) {
        if (!game.getMap()
          .getFieldMap()
          .get(List.of(mole.getPosition().getX(), mole.getPosition().getY()))
          .isHole()) {
          player.getMoles().remove(mole);
        }
      }
    }
    return eliminated;
  }

  /**
   * @author Carina
   * @use gives points to the player who are in holes when a next floor comes
   * @sse PlayerModel
   * @see de.thundergames.filehandling.Score
   */
  public void givePoints(final boolean lastFloor) {
    for (var player : game.getActivePlayers()) {
      if (!player.getMoles().isEmpty()) {
        game.getScore()
          .getPoints()
          .put(
            player.getClientID(),
            game.getScore().getPoints().get(player.getClientID())
              + game.getMap().getPoints() * (lastFloor ? 1 : player.getMoles().size()));
        if (MoleGames.getMoleGames().getServer().isDebug()) {
          System.out.println(
            "the player with the name: "
              + player.getName()
              + " got: "
              + game.getMap().getPoints() * (lastFloor ? 1 : player.getMoles().size())
              + " points and now has "
              + game.getScore().getPoints().get(player.getServerClient().getThreadID())
              + " points!");
        }
      }
    }
  }
}
