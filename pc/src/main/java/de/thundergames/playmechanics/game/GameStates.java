/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 09.01.22, 19:32 by Carina Latest changes made by Carina on 09.01.22, 19:31 All contents of "GameStates" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */
package de.thundergames.playmechanics.game;

import org.jetbrains.annotations.NotNull;

/**
 * @author Carina
 * @use the GameStats that a de.thundergames.game can have
 * @see Game as the class using the GameStates
 */
public enum GameStates {
  NOT_STARTED("NOT_STARTED"),
  STARTED("STARTED"),
  PAUSED("PAUSED"),
  OVER("OVER"),
  INVALID("INVALID");

  private final String name;

  GameStates(@NotNull final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
