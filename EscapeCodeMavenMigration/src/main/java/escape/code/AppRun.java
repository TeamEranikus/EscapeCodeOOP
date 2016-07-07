package escape.code;

import escape.code.core.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppRun extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}