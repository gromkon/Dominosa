package Drawable.Graph.Cell;

import Drawable.Domino.HorizontalDomino;
import javafx.event.Event;
import javafx.event.EventHandler;

public class LineClickListener implements EventHandler {

    private Element element1;
    private Element element2;
    private boolean isHorizontal;
    private HorizontalDomino[] horizontalDominoes;

    public LineClickListener(Element element1, Element element2, boolean isHorizontal, HorizontalDomino[] horizontalDominoes) {
        this.element1 = element1;
        this.element2 = element2;
        this.isHorizontal = isHorizontal;
        this.horizontalDominoes = horizontalDominoes;
    }

    @Override
    public void handle(Event event) {
        if (isHorizontal) {
            if (element1.isRight() && element2.isLeft()) {
                element1.setRight(false);
                element2.setLeft(false);
                changeDominoInfo(element1.getValue(), element2.getValue());
            } else if (!element1.isRight() && !element2.isLeft()) {
                element1.setRight(true);
                element2.setLeft(true);
                changeDominoInfo(element1.getValue(), element2.getValue());
            }
        } else {
            if (element1.isBot() && element2.isTop()) {
                element1.setBot(false);
                element2.setTop(false);
                changeDominoInfo(element1.getValue(), element2.getValue());
            } else if (!element1.isBot() && !element2.isTop()) {
                element1.setBot(true);
                element2.setTop(true);
                changeDominoInfo(element1.getValue(), element2.getValue());
            }
        }
    }

    private void changeDominoInfo(int value1, int value2) {
        for (HorizontalDomino domino: horizontalDominoes) {
            if (domino.isThisDomino(value1, value2)) {
                domino.calcCount();
                domino.reHighlight();
            }
            domino.checkBackgroundColor();
        }
    }
}
