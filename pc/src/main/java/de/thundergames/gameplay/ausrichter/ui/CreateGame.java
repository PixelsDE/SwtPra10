/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 13.01.22, 22:39 by Carina Latest changes made by Carina on 13.01.22, 22:39 All contents of "CreateGame" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.gameplay.ausrichter.ui;

import de.thundergames.MoleGames;
import de.thundergames.gameplay.ausrichter.ui.floor.*;
import de.thundergames.playmechanics.game.Game;
import de.thundergames.playmechanics.map.Field;
import de.thundergames.playmechanics.map.Map;
import de.thundergames.playmechanics.util.Dialog;
import de.thundergames.playmechanics.util.Punishments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Carina, Eva, Jana
 * @use to configure a game
 */

@Getter
public class CreateGame implements Initializable {

  // Liste von Floor welche DrawAgain<Field> und Hole<Field> enthält
  private static ArrayList<Floor> floors = new ArrayList<>();
  private static CreateGame createGameInstance;
  private static String molesAmountPrev;
  private static String maxPlayersPrev;
  private static String punishmentPrev;
  private static String radiusPrev;
  private static String thinkTimePrev;
  private static boolean pullDiscsOrderedPrev;
  private static String visualEffectsPrev;
  private static ArrayList<Integer> drawCardValuesList = new ArrayList<>();

  @FXML
  private Button configureDrawAgain;

  @FXML
  private Button configureHoles;
  private Map map;

  @FXML
  private ChoiceBox<Punishments> movePenalty;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button addItem;

  @FXML
  private Button back;

  @FXML
  private Button createGame;

  @FXML
  private TextField drawCardValue;

  @FXML
  private Button loadConfig;

  @FXML
  private TextField molesAmount;

  @FXML
  private TextField playerAmount;

  @FXML
  private ChoiceBox<String> punishment;

  @FXML
  private TextField radius;

  @FXML
  private Button removeAll;

  @FXML
  private TextArea drawCardValues;

  @FXML
  private TextField thinkTime;

  @FXML
  private CheckBox pullDiscsOrdered;

  @FXML
  private TextField visualEffects;
  private static String deductedPointsPrev;
  private String Spielmodus;

  public static String getDeductedPointsPrev() {
    return deductedPointsPrev;
  }

  public static void setDeductedPointsPrev(String deductedPointsPrev) {
    CreateGame.deductedPointsPrev = deductedPointsPrev;
  }

  public static String getMolesAmountPrev() {
    return molesAmountPrev;
  }

  public static void setMolesAmountPrev(String molesAmountPrev) {
    CreateGame.molesAmountPrev = molesAmountPrev;
  }

  public static ArrayList<Floor> getLevel() {
    return floors;
  }

  public static String getMaxPlayersPrev() {
    return maxPlayersPrev;
  }

  public static void setMaxPlayersPrev(String maxPlayersPrev) {
    CreateGame.maxPlayersPrev = maxPlayersPrev;
  }

  public static String getPunishmentPrev() {
    return punishmentPrev;
  }

  public static void setPunishmentPrev(String punishmentPrev) {
    CreateGame.punishmentPrev = punishmentPrev;
  }

  public static String getRadiusPrev() {
    return radiusPrev;
  }

  public static void setRadiusPrev(String radiusPrev) {
    CreateGame.radiusPrev = radiusPrev;
  }

  public static String getThinkTimePrev() {
    return thinkTimePrev;
  }

  public static void setThinkTimePrev(String thinkTimePrev) {
    CreateGame.thinkTimePrev = thinkTimePrev;
  }

  public static boolean isPullDiscsOrderedPrev() {
    return pullDiscsOrderedPrev;
  }

  public static void setPullDiscsOrderedPrev(boolean pullDiscsOrderedPrev) {
    CreateGame.pullDiscsOrderedPrev = pullDiscsOrderedPrev;
  }

  public static String getVisualEffectsPrev() {
    return visualEffectsPrev;
  }

  public static void setVisualEffectsPrev(String visualEffectsPrev) {
    CreateGame.visualEffectsPrev = visualEffectsPrev;
  }

  public static ArrayList<Integer> getDrawCardValuesList() {
    return drawCardValuesList;
  }

  public static void setDrawCardValuesList(ArrayList<Integer> drawCardValuesList) {
    CreateGame.drawCardValuesList = drawCardValuesList;
  }

