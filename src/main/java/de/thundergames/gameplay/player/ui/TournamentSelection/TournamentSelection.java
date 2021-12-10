/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2021
 * File created on 06.12.21, 22:18 by Carina latest changes made by Carina on 06.12.21, 22:18
 * All contents of "TournamentSelection" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.gameplay.player.ui.tournamentselection;

import de.thundergames.gameplay.player.Client;
import de.thundergames.gameplay.player.ui.PlayerMenu;
import de.thundergames.gameplay.util.SceneController;
import de.thundergames.playmechanics.game.GameState;
import de.thundergames.playmechanics.game.GameStates;
import de.thundergames.playmechanics.game.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TournamentSelection implements Initializable {

  private static Client client;
  private static TournamentSelection tournamentSelection;
  @FXML
  private Text PlayerName;
  @FXML
  private TableView<Tournament> gameTable;
  @FXML
  private TableColumn<Tournament, Integer> tournamentID;
  @FXML
  private TableColumn<Tournament, String> playerCount;
  private Stage primaryStage;

  public static TournamentSelection getTournamentSelection() {
    return tournamentSelection;
  }

  /**
   * Create the Scene for TournamentSelection
   *
   * @param event event from the current scene to build this scene on same object
   * @throws IOException error creating the scene TournamentSelection
   */
  public void create(ActionEvent event) throws IOException {
    tournamentSelection = this;
    primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    var loader = SceneController.loadFXML("player/style/TournamentSelection.fxml");
    loader.setController(this);
    Parent root = loader.load();
    primaryStage.setTitle("Maulwurf Company");
    primaryStage.setResizable(false);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
    primaryStage.setOnCloseRequest(ev -> logout(primaryStage));
    // region Create button events
    // set event for back button
    var btnBack = (Button) (primaryStage.getScene().lookup("#backToMenu"));
    btnBack.setOnAction(
      e -> {
        try {
          backToMenu(e);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      });
    // set event for spectate game
    var btnSpectateGame = (Button) (primaryStage.getScene().lookup("#spectateGame"));
    btnSpectateGame.setOnAction(
      e -> {
        try {
          spectateGame(e);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      });
    // endregion
  }

  /**
   * Is called when the object is initialized
   *
   * @param location  of base class Initialize
   * @param resources of base class Initialize
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    client = Client.getClient();
    // show username at scene
    PlayerName.setText("Spieler: " + client.name);
    // set value for each row
    tournamentID.setCellValueFactory(new PropertyValueFactory<>("HashtagWithTournamentID"));
    playerCount.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
    // load data for tableview
    updateTable();
  }

  /**
   * Refresh the games of tableview
   */
  public void updateTable() {
    // clear tableview and get tournaments from server and add all to table view
    gameTable.getItems().clear();
    gameTable.getItems().addAll(client.getTournaments());
  }

  /**
   * Button at Scene TournamentSelection. Call scene PlayerMenu
   *
   * @param event event from the current scene to build PlayerMenu on same object
   * @throws IOException error creating the scene PlayerMenu
   */
  @FXML
  void backToMenu(ActionEvent event) throws IOException {
    new PlayerMenu().create(event);
  }

  /**
   * Is called when the close button is clicked. Logout user.
   *
   * @param stage current stage
   */
  private void logout(Stage stage) {
    client.getClientPacketHandler().logoutPacket(client);
    stage.close();
  }

  /**
   * Button at Scene TournamentSelection. Observe the tournament. If tournament is already started,
   * spectate the tournament, else join the spectator lobby.
   *
   * @param event event from the current scene to build next scene on same object
   * @throws IOException error at creating the scene
   */
  @FXML
  public void spectateGame(ActionEvent event) throws IOException {
    var selectedItem = gameTable.getSelectionModel().getSelectedItem();
    // If no item of tableview is selected.
    if (selectedItem == null) {
      JOptionPane.showMessageDialog(
        null,
        "Es wurde kein Turnier selektiert!",
        "Turnier beobachten",
        JOptionPane.ERROR_MESSAGE);
      return;
    }
    // Send Packet to spectate tournament to get GameState
    client
      .getClientPacketHandler()
      .enterTournamentPacket(client, selectedItem.getTournamentID(), false);
    var currentGameState = client.getGameState();
    if (currentGameState == null) {
      System.out.println("TournamentSelection: GameState is null");
      return;
    }
    if (Objects.equals(currentGameState.getStatus(), GameStates.STARTED.toString())
      || Objects.equals(currentGameState.getStatus(), GameStates.PAUSED.toString())) {
      spectateGame(currentGameState);
    } else if (Objects.equals(currentGameState.getStatus(), GameStates.NOT_STARTED.toString())) {
      new LobbyObserverTournament().create(event, selectedItem.getTournamentID());
    } else if (Objects.equals(currentGameState.getStatus(), GameStates.OVER.toString())) {
      loadScoreboard();
    }
  }

  /**
   * @author Marc
   * @use Load scene of scoreboard
   */
  private void loadScoreboard() {
    client.getClientPacketHandler().getScorePacket(client);
    // TODO: Get TournamentState
    // var gameScore = client.getTournamentState().getTournamentScore();
    // Todo:Open scene of ScoreBoard
  }

  /**
   * @author Marc
   * @use Load scene of game
   */
  private void spectateGame(GameState gameState) {
    primaryStage.close();
    // Todo:Open scene of Game
  }
}
