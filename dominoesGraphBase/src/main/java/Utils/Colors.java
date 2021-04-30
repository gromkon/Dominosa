package Utils;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Colors {

    private static int[] randomR = {118, 76, 12, 173, 67, 35, 246, 17, 7, 70, 127, 51, 49, 53, 197, 25, 42, 80, 179, 19, 100};
    private static int[] randomG = {10, 200, 141, 14, 206, 179, 98, 253, 180, 40, 208, 172, 161, 30, 157, 213, 49, 154, 236, 128, 100};
    private static int[] randomB = {47, 171, 159, 96, 239, 208, 75, 169, 202, 234, 104, 144, 203, 240, 136, 179, 160, 123, 115, 209, 100};

    private static Paint[] colors = {
            Color.rgb(0, 0, 0),         // 1
            Color.rgb(255, 0, 0),       // 2
            Color.rgb(102, 204, 153),   // 3
            Color.rgb(0, 153, 255),     // 4
            Color.rgb(0, 102, 51),      // 5
            Color.rgb(102, 255, 255),   // 6
            Color.rgb(153, 153, 153),   // 7
            Color.rgb(252, 102, 0),     // 8
            Color.rgb(204, 51, 153),    // 9
            Color.rgb(255, 102,255),    // 10
            Color.rgb(51, 102,102),     // 11
            Color.rgb(0, 51,255),       // 12
            Color.rgb(0, 255,0),        // 13
            Color.rgb(255, 0,255),      // 14
            Color.rgb(51, 51,51),       // 15
            Color.rgb(255, 204,102),    // 16
            Color.rgb(204, 102,102),    // 17
            Color.rgb(102, 51,255),     // 18
            Color.rgb(102, 0,0),        // 19
            Color.rgb(0, 255,102),      // 20
            Color.rgb(153, 153,0),      // 21
    };

    // Цвет цифры на поле и в доминошке
    public static Paint getValueColor(int value) {
//        return Color.rgb(randomR[value - 1], randomG[value - 1], randomB[value - 1]);
        while (value > 21) {
            value -= 21;
        }
        return colors[value - 1];
    }

    // Цвет цифры доминошки
    public static Paint getDominoValueColor(int value) {
        return Color.BLACK;
    }

    // Цвет подстветки на поле
    public static Paint getBackgroundColor() {
        return Color.YELLOW;
    }

    // Цвет нажатой линии
    public static Paint getTouchedLineColor() {
        return Color.rgb(50, 50, 100);
    }

    // Цвет не нажатой линии
    public static Paint getNotTouchedLineColor() {
        return Color.rgb(200, 200, 200);
    }

    // Цвет невидимой линии
    public static Paint getInvisibleLineColor() {
        return Color.rgb(50, 0, 0, 0.00);
    }

    // Стандартный фоновый цвет доминошки
    public static Paint getDominoDefaultBackgroundColor() {
        return Color.WHITE;
    }

    // 0 решений, фоновый цвет доминошки
    public static Paint getDominoZeroBackgroundColor() {
        return Color.RED;
    }

    // 1 решение, которое обведено, фоновый цвет доминошки
    public static Paint getDominoSolveBackgroundColor() {
        return Color.rgb(121, 200, 123);
    }

    // 2 или более решений, причем одно которое обведено, фоновый цвет доминошки
    public static Paint getDominoAlmostSolveBackgroundColor() {
        return Color.YELLOW;
    }

    // Цвет границ доминошки
    public static Paint getDominoBordersColor() {
        return Color.BLACK;
    }

    // Цвет средней линии доминошки
    public static Paint getDominoLineColor() {
        return getNotTouchedLineColor();
    }

    // Цвет каунтера доминошки
    public static Paint getDominoDefaultCountColor() {
        return Color.BLACK;
    }

    // Цвет таймера
    public static Paint getTimerTextColor() {
        return Color.BLACK;
    }

    // Цвет high score
    public static Paint getHighScoreTextColor() {
        return Color.BLACK;
    }

}
