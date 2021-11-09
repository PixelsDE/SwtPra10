package de.thundergames.game.util;

public enum Punishments {

  NOTHING(0),
  KICK(1),
  POINTS(2);

  private final int id;

  Punishments(final int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static synchronized Punishments getByID(int id) {
    for (Punishments punishment : Punishments.values()) {
      if (punishment.getId() == id) {
        return punishment;
      }
    }
    return null;
  }
}