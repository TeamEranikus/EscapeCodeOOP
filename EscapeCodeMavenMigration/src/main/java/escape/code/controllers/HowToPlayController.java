package escape.code.controllers;

import escape.code.core.StageManager;
import escape.code.utils.Constants;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HowToPlayController {
    @FXML
    private Button startGame;

    @FXML
    private Button backToGame;

    private Stage currentStage;
    private FXMLLoader fxmlLoader;
    private ObservableMap<String,Object> fxmlObjects;
    private StageManager stageManager;

    public void startGame(ActionEvent event) throws IOException {
        /*currentStage = (Stage) startGame.getScene().getWindow();
        stageManager = new StageManager();
        fxmlLoader = stageManager.loadSceneToPrimaryStage(currentStage, Constants.DEMO_LEVEL_FXML_PATH);
        fxmlObjects = fxmlLoader.getNamespace();
        ImageView playerImage = (ImageView) fxmlObjects.get("imagePlayer");
        ResizableCanvas canvas = (ResizableCanvas) fxmlObjects.get("mainCanvas");
        Sprite sprite = new Sprite(playerImage, canvas);
        Engine engine = new Engine(fxmlLoader);
        AnchorPane anchorPane = (AnchorPane)fxmlObjects.get("anchorPane");
       // engine.run(currentStage.getScene());*/
    }
    //TODO use stageManager
    public void  backToMenu(ActionEvent event) throws IOException {
        currentStage = (Stage) backToGame.getScene().getWindow();
        stageManager = new StageManager();
        stageManager.loadSceneToPrimaryStage(currentStage,Constants.MENU_FXML_PATH);
    }
}
