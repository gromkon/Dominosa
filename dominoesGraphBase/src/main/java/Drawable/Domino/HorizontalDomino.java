package Drawable.Domino;

import Drawable.Graph.Cell.Element;
import Utils.Colors;
import Utils.LabelConfigurator;
import Utils.Layouts;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class HorizontalDomino {

    private int x;
    private int y;

    private Rectangle background;
    private Paint backgroundColor;
    private Rectangle borders;
    private Line middleLine;
    private Label firstLbl;
    private Label secondLbl;
    private Label countLbl;

    private int first;
    private int second;
    private int count;

    private DominoClickListener clickListener;
    private boolean isHighlight;

    private Element[][] graph;

    public HorizontalDomino(int x, int y, int first, int second) {
        this.x = x;
        this.y = y;

        background = new Rectangle(x, y, Layouts.DOMINO_WIDTH, Layouts.DOMINO_HEIGHT);
        backgroundColor = Colors.getDominoDefaultBackgroundColor();
        background.setFill(backgroundColor);

        middleLine = new Line(x + Layouts.DOMINO_WIDTH / 2, y, x + Layouts.DOMINO_WIDTH / 2, y + Layouts.DOMINO_HEIGHT);
        middleLine.setFill(Colors.getDominoLineColor());

        borders = new Rectangle(x, y, Layouts.DOMINO_WIDTH, Layouts.DOMINO_HEIGHT);
        borders.setFill(Colors.getDominoDefaultBackgroundColor());
        borders.setStroke(Colors.getDominoBordersColor());

        this.first = first;
        this.second = second;
        firstLbl = new Label(String.valueOf(first));
        firstLbl.setTextFill(Colors.getDominoValueColor(first));
        configureLabelPos(
                firstLbl,
                x + Layouts.DOMINO_WIDTH / 4,
                y + Layouts.DOMINO_HEIGHT / 2
        );
        secondLbl = new Label(String.valueOf(second));
        secondLbl.setTextFill(Colors.getDominoValueColor(second));
        configureLabelPos(
                secondLbl,
                x + Layouts.DOMINO_WIDTH * 3 / 4,
                y + Layouts.DOMINO_HEIGHT / 2
        );

        count = 0;
        countLbl = new Label(String.valueOf(count));
        countLbl.setTextFill(Colors.getDominoDefaultCountColor());
        configureLabelPos(
                countLbl,
                x + Layouts.DOMINO_WIDTH + (Layouts.FULL_DOMINO_WIDTH - Layouts.DOMINO_WIDTH) / 2,
                y + Layouts.DOMINO_HEIGHT / 2
        );

        graph = new Element[0][0];
        isHighlight = false;
    }

    public void calcCount() {
        count = 0;
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length - 1; x++) {
                if (isThisDomino(graph[y][x].getValue(), graph[y][x + 1].getValue())) {
                    if (!graph[y][x].isRight() && !graph[y][x + 1].isLeft()) {
                        count++;
                    }
                }
            }
        }
        for (int y = 0; y < graph.length - 1; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                if (isThisDomino(graph[y][x].getValue(), graph[y + 1][x].getValue())) {
                    if (!graph[y][x].isBot() && !graph[y + 1][x].isTop()) {
                        count++;
                    }
                }
            }
        }
        checkBackgroundColor();
        countLbl.setText(String.valueOf(count));

        // Чтобы еденичка подсвечивалась
        if (count == 1) {
            countLbl.setTextFill(Color.RED);
            countLbl.setStyle("-fx-font-weight: bold");
        } else {
            countLbl.setTextFill(Colors.getDominoDefaultCountColor());
            countLbl.setStyle("");
        }
    }

    public void checkBackgroundColor() {
        if (count == 0) {
            backgroundColor = Colors.getDominoZeroBackgroundColor();
        } else {
            if (isSolve()) {
                if (count == 1) {
                    backgroundColor = Colors.getDominoSolveBackgroundColor();
                } else {
                    backgroundColor = Colors.getDominoAlmostSolveBackgroundColor();
                }
            } else {
                if (count == 1) { // чтобы у 1 фон подсвечивался зеленым
                    backgroundColor = Color.GREEN;
                } else {
                    backgroundColor = Colors.getDominoDefaultBackgroundColor();
                }
            }
        }
        background.setFill(backgroundColor);

        // чтобы решенные пропадали
        if (backgroundColor.equals(Colors.getDominoSolveBackgroundColor())) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    private void setVisible(boolean visible) {
        borders.setVisible(visible);
        background.setVisible(visible);
        middleLine.setVisible(visible);
        firstLbl.setVisible(visible);
        secondLbl.setVisible(visible);
        countLbl.setVisible(visible);
    }

    private boolean isSolve() {
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length - 1; x++) {
                if (isThisDomino(graph[y][x].getValue(), graph[y][x + 1].getValue())) {
                    if (!graph[y][x].isRight() && !graph[y][x + 1].isLeft() &&          // между ними нет черты
                            graph[y][x].isTop() && graph[y][x + 1].isTop() &&           // верх закрашен
                            graph[y][x].isBot() && graph[y][x + 1].isBot() &&           // низ закрашен
                            graph[y][x].isLeft() && graph[y][x + 1].isRight()) {        // стороны закрашены
                        return true;
                    }
                }
            }
        }
        for (int y = 0; y < graph.length - 1; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                if (isThisDomino(graph[y][x].getValue(), graph[y + 1][x].getValue())) {
                    if (!graph[y][x].isBot() && !graph[y + 1][x].isTop() &&             // между ними нет черты
                            graph[y][x].isLeft() && graph[y + 1][x].isLeft() &&         // лево закрашено
                            graph[y][x].isRight() && graph[y + 1][x].isRight() &&       // право закрашено
                            graph[y][x].isTop() && graph[y + 1][x].isBot()) {        // верх и низ закрашены
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isThisDomino(int first, int second) {
        return (this.first == first && this.second == second) || (this.second == first && this.first == second);
    }

    public void reHighlight() {
        if (isHighlight) {
            clickListener.reHighlight();
        }
    }

    public void blur() {
        clickListener.blur();
    }

    public void addClickListener(HorizontalDomino[] dominoes) {
        clickListener = new DominoClickListener(this, dominoes);
        borders.setOnMouseClicked(clickListener);
        background.setOnMouseClicked(clickListener);
        middleLine.setOnMouseClicked(clickListener);
        firstLbl.setOnMouseClicked(clickListener);
        secondLbl.setOnMouseClicked(clickListener);
        countLbl.setOnMouseClicked(clickListener);
    }

    public void addToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(borders);
        anchorPane.getChildren().add(background);
        anchorPane.getChildren().add(middleLine);
        anchorPane.getChildren().add(firstLbl);
        anchorPane.getChildren().add(secondLbl);
        anchorPane.getChildren().add(countLbl);
    }

    private void configureLabelPos(Label label, double x, double y) {
        LabelConfigurator.configureLabelFontAndPos(label, (int) x, (int) y);
    }

    public void setGraph(Element[][] graph) {
        this.graph = graph;
        calcCount();
    }

    public Element[][] getGraph() {
        return graph;
    }

    public boolean isHighlight() {
        return isHighlight;
    }

    public void setHighlight(boolean highlight) {
        isHighlight = highlight;
    }

    public int getCount() {
        return count;
    }
}