  public static CreateGame getCreateGameInstance() {
    return createGameInstance;
  }

  public static ArrayList<Floor> getFloors() {
    return floors;
  }

  public static void setFloors(ArrayList<Floor> floors) {
    CreateGame.floors = floors;
  }

  @FXML
  void addItemButtonEvent(@NotNull final ActionEvent event) {
    if (drawCardValue.getText() != null && !"".equalsIgnoreCase(drawCardValue.getText())) {
      drawCardValuesList.add(Integer.valueOf(drawCardValue.getText()));
    }
    if (drawCardValues.getText() == null || "".equalsIgnoreCase(drawCardValues.getText())) {
      drawCardValues.setText(drawCardValue.getText());
    } else {
      drawCardValues.setText(drawCardValues.getText() + "\n" + drawCardValue.getText());
    }
    drawCardValue.clear();
  }

  /**
   * @author Carina, Jana, Eva
   * @use clears all components of the CreateGame
   */
  private void clearAllComponents() {
    drawCardValuesList.clear();
    drawCardValues.clear();
    drawCardValue.clear();
    playerAmount.clear();
    molesAmount.clear();
    thinkTime.clear();
    visualEffects.clear();
    radius.clear();
  }

  @FXML
  void removeAllButtonEvent(ActionEvent event) {
    drawCardValues.clear();
    drawCardValuesList.clear();
  }

  public void start(@NotNull final Stage primaryStage, String modus) throws Exception {
    var loader = new FXMLLoader(getClass().getResource("/ausrichter/style/CreateGame.fxml"));
    loader.setController(this);
    var root = (Parent) loader.load();
    primaryStage.setTitle("CreateGame");
    Spielmodus = modus;
    primaryStage.setResizable(false);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
    loadPrevSettings();
  }

  @FXML
  void initialize() {
    assert addItem != null
      : "fx:id=\"addItem\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert back != null
      : "fx:id=\"back\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert configureDrawAgain != null
      : "fx:id=\"configureDrawAgain\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert configureHoles != null
      : "fx:id=\"configureHoles\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert createGame != null
      : "fx:id=\"createGame\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert drawCardValue != null
      : "fx:id=\"drawCardValue\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert drawCardValues != null
      : "fx:id=\"drawCardValues\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert loadConfig != null
      : "fx:id=\"loadConfig\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert molesAmount != null
      : "fx:id=\"molesAmount\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert movePenalty != null
      : "fx:id=\"movePenalty\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert playerAmount != null
      : "fx:id=\"playerAmount\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert pullDiscsOrdered != null
      : "fx:id=\"pullDiscsOrdered\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert radius != null
      : "fx:id=\"radius\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert removeAll != null
      : "fx:id=\"removeAll\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert thinkTime != null
      : "fx:id=\"thinkTime\" was not injected: check your FXML file 'CreateGame.fxml'.";
    assert visualEffects != null
      : "fx:id=\"visualEffects\" was not injected: check your FXML file 'CreateGame.fxml'.";
    movePenalty.getItems().addAll(Punishments.values());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    createGameInstance = this;
    initialize();
  }

  @FXML
  void backButtonEvent(ActionEvent event) throws Exception {
    var primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    punishmentPrev = null;
    maxPlayersPrev = null;
    molesAmountPrev = null;
    thinkTimePrev = null;
    visualEffectsPrev = null;
    radiusPrev = null;
    drawCardValuesList.clear();
    floors.clear();
    savePrevSettings();
    if(Spielmodus.equalsIgnoreCase("TurnierModus")){
      AddGames.getAddGamesInstance().start(primaryStage, "Turniermodus");
    }else {
      Games.getGamesInstance().start(primaryStage);
    }
  }

