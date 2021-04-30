import Drawable.CustomAnchorPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        int sizeH = 10;
        int sizeW = 10;
        int levelNumber = 1;

        CustomAnchorPane customAnchorPane = new CustomAnchorPane(sizeH, sizeW, levelNumber);
        customAnchorPane.startCheckSolve();
        Scene scene = new Scene(customAnchorPane.getAnchorPane(), customAnchorPane.getWidth(), customAnchorPane.getHeight());
        stage.setScene(scene);
        stage.setTitle("Dominoes");
        stage.show();

        stage.setOnCloseRequest(event -> {
            customAnchorPane.saveState();
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
