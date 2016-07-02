package escape.code;

import escape.code.core.StageManager;
import escape.code.services.puzzleService.PuzzleService;
import escape.code.services.puzzleService.PuzzleServiceImpl;
import escape.code.utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

public class AppRun extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageManager stageManager = new StageManager();
        stageManager.setPrimaryStage(primaryStage,Constants.MENU_FXML_PATH);

        String[][] taskParams = {
                { "-What the following code will print?", "piano", "Do you really need the numbers?", "What could it be??", "/pictures/ComputerTaskWhite.png", "zero" },
                { "-One piano button is stuck!", "13", "How will you EXit?", "You are one step away from the exit.", "/pictures/PianoTask.jpg","zero" },
                { "-The way to unlock the door is to know the secret number.", "111", "Do you see something unusual?", "The door is open now.", "/pictures/LibraryWithJoker.jpg", "zero" }
        };

        PuzzleService puzzleService = new PuzzleServiceImpl();
        Arrays.stream(taskParams).forEach(puzzleService::createPuzzle);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
