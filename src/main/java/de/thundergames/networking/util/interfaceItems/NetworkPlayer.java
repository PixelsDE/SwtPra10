/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 22.11.21, 21:41 by Carina latest changes made by Carina on 22.11.21, 19:55 All contents of "NetworkPlayer" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.networking.util.interfaceItems;

public class NetworkPlayer {

  private final int clientID;
  private final String name;

  public NetworkPlayer(String name, int clientID) {
    this.name = name;
    this.clientID = clientID;
  }

  public String getName() {
    return name;
  }

  public int getClientID() {
    return clientID;
  }

}