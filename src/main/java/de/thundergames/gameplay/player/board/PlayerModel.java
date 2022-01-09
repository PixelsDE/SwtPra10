/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 09.01.22, 16:05 by Carina Latest changes made by Carina on 09.01.22, 16:05 All contents of "PlayerModel" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.gameplay.player.board;

import de.thundergames.playmechanics.util.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PlayerModel {
  private final String playerColor;
  private int ID;
  private ArrayList<MoleModel> moles;
  private MoleModel activeMole;
  private boolean isItMyTurn;
  private Player player;
  private ArrayList<Marker> markers;

  public PlayerModel(Player player, ArrayList<MoleModel> moles, boolean isItMyTurn, String playerColor) {
    this.player = player;
    this.moles = moles;
    this.isItMyTurn = isItMyTurn;
    this.markers = new ArrayList<>();
    this.playerColor = playerColor;
  }

  /**
   * @author Alp, Dila, Issam
   * @use updates the marker
   */
  public void updateMarker() {
    markers.clear();
    for (var mole : this.moles) {
      var marker = new Marker();
      marker.setLayoutX(mole.getLayoutX() + 8);
      marker.setLayoutY(mole.getLayoutY() - 16);
      marker.setOpacity(this.isItMyTurn ? 1 : 0);
      this.markers.add(marker);
    }
  }

  public void setMoles(ArrayList<MoleModel> moles) {
    this.moles = moles;
  }

  public void setItMyTurn(boolean isItMyTurn) {
    this.isItMyTurn = isItMyTurn;
  }
}