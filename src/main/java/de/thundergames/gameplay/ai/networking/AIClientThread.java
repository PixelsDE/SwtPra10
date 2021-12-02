/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 02.12.21, 15:53 by Carina latest changes made by Carina on 02.12.21, 15:53
 * All contents of "AIClientThread" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.gameplay.ai.networking;

import de.thundergames.gameplay.ai.AI;
import de.thundergames.gameplay.player.networking.Client;
import de.thundergames.gameplay.player.networking.ClientThread;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;

public class AIClientThread extends ClientThread {

  public AIClientThread(@NotNull Socket socket, int id, Client client) throws IOException {
    super(socket, id, client);

  }

  public AI getAIClient() {
    return (AI) client;
  }
}
