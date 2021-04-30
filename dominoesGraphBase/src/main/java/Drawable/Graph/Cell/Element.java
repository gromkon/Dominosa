package Drawable.Graph.Cell;

import Drawable.Domino.HorizontalDomino;
import Drawable.Graph.Lines.CustomLine;
import LevelJSON.ElementJSON;
import Utils.Colors;
import Utils.LabelConfigurator;
import Utils.Layouts;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Element {

    private int value;
    private boolean top;
    private boolean bot;
    private boolean left;
    private boolean right;

    private int x;
    private int y;
    private Rectangle background;
    private Paint backgroundColor;
    private Label valueLbl;
    private Paint valueColor;

    private CustomLine topBorder;
    private CustomLine botBorder;
    private CustomLine leftBorder;
    private CustomLine rightBorder;

    public Element(int x, int y, int value) {
        this.value = value;
        top = false;
        bot = false;
        left = false;
        right = false;

        this.x = x;
        this.y = y;
        background = new Rectangle(x, y, Layouts.TABLE_CELL_SIZE, Layouts.TABLE_CELL_SIZE);
        background.setFill(Color.WHITE);
        backgroundColor = Colors.getBackgroundColor();

        valueLbl = new Label(String.valueOf(value));
        configureLabelFontAndPos();
        valueColor = Colors.getValueColor(value);
        valueLbl.setTextFill(valueColor);

        topBorder = new CustomLine(x, y, x + Layouts.TABLE_CELL_SIZE, y);
        botBorder = new CustomLine(x, y + Layouts.TABLE_CELL_SIZE, x + Layouts.TABLE_CELL_SIZE, y + Layouts.TABLE_CELL_SIZE);
        leftBorder = new CustomLine(x, y, x, y + Layouts.TABLE_CELL_SIZE);
        rightBorder = new CustomLine(x + Layouts.TABLE_CELL_SIZE, y, x + Layouts.TABLE_CELL_SIZE, y + Layouts.TABLE_CELL_SIZE);
    }

    private void configureLabelFontAndPos() {
        LabelConfigurator.configureLabelFontAndPos(valueLbl, x + Layouts.TABLE_CELL_SIZE / 2, y + Layouts.TABLE_CELL_SIZE / 2);
        valueLbl.setStyle("-fx-font-weight: bold");
    }

    public void addBackgroundToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(background);
    }

    public void addLabelToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(valueLbl);
    }

    public void addDefaultLinesToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(topBorder.getDefaultLine());
        anchorPane.getChildren().add(leftBorder.getDefaultLine());
        anchorPane.getChildren().add(botBorder.getDefaultLine());
        anchorPane.getChildren().add(rightBorder.getDefaultLine());
    }

    public void addTouchedLinesToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(topBorder.getTouchedLine());
        anchorPane.getChildren().add(leftBorder.getTouchedLine());
        anchorPane.getChildren().add(botBorder.getTouchedLine());
        anchorPane.getChildren().add(rightBorder.getTouchedLine());
    }

    public void addInvisibleLinesToAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().add(topBorder.getInvisibleLine());
        anchorPane.getChildren().add(leftBorder.getInvisibleLine());
        anchorPane.getChildren().add(botBorder.getInvisibleLine());
        anchorPane.getChildren().add(rightBorder.getInvisibleLine());
    }

    public void highlight() {
        background.setFill(backgroundColor);
    }

    public void blur() {
        background.setFill(Color.WHITE);
    }

    public boolean isHighlight() {
        return background.getFill() == backgroundColor;
    }

    public void addClickListener(Element element, HorizontalDomino[] horizontalDominoes, boolean isHorizontal) {
        LineClickListener lineClickListener = new LineClickListener(this, element, isHorizontal, horizontalDominoes);
        if (isHorizontal) {
            rightBorder.addClickListener(lineClickListener);
            element.leftBorder.addClickListener(lineClickListener);
        } else {
            botBorder.addClickListener(lineClickListener);
            element.topBorder.addClickListener(lineClickListener);
        }

    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
        topBorder.setTouched(top);
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
        botBorder.setTouched(bot);
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
        leftBorder.setTouched(left);
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
        rightBorder.setTouched(right);
    }

    public int getValue() {
        return value;
    }

    public void setElement(ElementJSON element) {
        setTop(element.isTop());
        setBot(element.isBot());
        setLeft(element.isLeft());
        setRight(element.isRight());
    }
}
