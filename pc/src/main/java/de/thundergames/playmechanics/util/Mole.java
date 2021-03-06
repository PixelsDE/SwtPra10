/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 13.01.22, 16:58 by Carina Latest changes made by Carina on 13.01.22, 16:58 All contents of "Mole" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.playmechanics.util;

import de.thundergames.playmechanics.map.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mole {
  private final Player player;
  private Field position;
}