  /**
   * @param event
   * @throws Exception
   * @author Carina, Jana, Eva
   * @use creates a new game with the settings from the user
   */
  @FXML
  void createGameButtonEvent(ActionEvent event) throws Exception {
    var floorMap = new ArrayList<Map>();
    for (var floor : floors) {
      var drawAgain = new HashSet<Field>();
      var holes = new HashSet<Field>();
      for (var fields : floor.getDrawAgainFields()) {
        var field = new Field(fields.getXPosition(), fields.getYPosition());
        field.setOccupied(false);
        field.setHole(false);
        drawAgain.add(field);
      }
      for (var fields : floor.getHoles()) {
        var field = new Field(fields.getXPosition(), fields.getYPosition());
        field.setOccupied(false);
        field.setHole(false);
        holes.add(field);
      }
      var newFloor = new Map(holes, drawAgain, floor.getPoints());
      floorMap.add(newFloor);
      if (holes.isEmpty() || floors.isEmpty()) {
        Dialog.show("Du musst erst das Spiel voll konfigurieren!", "Fehler!", Dialog.DialogType.ERROR);
        return;
      }
    }
    var id = MoleGames.getMoleGames().getGameHandler().getGames().size();
    if (!isLegalConfiguration(
      radius.getText() != null && !"".equalsIgnoreCase(radius.getText())
        ? Integer.parseInt(radius.getText())
        : 8,
      molesAmount.getText() != null && playerAmount != null ? Integer.parseInt(molesAmount.getText()) * Integer.parseInt(playerAmount.getText()) : 16)) {
      Dialog.show("Das Spiel ist nicht richtig konfiguriert!", "Fehler!", Dialog.DialogType.ERROR);
      return;
    }
    MoleGames.getMoleGames().getGameHandler().createNewGame(id);

    var game = MoleGames.getMoleGames().getGameHandler().getIDGames().get(id);
    game.getSettings().getFloors().addAll(floorMap);
    game.getSettings()
      .setMaxPlayers(
        (playerAmount.getText() != null && !"".equalsIgnoreCase(playerAmount.getText()))
          ? Integer.parseInt(playerAmount.getText())
          : 4);
    game.getSettings()
      .setRadius(
        (radius.getText() != null && !"".equalsIgnoreCase(radius.getText()))
          ? Integer.parseInt(radius.getText())
          : 6);
    game.getSettings()
      .setNumberOfMoles(
        (molesAmount.getText() != null && !"".equalsIgnoreCase(molesAmount.getText()))
          ? Integer.parseInt(molesAmount.getText())
          : 4);
    game.getSettings().setPullDiscsOrdered(pullDiscsOrdered.isSelected());
    game.getSettings()
      .setTurnTime(
        (thinkTime.getText() != null && !"".equalsIgnoreCase(thinkTime.getText()))
          ? Integer.parseInt(thinkTime.getText()) * 1000
          : 15000);
    if (!drawCardValuesList.isEmpty()) {
      game.getSettings().getPullDiscs().clear();
      game.getSettings().getPullDiscs().addAll(drawCardValuesList);
    }
    game.getSettings()
      .setVisualizationTime(
        (visualEffects.getText() != null && !"".equalsIgnoreCase(visualEffects.getText()))
          ? Integer.parseInt(visualEffects.getText()) * 1000
          : 5000);
    game.getSettings()
      .setMovePenalty(
        movePenalty.getSelectionModel().getSelectedItem() != null
          ? movePenalty.getSelectionModel().getSelectedItem().getName()
          : Punishments.NOTHING.getName());
    MoleGames.getMoleGames().getGameHandler().getIDGames().get(id).updateGameState();
    floors.clear();

    var primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    if(Spielmodus.equalsIgnoreCase("TurnierModus")){
      AddGames.getAddGamesInstance().start(primaryStage, Spielmodus);
      AddGames.getAddGamesInstance().updateTable();
    }else{
      Games.getGamesInstance().start(primaryStage);
      Games.getGamesInstance().updateTable();
    }

    for (var observer : MoleGames.getMoleGames().getServer().getObserver()) {
      MoleGames.getMoleGames().getServer().getPacketHandler().overviewPacket(observer);
    }
  }

  @FXML
  void loadConfigButtonEvent(ActionEvent event) throws Exception {
    savePrevSettings();
    var primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    new SettingsImport().start(primaryStage);
  }

  @FXML
  void onConfigureDrawAgain(ActionEvent event) throws Exception {
    savePrevSettings();
    var primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    new DrawAgainConfiguration().start(primaryStage, Spielmodus);
  }

  @FXML
  void onConfigureHoles(ActionEvent event) throws Exception {
    savePrevSettings();
    var primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    new HolesConfiguration().start(primaryStage, Spielmodus);
  }

