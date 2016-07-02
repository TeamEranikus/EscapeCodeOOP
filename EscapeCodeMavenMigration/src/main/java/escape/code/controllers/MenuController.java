package escape.code.controllers;

import escape.code.core.Engine;
import escape.code.core.ResizableCanvas;
import escape.code.core.StageManager;
import escape.code.models.Sprite;
import escape.code.utils.Constants;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    // Fields must be private with FXML annotations
    @FXML
    private Button newGameButton;

    @FXML
    private Button howToPlayButton;

    @FXML
    private Button quitButton;

    private Stage currentStage;
    private FXMLLoader fxmlLoader;
    private ObservableMap<String,Object> fxmlObjects;
    private StageManager stageManager;

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void onNewGameClicked(ActionEvent event) throws IOException {
        currentStage = (Stage) newGameButton.getScene().getWindow();
        stageManager = new StageManager();
        fxmlLoader = stageManager.loadSceneToPrimaryStage(currentStage, Constants.DEMO_LEVEL_FXML_PATH);
        fxmlObjects = fxmlLoader.getNamespace();
        ImageView playerImage = (ImageView) fxmlObjects.get("imagePlayer");
        ResizableCanvas canvas = (ResizableCanvas) fxmlObjects.get("mainCanvas");
        Sprite sprite = new Sprite(playerImage, canvas);
        Engine engine = new Engine(sprite);
        AnchorPane anchorPane = (AnchorPane)fxmlObjects.get("anchorPane");
        engine.loadRectanglesPuzzles(anchorPane);
        engine.loadRectanglesCollision(anchorPane);
        engine.setCurrentLoadedStage(currentStage);
        engine.run(currentStage.getScene());
    }

    public void onHowToPlayClicked(ActionEvent event) throws IOException {
        currentStage = (Stage)howToPlayButton.getScene().getWindow();
        stageManager = new StageManager();
        stageManager.loadSceneToPrimaryStage(currentStage,Constants.HOW_TO_PLAY_FXML_PATH);
    }

    public void onQuitClicked(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
        // should call confirm box? "Are you sure?"
    }
}
