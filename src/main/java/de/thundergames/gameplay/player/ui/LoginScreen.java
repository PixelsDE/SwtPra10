/*
 * Copyright Notice for Swtpra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 22.11.21, 21:41 by Carina latest changes made by Carina on 22.11.21, 19:55 All contents of "LoginScreen" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */
package de.thundergames.gameplay.player.ui;

import de.thundergames.gameplay.player.networking.Client;
import de.thundergames.gameplay.player.ui.GameSelection.GameSelection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen extends Application {

  private final boolean loggedIn = false;
  @FXML
  private ResourceBundle resources;
  @FXML
  private URL location;
  @FXML
  private TextField ip;
  @FXML
  private Button login;
  @FXML
  private TextField name;
  @FXML
  private TextField port;

  /**
   * @param event
   * @author Carina
   * @use handles the login button when clicked
   */
  @FXML
  void onLoginButtonClick(ActionEvent event) throws IOException {
/*  TODO: Hier
    String ip = this.ip.getText();
    String port = this.port.getText();
    String name = this.name.getText();
    if (ip != "" && port != "" && name != "" && !loggedIn) {
      System.out.println("IP: " + ip + " Port: " + port + " Name: " + name);
      Stage stage = (Stage) login.getScene().getWindow();
      Client client = new Client(Integer.parseInt(port), ip, name);
      JSONObject object;
      object = new JSONObject();
      object.put("type", Packets.LOGIN.getPacketType());
      var json = new JSONObject();
      json.put("name", name);
      object.put("value", json.toString());
      client.create();
      loggedIn = true;
      client.getClientThread().sendPacket(new Packet(object));
      stage.close();
    }*/
    String ip = "127.0.0.1";
    String port = "5000";
    String name = "Nick";
    Client client = new Client(Integer.parseInt(port), ip, name);
    client.create();
    new GameSelection().create(event);
  }

  @FXML
  void initialize() {
    assert ip != null : "fx:id=\"ip\" was not injected: check your FXML file 'LoginScreen.fxml'.";
    assert login != null
        : "fx:id=\"login\" was not injected: check your FXML file 'LoginScreen.fxml'.";
    assert name != null
        : "fx:id=\"name\" was not injected: check your FXML file 'LoginScreen.fxml'.";
    assert port != null
        : "fx:id=\"port\" was not injected: check your FXML file 'LoginScreen.fxml'.";
  }

  public void create(String... args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    location =
            new File("src/main/java/de/thundergames/gameplay/player/ui/LoginScreen.fxml")
            .toURI()
            .toURL();
    Parent root = FXMLLoader.load(location);
    primaryStage.setResizable(false);
    primaryStage.setTitle("Maulwurf Company");
    primaryStage.setScene(new Scene(root));
    initialize();
    primaryStage.show();
  }
}