  /**
   * @author Carina
   * @use checks if a configuration was legal or not
   */
  private boolean isLegalConfiguration(final int radius, int numberOfMoles) {
    if (radius <= 0 || numberOfMoles <= 0) {
      return false;
    }
    if (thinkTime.getText() != null && !"".equalsIgnoreCase(thinkTime.getText())) {
      if (Integer.parseInt(thinkTime.getText()) <= 0) {
        return false;
      }
    }
    if (visualEffects.getText() != null && !"".equalsIgnoreCase(visualEffects.getText())) {
      if (Integer.parseInt(visualEffects.getText()) < 0) {
        return false;
      }
    }
    if (!drawCardValuesList.isEmpty()) {
      for (var value : drawCardValuesList) {
        if (value <= 0) {
          return false;
        }
      }
    }
    if (playerAmount.getText() != null && !"".equalsIgnoreCase(playerAmount.getText())) {
      if (Integer.parseInt(playerAmount.getText()) <= 1) {
        return false;
      }
    }
    if (MoleGames.getMoleGames().getServer().isDebug()) {
      System.out.println(
        "Server: Testing the configuration with a radius of: "
          + radius
          + " and a mole amount of: "
          + numberOfMoles);
    }

    var holeDouble = new ArrayList<Hole>();
    var holes = new ArrayList<Hole>();
    var game = new Game(1);
    game.setRadius(radius);
    var map = new Map(game, new HashSet<>(), new HashSet<>(), 1);
    map.build(game);
    if (numberOfMoles <= floors.get(0).getHoles().size()) {
      return false;
    }
    for (var floor : floors) {
      if (floor.getHoles().size() == 0) {
        return false;
      }
      // check for the amount of holes
      if (floors.size() > floors.indexOf(floor) + 1) {
        if (floor.getHoles().size() < floors.get(floors.indexOf(floor) + 1).getHoles().size()) {
          return false;
        }
      }
      numberOfMoles -= floor.getHoles().size();
      if (numberOfMoles < 0) {
        return false;
      }
      for (var field : floor.getDrawAgainFields()) {
        if (!map.getFieldMap().containsKey(List.of(field.getXPosition(), field.getYPosition()))) {
          return false;
        }
      }
      for (var hole : floor.getHoles()) {
        holeDouble.add(hole);
        if (!map.getFieldMap().containsKey(List.of(hole.getXPosition(), hole.getYPosition()))) {
          return false;
        }
      }
      if (holes.isEmpty()) {
        holes.addAll(floor.getHoles());
      } else {
        for (var hole : floor.getHoles()) {
          for (var holeCheck : holes) {
            if (hole.getXPosition() == holeCheck.getXPosition()
              && hole.getYPosition() == holeCheck.getYPosition()) {
              return false;
            }
          }
        }
        holes.clear();
        holes.addAll(floor.getHoles());
      }
    }
    var prev = (Hole) null;
    for (var hole : holeDouble) {
      if (prev == null) {
        prev = hole;
      } else {
        if (hole.getXPosition() == prev.getXPosition()
          && hole.getYPosition() == prev.getYPosition()) {
          return false;
        }
      }
    }
    if (floors.size() < 1) {
      return false;
    }
    return floors.get(floors.size() - 1).getHoles().size() >= 1;
  }

  /**
   * @author Carina, Jana, Eva
   * @use loads prev configs.
   */
  public void
  loadPrevSettings() {
    molesAmount.setText(molesAmountPrev);
    playerAmount.setText(maxPlayersPrev);
    radius.setText(radiusPrev);
    thinkTime.setText(thinkTimePrev);
    visualEffects.setText(visualEffectsPrev);
    pullDiscsOrdered.setSelected(pullDiscsOrderedPrev);
    for (var value : drawCardValuesList) {
      if (drawCardValues.getText() == null || "".equalsIgnoreCase(drawCardValues.getText())) {
        drawCardValues.setText(value.toString());
      } else {
        drawCardValues.setText(drawCardValues.getText() + "\n" + value.toString());
      }
    }
    if (punishmentPrev != null) {
      movePenalty.setValue(Punishments.valueOf(punishmentPrev));
    }
  }

  /**
   * @author Carina, Jana, Eva
   * @use saves prev configs.
   */
  private void savePrevSettings() {
    molesAmountPrev = molesAmount.getText();
    maxPlayersPrev = playerAmount.getText();
    radiusPrev = radius.getText();
    thinkTimePrev = thinkTime.getText();
    visualEffectsPrev = visualEffects.getText();
    pullDiscsOrderedPrev = pullDiscsOrdered.isSelected();
    try {
      punishmentPrev = movePenalty.getSelectionModel().getSelectedItem().getName();
    } catch (Exception ignored) {
    }
  }
}
