/*
 * Copyright Notice for SwtPra10
 * Copyright (c) at ThunderGames | SwtPra10 2022
 * File created on 09.01.22, 21:35 by Carina Latest changes made by Carina on 09.01.22, 21:35 All contents of "MoleHat" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at ThunderGames | SwtPra10. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of ThunderGames | SwtPra10.
 */

package de.thundergames.gameplay.player.board;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

public class MoleHat {
  /**
   * @param fill color used for the paths (expects a valid web color string, read: https://docs.oracle.com/javafx/2/api/javafx/scene/paint/Color.html)
   * @author Issam
   * @use creates a custom hat svg from paths for the mole
   */
  public static Group getHat(@NotNull final String fill, final int x, final int y, final int scale) {
    var g = new Group();
    var hat = new SVGPath();
    var tip = new SVGPath();
    var contour = new SVGPath();
    tip.setContent("M4 7H3v1h1V7ZM5 7H4v1h1V7ZM6 7H5v1h1V7ZM13 7h-1v1h1V7ZM14 7h-1v1h1V7ZM15 7h-1v1h1V7ZM7 8H6v1h1V8ZM8 8H7v1h1V8ZM9 8H8v1h1V8ZM10 8H9v1h1V8ZM11 8h-1v1h1V8ZM12 8h-1v1h1V8Z");
    tip.setFill(Color.web(fill, 0.7));
    hat.setContent("M7 1H6v1h1V1ZM8 1H7v1h1V1ZM9 1H8v1h1V1ZM10 1H9v1h1V1ZM11 1h-1v1h1V1ZM12 1h-1v1h1V1ZM5 2H4v1h1V2ZM6 2H5v1h1V2ZM7 2H6v1h1V2ZM8 2H7v1h1V2ZM9 2H8v1h1V2ZM10 2H9v1h1V2ZM11 2h-1v1h1V2ZM12 2h-1v1h1V2ZM13 2h-1v1h1V2ZM14 2h-1v1h1V2ZM3 3H2v1h1V3ZM4 3H3v1h1V3ZM5 3H4v1h1V3ZM6 3H5v1h1V3ZM7 3H6v1h1V3ZM8 3H7v1h1V3ZM9 3H8v1h1V3ZM10 3H9v1h1V3ZM11 3h-1v1h1V3ZM12 3h-1v1h1V3ZM13 3h-1v1h1V3ZM14 3h-1v1h1V3ZM15 3h-1v1h1V3ZM16 3h-1v1h1V3ZM3 4H2v1h1V4ZM4 4H3v1h1V4ZM5 4H4v1h1V4ZM6 4H5v1h1V4ZM7 4H6v1h1V4ZM8 4H7v1h1V4ZM9 4H8v1h1V4ZM10 4H9v1h1V4ZM11 4h-1v1h1V4ZM12 4h-1v1h1V4ZM13 4h-1v1h1V4ZM14 4h-1v1h1V4ZM15 4h-1v1h1V4ZM16 4h-1v1h1V4ZM3 5H2v1h1V5ZM4 5H3v1h1V5ZM5 5H4v1h1V5ZM6 5H5v1h1V5ZM7 5H6v1h1V5ZM8 5H7v1h1V5ZM9 5H8v1h1V5ZM10 5H9v1h1V5ZM11 5h-1v1h1V5ZM12 5h-1v1h1V5ZM13 5h-1v1h1V5ZM14 5h-1v1h1V5ZM15 5h-1v1h1V5ZM16 5h-1v1h1V5ZM2 6H1v1h1V6ZM3 6H2v1h1V6ZM4 6H3v1h1V6ZM5 6H4v1h1V6ZM6 6H5v1h1V6ZM7 6H6v1h1V6ZM8 6H7v1h1V6ZM9 6H8v1h1V6ZM10 6H9v1h1V6ZM11 6h-1v1h1V6ZM12 6h-1v1h1V6ZM13 6h-1v1h1V6ZM14 6h-1v1h1V6ZM15 6h-1v1h1V6ZM16 6h-1v1h1V6ZM17 6h-1v1h1V6ZM2 7H1v1h1V7ZM3 7H2v1h1V7ZM7 7H6v1h1V7ZM8 7H7v1h1V7ZM9 7H8v1h1V7ZM10 7H9v1h1V7ZM11 7h-1v1h1V7ZM12 7h-1v1h1V7ZM16 7h-1v1h1V7ZM17 7h-1v1h1V7Z");
    hat.setFill(Color.web(fill));
    contour.setContent("M7 0H6v1h1V0ZM8 0H7v1h1V0ZM9 0H8v1h1V0ZM10 0H9v1h1V0ZM11 0h-1v1h1V0ZM12 0h-1v1h1V0ZM5 1H4v1h1V1ZM6 1H5v1h1V1ZM13 1h-1v1h1V1ZM14 1h-1v1h1V1ZM3 2H2v1h1V2ZM4 2H3v1h1V2ZM15 2h-1v1h1V2ZM16 2h-1v1h1V2ZM2 3H1v1h1V3ZM17 3h-1v1h1V3ZM2 4H1v1h1V4ZM17 4h-1v1h1V4ZM1 5H0v1h1V5ZM2 5H1v1h1V5ZM17 5h-1v1h1V5ZM18 5h-1v1h1V5ZM1 6H0v1h1V6ZM18 6h-1v1h1V6ZM1 7H0v1h1V7ZM18 7h-1v1h1V7Z");
    g.getChildren().add(tip);
    g.getChildren().add(hat);
    g.getChildren().add(contour);
    g.setLayoutX(x);
    g.setLayoutY(y);
    g.setScaleX(scale);
    g.setScaleY(scale);
    return g;
  }
}
