package de.thundergames.game.util;

import de.thundergames.game.map.Field;
import de.thundergames.game.map.Floors;
import org.jetbrains.annotations.NotNull;

public class Mole {

  private final Floors floor;
  private final Field field;
  private boolean inHole = false;
  private final int MoleID;

  public Mole(@NotNull final Floors floor, @NotNull final Field field, int moleID) {
    this.floor = floor;
    this.field = field;
    MoleID = moleID;
  }

  public int getMoleID() {
    return MoleID;
  }

  public Floors getFloor() {
    return floor;
  }

  public Field getField() {
    return field;
  }

  public boolean isMoveable() {
    //TODO hier logik einbauen
    return false;
  }

  public boolean isInHole() {
    return inHole;
  }

  public void setInHole(final boolean inHole) {
    this.inHole = inHole;
  }
}


