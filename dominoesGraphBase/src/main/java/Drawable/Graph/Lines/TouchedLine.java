package Drawable.Graph.Lines;

import Utils.Colors;
import Utils.Layouts;
import javafx.scene.shape.Line;

public class TouchedLine {

    private Line line;

    public TouchedLine(double x1, double y1, double x2, double y2) {
        this(x1, y1, x2, y2, false);
    }

    public TouchedLine(double x1, double y1, double x2, double y2, boolean visible) {
        line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(Layouts.TOUCHED_VISIBLE_LINE_WIDTH);
        line.setStroke(Colors.getTouchedLineColor());
        line.setVisible(visible);
    }

    public Line getLine() {
        return line;
    }

    public void setVisible(boolean value) {
        line.setVisible(value);
    }
}
