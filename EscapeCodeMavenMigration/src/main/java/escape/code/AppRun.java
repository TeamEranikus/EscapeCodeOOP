package escape.code;

import escape.code.core.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppRun extends Application {

    @Override
    public void start(Stage primaryStage){
        //StageManager stageManager = new StageManager();
        //FXMLLoader loader = stageManager.loadSceneToPrimaryStage(primaryStage, Constants.LOGIN_FXML_PATH);

        String[][] taskParams = {
                {"-What the following code will print?", "piano", "Do you really need the numbers?", "What could it be??", "/pictures/ComputerTaskWhite.png", "0"},
                {"-One piano button is stuck!", "13", "How will you EXit?", "You are one step away from the exit.", "/pictures/PianoTask.jpg", "0"},
                {"-The way to unlock the door is to know the secret number.", "111", "Do you see something unusual?", "The door is open now.", "/pictures/LibraryWithJoker.jpg", "0"}
        };
        //PuzzleService puzzleService = new PuzzleServiceImpl();
        //Arrays.stream(taskParams).forEach(puzzleService::createPuzzle);
        Game game = new Game(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
