/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 15.11.21, 10:33 by Carina
 * Latest changes made by Carina on 15.11.21, 10:26
 * All contents of "GameLogic" are protected by copyright.
 * The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */
package de.thundergames.play.game;

import de.thundergames.gameplay.player.Player;
import de.thundergames.play.map.Map;
import de.thundergames.play.util.Punishments;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class GameLogic {

  /**
   * @param start the startpoint in form of x and y
   * @param stop the endpoint where the player wants to go in form of x and y
   * @param moveCounter the amounts of fields the player can move
   * @param map of the de.thundergames.game and of the player
   * @return if the move is valid it will return true
   * @author Carina
   * @use add the parameters and it will return if the move was valid with true or invalid with
   *     false
   * @premisse the startpoint and endpoint must be in the playingfield and the player was allowed to
   *     move.
   */
  public synchronized boolean wasLegalMove(
      @NotNull final List<Integer> start,
      @NotNull final List<Integer> stop,
      final int moveCounter,
      @NotNull final Map map) {
    // check if player moved to much
    if (map.getFloor().getFieldMap().containsKey(start)
        && map.getFloor().getFieldMap().containsKey(stop)
        && start != stop) {
      if (stop.get(0) - start.get(0) == 0 && Math.abs(stop.get(1) - start.get(1)) == moveCounter
          || start.get(1) - stop.get(1) == 0 && Math.abs(stop.get(0) - start.get(0)) == moveCounter
          || Math.abs(stop.get(0) - start.get(0)) == Math.abs(stop.get(1) - start.get(1))
              && Math.abs(start.get(1) - stop.get(1)) == moveCounter) {
        for (var field : map.getFloor().getOccupied()) {
          if (stop.get(0) - start.get(0) == 0) {
            for (var i = 1; i < moveCounter; i++) {
              if (stop.get(1) - start.get(1) > 0) {
                if (field.getY() == start.get(1) + i && field.getX() == start.get(0)) {
                  return false;
                }
              } else if (stop.get(1) - start.get(1) < 0) {
                if (field.getY() == start.get(1) - i && field.getX() == start.get(0)) {
                  return false;
                }
              }
            }
          } else if (stop.get(1) - start.get(1) == 0) {
            for (var i = 1; i < moveCounter; i++) {
              if (stop.get(0) - start.get(0) > 0) {
                if (field.getX() == start.get(0) + i && field.getY() == start.get(1)) {
                  return false;
                }
              } else if (stop.get(0) - start.get(0) < 0) {
                if (field.getX() == start.get(0) - i && field.getY() == start.get(1)) {
                  return false;
                }
              }
            }
          } else if (Math.abs(stop.get(0) - start.get(0)) == Math.abs(stop.get(1) - start.get(1))) {
            for (var i = 1; i < moveCounter; i++) {
              if (stop.get(1) - start.get(1) > 0 && stop.get(0) - start.get(0) > 0) {
                if (field.getX() == start.get(0) + i && field.getY() == start.get(1) + i) {
                  return false;
                }
              } else if (stop.get(0) - start.get(0) < 0 && stop.get(1) - start.get(1) > 0) {
                if (field.getX() == start.get(0) - i && field.getY() == start.get(1) + i) {
                  return false;
                }
              } else if (stop.get(0) - start.get(0) > 0 && stop.get(1) - start.get(1) < 0) {
                if (field.getX() == start.get(0) + i && field.getY() == start.get(1) - i) {
                  return false;
                }
              } else if (stop.get(0) - start.get(0) < 0 && stop.get(1) - start.get(1) < 0) {
                if (field.getX() == start.get(0) - i && field.getY() == start.get(1) - i) {
                  return false;
                }
              }
            }
          }
        }
        return true;
      }
    }
    return false;
  }

  public void performPunishment(Player player) {
    if (!player.getGame().getSettings().getPunishment().equals(Punishments.NOTHING)) {}
  }
}
