package Drawable;

import Drawable.Domino.HorizontalDomino;
import Drawable.Graph.Cell.Element;
import Drawable.HighScore.HighScore;
import Drawable.Timer.CustomTimer;
import Level.Level;
import Level.Domino;
import LevelJSON.LevelJSON;
import LevelJSON.ElementJSON;
import Utils.LabelConfigurator;
import Utils.Layouts;
import com.google.gson.Gson;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class CustomAnchorPane {

    private int sizeH;
    private int sizeW;
    private int levelNumber;
    private LevelJSON levelJSON;

    private AnchorPane anchorPane;

    private CustomTimer timer;

    private int[][] table;
    private Domino[] levelDominoes;
    private HorizontalDomino[] horizontalDominoes;
    private Element[][] graph;

    // Решен ли уровень на данный момент
    private boolean isSolveNow;

    public CustomAnchorPane(int sizeH, int sizeW, int number) {
        anchorPane = new AnchorPane();
        this.sizeH = sizeH;
        this.sizeW = sizeW;
        this.levelNumber = number;
        isSolveNow = false;

//        initRandomLevel();
        initLevelFromJSON();
        initLevel();
        initGraph();
        loadGraphStateFromJson();
        initDominoesAndAddToAnchorPane();

        addClickListenerOnGraph();
        addClickListenerOnDominoes();
        addGraphToAnchorPane();

        addTimer();
        addHighScore();
    }

    // Добавляет надпись с лучшим результатом
    private void addHighScore() {
        HighScore highScore = new HighScore(
                levelJSON.getSec(),
                Layouts.INDENT_HIGH_SCORE_RIGHT,
                Layouts.TABLE_INDENT_TOP + sizeH * Layouts.TABLE_CELL_SIZE + Layouts.INDENT_BETWEEN_TABLE_HIGH_SCORE
        );
        highScore.addToAnchorPane(anchorPane);
    }

    // Добавляет таймер
    private void addTimer() {
        timer = new CustomTimer(
                Layouts.INDENT_TIMER_LEFT,
                Layouts.TABLE_INDENT_TOP + sizeH * Layouts.TABLE_CELL_SIZE + Layouts.INDENT_BETWEEN_TABLE_TIMER
        );
        timer.addToAnchorPane(anchorPane);
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(timer::inc);
                    }
                },
                0, 1000
        );
    }

    // Инициализирует случайный уровень
    private void initRandomLevel() {
        Level level = new Level(sizeH, sizeW);
        while (!level.isValidLevel()) {
            level = new Level(sizeH, sizeW);
        }
        table = level.getLevel();
        levelDominoes = level.getDominoes();
    }

    // Загружает уровень из JSON
    private void initLevelFromJSON() {
        String path = "Levels/" + sizeH + "x" + sizeW + "/level" + levelNumber + ".json";
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        levelJSON = new Gson().fromJson(jsonString, LevelJSON.class);
    }

    // Инициализирует уровень из файла
    private void initLevel() {
        table = levelJSON.getTable();
        levelDominoes = levelJSON.getDominoes();
    }

    // Инициализируем граф
    private void initGraph() {
        graph = new Element[sizeH][sizeW];
        for (int y = 0; y < sizeH; y++) {
            for (int x = 0; x < sizeW; x++) {
                graph[y][x] = new Element(
                        Layouts.TABLE_INDENT_LEFT + x * Layouts.TABLE_CELL_SIZE,
                        Layouts.TABLE_INDENT_TOP + y * Layouts.TABLE_CELL_SIZE,
                        table[y][x]
                );
            }
        }
    }

    // Загружает уровень из json
    private void loadGraphStateFromJson() {
        ElementJSON[][] elements = levelJSON.getElements();
        if (elements != null) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[i].length; j++) {
                    if (elements[i][j] != null) {
                        graph[i][j].setElement(elements[i][j]);
                    }
                }
            }
        }
        // Закрашиваем границы
        for (int x = 0; x < sizeW; x++) {
            graph[0][x].setTop(true);
            graph[sizeH - 1][x].setBot(true);
        }
        for (int y = 0; y < sizeH; y++) {
            graph[y][0].setLeft(true);
            graph[y][sizeW - 1].setRight(true);
        }
    }

    // Добавляем clickListener на graph
    private void addClickListenerOnGraph() {
        for (int y = 0; y < sizeH; y++) {
            for (int x = 0; x < sizeW - 1; x++) {
                graph[y][x].addClickListener(graph[y][x + 1], horizontalDominoes, true);
            }
        }
        for (int y = 0; y < sizeH - 1; y++) {
            for (int x = 0; x < sizeW; x++) {
                graph[y][x].addClickListener(graph[y + 1][x], horizontalDominoes, false);
            }
        }
    }

    // Добавляет элементы графа на anchorPane
    private void addGraphToAnchorPane() {
        // Фон
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                graph[y][x].addBackgroundToAnchorPane(anchorPane);
            }
        }
        // Обычные границы
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                graph[y][x].addDefaultLinesToAnchorPane(anchorPane);
            }
        }
        // Нажатые границы
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                graph[y][x].addTouchedLinesToAnchorPane(anchorPane);
            }
        }
        // Невидимые границы
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                graph[y][x].addInvisibleLinesToAnchorPane(anchorPane);
            }
        }
        // Значение
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                graph[y][x].addLabelToAnchorPane(anchorPane);
            }
        }
    }

    // Инициализирует доминошки, которые находятся справа
    private void initDominoesAndAddToAnchorPane() {
        horizontalDominoes = new HorizontalDomino[levelDominoes.length];
        int countLines = sizeH;
        int countColumns = (int) Math.ceil( (double) levelDominoes.length / (double) countLines);
        int identLeft = (int) (Layouts.TABLE_CELL_SIZE * sizeW + Layouts.TABLE_INDENT_LEFT + Layouts.INDENT_BETWEEN_TABLE_DOMINOES);
        int iter = 0;
        for (int x = 0; x < countColumns; x++) {
            for (int y = 0; y < countLines; y++) {
                if (iter >= levelDominoes.length) break;
                Domino domino = levelDominoes[iter];
                HorizontalDomino horizontalDomino = new HorizontalDomino(
                        (int) (identLeft + x * Layouts.FULL_DOMINO_WIDTH),
                        (int) (Layouts.TABLE_INDENT_TOP + y * Layouts.DOMINO_HEIGHT + Layouts.INDENT_BETWEEN_DOMINOES / 2 * (2 * y + 1)),
                        domino.getFirst(), domino.getSecond()
                );
                horizontalDomino.setGraph(graph);
                horizontalDominoes[iter++] = horizontalDomino;
                horizontalDomino.addToAnchorPane(anchorPane);
            }
        }
    }

    // Добавляет clickListener на доминошки, которые находятся справа
    private void addClickListenerOnDominoes() {
        for (HorizontalDomino domino: horizontalDominoes) {
            domino.addClickListener(horizontalDominoes);
        }
    }

    // Запускает проверку решения
    public void startCheckSolve() {
        final boolean[] solve = {false};
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isSolve() && !solve[0]) {
                    Alert alert = initSolveAlert();
                    updateResult();
                    Platform.runLater(alert::showAndWait);
                    solve[0] = true;
                }
            }
        };
        at.start();
    }

    // Инициализирует окошко с поздравлением
    private Alert initSolveAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ты решила уровень!");
        alert.setHeaderText(null);
        alert.setContentText("Зайка, Ты правильно решила уровень и потратила на это " + timer.getTime() + ", молодец <3");
        timer.stop();
        return alert;
    }

    // Обновляет результат в json
    private void updateResult() {
        if (!levelJSON.isSolve()) {
            levelJSON.setSolve(timer.getTimeSec());
            levelJSON.rewrite(sizeH, sizeW, levelNumber);
        } else {
            if (levelJSON.getSec() > timer.getTimeSec()) {
                levelJSON.setSec(timer.getTimeSec());
                levelJSON.rewrite(sizeH, sizeW, levelNumber);
            }
        }
        isSolveNow = true;
    }

    // Сохранить состояние
    public void saveState() {
        ElementJSON[][] elements = new ElementJSON[graph.length][graph[0].length];
        // Если уровень не решен, состояние сохраняется
        if (!isSolveNow) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    Element graphElement = graph[i][j];
                    elements[i][j] = new ElementJSON(
                            graphElement.isTop(),
                            graphElement.isBot(),
                            graphElement.isLeft(),
                            graphElement.isRight()
                    );
                }
            }
        } else {
            // Если уровень решен, состояние сбрасывается
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    elements[i][j] = new ElementJSON();
                }
            }
        }
        levelJSON.setElements(elements);
        levelJSON.rewrite(sizeH, sizeW, levelNumber);
    }

    // Проверяет, решен ли уровень
    public boolean isSolve() {
        for (HorizontalDomino domino: horizontalDominoes) {
            if (domino.getCount() != 1) {
                return false;
            }
        }
        return true;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public int getWidth() {
        int countLines = sizeH;
        int countColumns = (int) Math.ceil( (double) levelDominoes.length / (double) countLines);
        return (int) (Layouts.TABLE_INDENT_LEFT +               // Отступ слева от таблицы
                sizeW * Layouts.TABLE_CELL_SIZE +               // Ширина таблицы
                Layouts.INDENT_BETWEEN_TABLE_DOMINOES +         // Расстоние между таблицей и доминошками
                Layouts.FULL_DOMINO_WIDTH * countColumns);      // Длина всех доминошек
    }

    public int getHeight() {
        return (int) (Layouts.TABLE_INDENT_TOP +                // Отступ сверху от таблицы
                sizeH * Layouts.TABLE_CELL_SIZE +               // Высота таблицы
                Layouts.INDENT_BETWEEN_TABLE_TIMER +            // Расстояние между таблицей и таймером
                LabelConfigurator.TEXT_SIZE +                   // Высота текста
                Layouts.INDENT_TIMER_BOT);                      // Отступ снизу от таймера
    }
}
