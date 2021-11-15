/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra 10 2021
 * File created on 15.11.21, 10:24 by Carina
 * Latest changes made by Carina on 15.11.21, 10:22
 * All contents of "ClientThread" are protected by copyright.
 * The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra 10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra 10.
 */
package de.thundergames.networking.client;

import de.thundergames.networking.util.NetworkThread;
import java.io.IOException;
import java.net.Socket;
import org.jetbrains.annotations.NotNull;

public class ClientThread extends NetworkThread {

  protected Client client;

  public ClientThread(@NotNull final Socket socket, final int id, Client client) throws IOException {
    super(socket, id);
    this.client = client;
  }

  public Client getClient() {
    return client;
  }

  /**
   * @author Carina
   * @use disconnects the ClientThread from the System and will run the shutdown-logic of the
   *     playerSystem
   */
  @Override
  public void disconnect() {
    {
      try {
        System.out.println("Server disconnected!");
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setID(final int id) {
    this.id = id;
  }
}
