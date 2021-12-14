/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 14.12.21, 15:41 by Carina Latest changes made by Carina on 14.12.21, 15:41 All contents of "GameState" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.playmechanics.game;

import de.thundergames.filehandling.Score;
import de.thundergames.networking.util.interfaceitems.NetworkFloor;
import de.thundergames.networking.util.interfaceitems.NetworkMole;
import de.thundergames.networking.util.interfaceitems.NetworkPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {

  private ArrayList<NetworkPlayer> activePlayers = new ArrayList<>();
  private NetworkPlayer currentPlayer;
  private int remainingTime;
  private ArrayList<NetworkMole> placedMoles = new ArrayList<>();
  private int moles;
  private int radius;
  private NetworkFloor level;
  private boolean pullDiscsOrdered;
  private HashMap<Integer, ArrayList<Integer>> pullDiscs = new HashMap<>();
  private long visualizationTime = 10000;
  private String status;
  private Score score;

  public void setActivePlayers(ArrayList<NetworkPlayer> activePlayers) {
    this.activePlayers = activePlayers;
  }

  public int getRemainingTime() {
    return remainingTime;
  }

  public void setRemainingTime(int remainingTime) {
    this.remainingTime = remainingTime;
  }

  public void setCurrentPlayer(NetworkPlayer currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public ArrayList<NetworkMole> getPlacedMoles() {
    return placedMoles;
  }

  public void setPlacedMoles(ArrayList<NetworkMole> placedMoles) {
    this.placedMoles = placedMoles;
  }

  public int getMoles() {
    return moles;
  }

  public void setMoles(int moles) {
    this.moles = moles;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public NetworkFloor getFloor() {
    return level;
  }

  public void setFloor(NetworkFloor level) {
    this.level = level;
  }

  public boolean isPullDiscsOrdered() {
    return pullDiscsOrdered;
  }

  public void setPullDiscsOrdered(boolean pullDiscsOrdered) {
    this.pullDiscsOrdered = pullDiscsOrdered;
  }

  public HashMap<Integer, ArrayList<Integer>> getPullDiscs() {
    return pullDiscs;
  }

  public void setPullDiscs(HashMap<Integer, ArrayList<Integer>> pullDiscs) {
    this.pullDiscs = pullDiscs;
  }

  public void setVisualizationTime(long visualizationTime) {
    this.visualizationTime = visualizationTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Score getScore() {
    return score;
  }

  public void setScore(Score score) {
    this.score = score;
  }
}
