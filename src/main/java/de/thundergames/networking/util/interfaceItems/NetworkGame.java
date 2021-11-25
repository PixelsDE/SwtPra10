/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 22.11.21, 21:41 by Carina latest changes made by Carina on 22.11.21, 19:55 All contents of "NetworkGame" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.networking.util.interfaceItems;

import de.thundergames.filehandling.Score;
import java.util.ArrayList;

public class NetworkGame {

  private final int gameID;
  private int currentPlayerCount;
  private int maxPlayerCount;
  private int levelCount;
  private int moleCount;
  private int radius;
  private boolean pullDiscsOrdered;
  private ArrayList<Integer> pullDiscs = new ArrayList<>();
  private long turnTime;
  private long visualizationTime;
  private String status;
  private String movePenalty;
  private long startDateTime;
  private long finishDateTime;
  private Score result;

  public NetworkGame(int gameID) {
    this.gameID = gameID;
  }

  public int getGameID() {
    return gameID;
  }

  public int getCurrentPlayerCount() {
    return currentPlayerCount;
  }

  public void setCurrentPlayerCount(int currentPlayerCount) {
    this.currentPlayerCount = currentPlayerCount;
  }

  public int getMaxPlayerCount() {
    return maxPlayerCount;
  }

  public void setMaxPlayerCount(int maxPlayerCount) {
    this.maxPlayerCount = maxPlayerCount;
  }

  public int getLevelCount() {
    return levelCount;
  }

  public void setLevelCount(int levelCount) {
    this.levelCount = levelCount;
  }

  public int getMoleCount() {
    return moleCount;
  }

  public void setMoleCount(int moleCount) {
    this.moleCount = moleCount;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public boolean isPullDiscsOrdered() {
    return pullDiscsOrdered;
  }

  public void setPullDiscsOrdered(boolean pullDiscsOrdered) {
    this.pullDiscsOrdered = pullDiscsOrdered;
  }

  public ArrayList<Integer> getPullDiscs() {
    return pullDiscs;
  }

  public void setPullDiscs(ArrayList<Integer> pullDiscs) {
    this.pullDiscs = pullDiscs;
  }

  public long getTurnTime() {
    return turnTime;
  }

  public void setTurnTime(long turnTime) {
    this.turnTime = turnTime;
  }

  public long getVisualizationTime() {
    return visualizationTime;
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

  public String getMovePenalty() {
    return movePenalty;
  }

  public void setMovePenalty(String movePenalty) {
    this.movePenalty = movePenalty;
  }

  public long getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(long startDateTime) {
    this.startDateTime = startDateTime;
  }

  public Score getScore() {
    return result;
  }

  public void setScore(Score result) {
    this.result = result;
  }

  public long getFinishDateTime() {
    return finishDateTime;
  }

  public void setFinishDateTime(long finishDateTime) {
    this.finishDateTime = finishDateTime;
  }
}