package Drawable.Graph.Lines;

import Drawable.Graph.Cell.LineClickListener;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class CustomLine {

    private DefaultLine defaultLine;
    private TouchedLine touchedLine;
    private InvisibleLine invisibleLine;

    public CustomLine(double x1, double y1, double x2, double y2) {
        defaultLine = new DefaultLine(x1, y1, x2, y2);
        touchedLine = new TouchedLine(x1, y1, x2, y2);
        invisibleLine = new InvisibleLine(x1, y1, x2, y2);
    }

    public Line getDefaultLine() {
        return defaultLine.getLine();
    }

    public Line getTouchedLine() {
        return touchedLine.getLine();
    }

    public Line getInvisibleLine() {
        return invisibleLine.getLine();
    }

    public void addDefaultLineToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(defaultLine.getLine());
    }

    public void addTouchedLineToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(touchedLine.getLine());
    }

    public void addInvisibleLineToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(invisibleLine.getLine());
    }

    public void addClickListener(LineClickListener lineClickListener) {
        invisibleLine.getLine().setOnMouseClicked(lineClickListener);
    }

    public void touch() {
        touchedLine.setVisible(true);
    }

    public void untouched() {
        touchedLine.setVisible(false);
    }

    public void setTouched(boolean touched) {
        touchedLine.setVisible(touched);
    }
}

