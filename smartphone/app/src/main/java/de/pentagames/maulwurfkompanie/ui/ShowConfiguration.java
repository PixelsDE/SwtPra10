package de.pentagames.maulwurfkompanie.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.pentagames.maulwurfkompanie.R;
import de.pentagames.maulwurfkompanie.client.Client;
import upb.maulwurfcompany.library.data.MovePenalty;

/**
 * ShowConfiguration is used to show the configuration and the rules during a game through a button.
 * Said window can be left via arrow button.
 *
 * @author Lennart
 */

public class ShowConfiguration extends AppCompatActivity {

  @Override
  protected void onCreate(@NonNull final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_rules);
    var mMessageWindow = (TextView) findViewById(R.id.messageWindow);
    var mTextView = (TextView) findViewById(R.id.textView2);
    mTextView.setText("Spielregeln");
    // returns to game window if arrow button is pressed
    var configbackButton = (Button) findViewById(R.id.rulesbackbutton);
    configbackButton.setOnClickListener(view -> {
      super.onBackPressed();
    });
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    StringBuilder pullDiscs = new StringBuilder();
    for (int i = 0; i < Client.messageHandler.game.pullDiscs.length; i++) {
      pullDiscs.append(Client.messageHandler.game.pullDiscs[i]).append(" ");
    }
    var penalty = "";
    var penaltyMessage = Client.messageHandler.game.movePenalty;
    if (penaltyMessage.equals(MovePenalty.NOTHING)){
      penalty = "keine<br/>";
    } else if (penaltyMessage.equals(MovePenalty.POINT_DEDUCTION)){
      penalty = "Punktabzug<br/>" +
      "<b> - Punktabzug bei Strafe:</b>  " + Client.messageHandler.game.deductedPoints + "<br/><br/>";
    } else if (penaltyMessage.equals(MovePenalty.KICK)){
      penalty = "Ausschluss aus dem Spiel<br/>";
    }
    var someMessage = "<h2>Konfiguration</h2>" +
            "<b> - Maximale Spieleranzahl:</b> " + Client.messageHandler.game.maxPlayerCount + "<br/>" +
            "<b> - Spielfeldgr????e:</b> " + Client.messageHandler.game.radius + "<br/>" +
            "<b> - Maulwurfanzahl:</b> " + Client.messageHandler.game.moleCount + "<br/>" +
            "<b> - Ebenen:</b> " + Client.messageHandler.game.levelCount + "<br/>" +
            "<b> - Spielkarten:</b>  " + pullDiscs + "<br/>" +
            "<b> - Sortierte Spielkarten:</b>  " + (Client.messageHandler.game.pullDiscsOrdered ? "ja" : "nein") + "<br/>" +
            "<b> - Zugzeit:</b>  " + Client.messageHandler.game.turnTime + " ms<br/>" +
            "<b> - Visualisierungszeit:</b>  " + Client.messageHandler.game.visualisationTime + " ms<br/>" +
            "<b> - Strafe:</b> " + penalty;
    var someMessage2 = "<h2>Regeln:<h2/>" +
            " - das Spiel kann mit zwei oder mehr Spielern gespielt werden<br/>" +
            " - jeder Spieler erh??lt beim Spielstart eine eigene Maulwurffarbe<br/>" +
            "<br/>" +
            "<b>Spielbeginn:</b><br/>" +
            " - jeder Spieler platziert seine Maulw??rfe auf der obersten Ebene<br/>" +
            " - Maulw??rfe d??rfen nicht auf ein bereits belegtes Feld gestellt werden<br/>" +
            " - Maulw??rfe d??rfen nicht in ein Loch gestellt werden<br/>" +
            " - Spielerreihnfolge wird festgelegt und nicht ver??ndert<br/>" +
            "<br/>" +
            "<b>Spielablauf:</b><br/>" +
            " - erster Spieler zieht eine Zugkarte und darf nur genau die angegebenen Schritte gehen<br/>" +
            " - pro Runde zieht jeder Spieler nur eine Karte<br/>" +
            " - sobald die Karten aufgebraucht sind, werden sie neu gemischt<br/>" +
            " - sobald alle L??cher in einer Ebene besetzt sind, wird die aktuelle Ebene mit allen Maulw??rfen, die sich nicht in L??chern befinden entfernt<br/>" +
            " - hat eine Spieler keine Maulw??rfe mehr, spielt er nicht mehr mit<br/>" +
            "<br/>" +
            "<b>Bewegungsm??glichkeiten:</b><br/>" +
            " - der Maulwurf darf nur gerade und in eine Richtung gezogen werden<br/>" +
            " - es d??rfen keine anderen Maulw??rfe ??bersprungen werden, egal ob sie in einem Loch sind oder nicht<br/>" +
            " - es darf nur die Anzahl Schritte gegangen werden, die auf der Zugkarte angegeben sind<br/>" +
            " - ein Spielzug enf??llt, falls kein eigener Maulwurf (inklusive der Maulw??rfe in L??chern) die komplette Anzahl der Schritte gehen kann<br/>" +
            " - wenn alle Maulw??rfe eines Spielers in L??chern sitzen, setzt der Spieler aus<br/>" +
            " - erreicht der bewegte Maulwurf am Ende des Zuges ein ???Ziehe erneut???-Feld, darf der Spieler einen weiteren Spielzug durchf??hren<br/>" +
            "<br/>" +
            "<b>Punkte:</b><br/>" +
            " - f??r jeden eigenen Maulwurf, der eine n??chste Ebene erreicht, erh??lt man Punkte<br/>" +
            " - f??r das Beenden des Spiels (Erreichen des letzten Lochs) erh??lt der Spieler zus??tzliche Siegpunkte<br/>" +
            "<br/>" +
            "<b>Ziel des Spiels:</b><br/>" +
            " - so viele eigene Maulw??rfe wie m??glich in L??chern platzieren um in die n??chste Ebene zu gelangen<br/>" +
            " - so viele Punkte wie m??glich erzielen<br/>" +
            " - der Spieler mit den meisten gesammelten Punkten gewinnt<br/>" +
            "<br/>" +
            "<b>Ende:</b><br/>" +
            " - das Spiel endet, sobald die letzte Ebene erreicht wurde<br/>" +
            "<br/>" +
            "<br/>";
    mMessageWindow.setText(Html.fromHtml(someMessage + someMessage2));
  }
}