package Utils;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LabelConfigurator {

    private final static FontLoader FONT_LOADER = Toolkit.getToolkit().getFontLoader();
    public final static int TEXT_SIZE = 16;

    public static void configureLabelFontAndPos(Label label, int x, int y) {
        label.setFont(Font.font("Consolas", FontWeight.THIN, FontPosture.REGULAR, TEXT_SIZE));
        float labelWidth = FONT_LOADER.computeStringWidth(label.getText(), label.getFont());
        AnchorPane.setLeftAnchor(label, (double) (x - labelWidth / 2));
        AnchorPane.setTopAnchor(label, (double) (y - TEXT_SIZE / 2));
    }

    public static void configureLabelFont(Label label) {
        label.setFont(Font.font("Consolas", FontWeight.THIN, FontPosture.REGULAR, TEXT_SIZE));
    }
}
