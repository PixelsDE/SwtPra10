/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 10.01.22, 22:08 by Carina Latest changes made by Carina on 10.01.22, 22:07 All contents of "LobbyObserverTournament" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.gameplay.player.ui.tournamentselection;

import de.thundergames.gameplay.player.Client;
import de.thundergames.gameplay.player.board.GameBoard;
import de.thundergames.gameplay.util.SceneController;
import de.thundergames.playmechanics.game.GameStates;
import de.thundergames.playmechanics.util.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Getter
@Setter
public class LobbyObserverTournament implements Initializable {

  @FXML
  private static Client CLIENT;
  private static LobbyObserverTournament OBSERVER;
  @FXML
  private Text PlayerName;
  @FXML
  private Text PlayerJoined;
  @FXML
  private Text JoinedSuccessfully;

  private int selectedTournamentID;
  private Stage primaryStage;

  public static LobbyObserverTournament getObserver() {
    return OBSERVER;
  }

  public void create(Stage primaryStage) throws IOException {
    CLIENT = Client.getClientInstance();
    OBSERVER = this;
    createScene(primaryStage);
  }

  private void createScene(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    var loader = SceneController.loadFXML("/player/style/LobbyObserverTournament.fxml");
    loader.setController(this);
    var root = (Parent) loader.load();
    primaryStage.setTitle("Maulwurf Company");
    primaryStage.setResizable(false);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
    primaryStage.setOnCloseRequest(ev -> logout(primaryStage));
    // region Create button events
    // set event for back button
    var btnBack = (Button) (primaryStage.getScene().lookup("#backToTournamentSelection"));
    btnBack.setOnAction(
      e -> {
        try {
          onBackClick(e);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      });
    // endregion
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    PlayerName.setText("Spieler: " + CLIENT.name);
  }

  /**
   * @author Nick
   * @use processes the click on the back button, loads previous scene GameSelection and informs
   * server player has left via leaveGame Packet (method inspired by "onSignOutClick()" -> see
   * GameSelection
   */
  @FXML
  void onBackClick(ActionEvent event) throws IOException {
    CLIENT.getClientPacketHandler().leaveTournament(selectedTournamentID);
    new TournamentSelection().create(event);
  }

  /**
   * Is called when the close button is clicked. Logout user.
   *
   * @param stage current stage
   */
  private void logout(Stage stage) {
    CLIENT.getClientPacketHandler().logoutPacket();
    CLIENT.getClientPacketHandler().leaveTournament(selectedTournamentID);
    stage.close();
  }

  /**
   * @author Nick
   * @use Changes the opacity of a text field with the content "Ein weiterer Spieler ist
   * beigetreten" thus making it visible for 3 seconds when another player has joined
   * respectively when the client has received playerJoined packet.
   */
  public void showNewPlayer() {
    PlayerJoined.setOpacity(1.0);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PlayerJoined.setOpacity(0.0);
  }

  /**
   * @author Nick
   * @use Changes the opacity of a text field with the content "Beitritt zum Turnier war
   * erfolgreich! Bitte warten." thus making it visible for 5 seconds.
   */
  public void showPLayerJoin(Player player) {
    JoinedSuccessfully.setOpacity(1.0);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    JoinedSuccessfully.setOpacity(0.0);
  }

  /**
   * @author Nick
   * @use Create scene and spectate the game of tournament
   */
  public void spectateGame() {
    Platform.runLater(() -> {
      var status = CLIENT.getGameState().getStatus();
      if (Objects.equals(status, GameStates.STARTED.toString())
        || Objects.equals(status, GameStates.PAUSED.toString()))
      {
        GameBoard board = new GameBoard();
        board.create(primaryStage,true);
        board.setTournamentId(selectedTournamentID);
      }
    });
  }

  public void showPlayerLeave(Player player) {
  }

  public void showPlayerKicked(Player player) {
  }
}