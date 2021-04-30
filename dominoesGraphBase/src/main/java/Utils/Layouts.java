package Utils;

public class Layouts {

    public final static int TABLE_CELL_SIZE = 40;
    public final static int TABLE_INDENT_TOP = 10;
    public final static int TABLE_INDENT_LEFT = 10;

    // ----------- LINES -----------
    public final static double DEFAULT_VISIBLE_LINE_WIDTH = 2.0;
    public final static double TOUCHED_VISIBLE_LINE_WIDTH = 4.0;
    public final static double INVISIBLE_LINE_WIDTH = 20.0;

    // ----------- DOMINO -----------
    public final static double DOMINO_WIDTH = TABLE_CELL_SIZE * 1.5;
    public final static double DOMINO_HEIGHT = TABLE_CELL_SIZE * 0.75;
    public final static double FULL_DOMINO_WIDTH = DOMINO_WIDTH + DOMINO_WIDTH * 0.3;

    public final static double INDENT_BETWEEN_TABLE_DOMINOES = TABLE_CELL_SIZE / 2;
    public final static double INDENT_BETWEEN_DOMINOES = TABLE_CELL_SIZE - DOMINO_HEIGHT;

    // ----------- TIMER -----------
    public final static double INDENT_BETWEEN_TABLE_TIMER = TABLE_INDENT_TOP;
    public final static double INDENT_TIMER_LEFT = TABLE_INDENT_LEFT;
    public final static double INDENT_TIMER_BOT = INDENT_BETWEEN_TABLE_TIMER;

    // ----------- HIGH SCORE -----------
    public final static double INDENT_HIGH_SCORE_RIGHT = TABLE_INDENT_LEFT;
    public final static double INDENT_BETWEEN_TABLE_HIGH_SCORE = TABLE_INDENT_TOP;



}
