package Drawable.HighScore;

import Drawable.Timer.CustomTimer;
import Utils.Colors;
import Utils.LabelConfigurator;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HighScore {

    private final static String YOUR_RECORD = "Твой рекорд: ";
    private final static String YOUR_DONT_SOLVE_THIS_LEVEL = "Ты еще не решала этот уровень. У Тебя получится!";

    private Label label;

    public HighScore(int sec, double xRight, double yTop) {
        if (sec == 0) {
            label = new Label(YOUR_DONT_SOLVE_THIS_LEVEL);
        } else {
            label = new Label(YOUR_RECORD + calcTime(sec));
        }
        configureLabel(xRight, yTop);
    }

    private void configureLabel(double xRight, double yTop) {
        label.setTextFill(Colors.getHighScoreTextColor());
        LabelConfigurator.configureLabelFont(label);
        AnchorPane.setTopAnchor(label, yTop);
        AnchorPane.setRightAnchor(label, xRight);
    }

    private String calcTime(int sec) {
        return CustomTimer.calcTime(sec);
    }

    public void addToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(label);
    }


}
