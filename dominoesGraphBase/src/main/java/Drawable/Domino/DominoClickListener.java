package Drawable.Domino;


import Drawable.Graph.Cell.Element;
import javafx.event.Event;
import javafx.event.EventHandler;

public class DominoClickListener implements EventHandler {

    private Element[][] graph;
    private HorizontalDomino domino;
    private HorizontalDomino[] dominoes;

    public DominoClickListener(HorizontalDomino domino, HorizontalDomino[] dominoes) {
        this.domino = domino;
        this.graph = domino.getGraph();
        this.dominoes = dominoes;
    }

    @Override
    public void handle(Event event) {
        if (domino.isHighlight()) {
            blur();
        } else {
            highlight();
        }
    }

    private void highlight() {
        blurOtherDominoes();
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length - 1; x++) {
                if (domino.isThisDomino(graph[y][x].getValue(), graph[y][x + 1].getValue())) {
                    if (!graph[y][x].isRight() && !graph[y][x + 1].isLeft()) {
                        graph[y][x].highlight();
                        graph[y][x + 1].highlight();
                    }
                }
            }
        }
        for (int y = 0; y < graph.length - 1; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                if (domino.isThisDomino(graph[y][x].getValue(), graph[y + 1][x].getValue())) {
                    if (!graph[y][x].isBot() && !graph[y + 1][x].isTop()) {
                        graph[y][x].highlight();
                        graph[y + 1][x].highlight();
                    }
                }
            }
        }
        domino.setHighlight(true);
    }

    private void blurOtherDominoes() {
        for (HorizontalDomino d: dominoes) {
            if (!d.equals(domino)) {
                d.blur();
            }
        }
    }

    public void reHighlight() {
        blur();
        highlight();
    }

    public void blur() {
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                graph[y][x].blur();
            }
        }
        domino.setHighlight(false);
    }
}
