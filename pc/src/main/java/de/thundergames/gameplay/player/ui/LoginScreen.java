/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 12.01.22, 17:08 by Carina Latest changes made by Carina on 12.01.22, 17:08 All contents of "LoginScreen" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */
package de.thundergames.gameplay.player.ui;

import de.thundergames.gameplay.player.Client;
import de.thundergames.playmechanics.util.Dialog;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginScreen extends Application {

  @FXML
  private ResourceBundle resources;
  @FXML
  private TextField ip;
  @FXML
  private Button login;
  @FXML
  private TextField name;
  @FXML
  private TextField port;

  public static void create(final String... args) {
    launch(args);
  }

  /**
   * @param event
   * @author Carina and Philipp
   * @use handles the login button when clicked
   */
  @FXML
  void onLoginButtonClick(ActionEvent event) throws IOException {
    var ip = this.ip.getText();
    var port = this.port.getText();
    var name = this.name.getText();
    var errorMessage = "";
    if (ip.equals("")) {
      errorMessage += "IP Feld muss ausgefuellt sein!\n";
    }
    if (port.equals("")) {
      errorMessage += "Port Feld muss ausgefuellt sein!\n";
    } else {
      try {
        int intPort = Integer.parseInt(port);
        if (intPort < 0 || intPort > 65535) {
          errorMessage += "Port Feld muss eine ganze Zahl zwischen 0 und 65535 sein!\n";
        }
      } catch (NumberFormatException e) {
        errorMessage += "Port Feld muss eine ganze Zahl zwischen 0 und 65535 sein!\n";
      }
    }
    if (name.length() > 32) {
      errorMessage += "Namen duerfen maximal 32 Zeichen lang sein!\n";
    }
    if (errorMessage.equals("")) {
      System.out.println("IP: " + ip + " Port: " + port + " Name: " + name);
      var client = new Client(Integer.parseInt(port), ip, name);
      client.create();
      if (client.isConnected()) {
        new PlayerMenu().create(event);
      } else {
        Dialog.show("Konnte mich nicht mit dem Server verbinden.", "Verbindungsfehler", Dialog.DialogType.ERROR);
      }
    } else {
      Dialog.show(errorMessage, "Falscher Feldinhalt", Dialog.DialogType.ERROR);
    }
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

  @Override
  public void start(Stage primaryStage) throws Exception {
    var loader = new FXMLLoader(getClass().getResource("/player/style/LoginScreen.fxml"));
    var root = (Parent) loader.load();
    primaryStage.setResizable(false);
    primaryStage.setTitle("Maulwurf Company");
    primaryStage.setScene(new Scene(root));
    initialize();
    primaryStage.show();
  }
}
