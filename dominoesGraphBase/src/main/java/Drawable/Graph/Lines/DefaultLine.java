package Drawable.Graph.Lines;

import Utils.Colors;
import Utils.Layouts;
import javafx.scene.shape.Line;

public class DefaultLine {

    private Line line;

    public DefaultLine(double x1, double y1, double x2, double y2) {
        line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(Layouts.DEFAULT_VISIBLE_LINE_WIDTH);
        line.setStroke(Colors.getNotTouchedLineColor());
    }

    public Line getLine() {
        return line;
    }

    public boolean isVisible() {
        return line.isVisible();
    }

    public void setVisible(boolean value) {
        line.setVisible(value);
    }
}
