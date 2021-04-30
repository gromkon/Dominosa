package Drawable.Graph.Lines;

import Utils.Colors;
import Utils.Layouts;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class InvisibleLine {

    private Line line;

    public InvisibleLine(double x1, double y1, double x2, double y2) {
        line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(Layouts.INVISIBLE_LINE_WIDTH);
        line.setStroke(Colors.getInvisibleLineColor());
        line.setStrokeLineCap(StrokeLineCap.BUTT);
    }

    public Line getLine() {
        return line;
    }
}
