package escape.code.core;

import escape.code.models.Sprite;
import escape.code.models.User;
import escape.code.utils.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Engine {

    private PuzzleManager puzzleManager;
    private HashMap<KeyCode, Boolean> keys;
    private LinkedList<Rectangle> rectangles;
    private ArrayList<Rectangle> rectCollision;
    private Rectangle currentPuzzle;
    private boolean hasCol = false;
    private boolean hasToSetPuzzle = true;
    private Sprite sprite;
    private StageManager stageManager;
    private Stage currentLoadedStage;
    private FXMLLoader loader;
    private User user;

    private AudioClip iSound0, iSound1, iSound2, iSound3, iSoundBackground;

    private URL iAudioFile0, iAudioFile1, iAudioFile2, iAudioFile3, iAudioFileBackground;

    public Engine(FXMLLoader loader, User user) {
        this.loader = loader;
        this.user = user;
        this.initialize();
    }

    private void initialize() {
        this.puzzleManager = new PuzzleManager();
        this.keys = new HashMap<>();
        this.rectangles = new LinkedList<>();
        this.rectCollision = new ArrayList<>();
        this.stageManager = new StageManager();
        ImageView playerImage = (ImageView) this.loader.getNamespace().get("imagePlayer");
        ResizableCanvas canvas = (ResizableCanvas) this.loader.getNamespace().get("mainCanvas");
        this.sprite = new Sprite(playerImage, canvas);
        this.loadRectanglesPuzzles();
        this.loadRectanglesCollision();

        Scene scene = ((Pane) this.loader.getRoot()).getScene();
        this.currentLoadedStage = (Stage) (scene.getWindow());
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        puzzleManager.load(user.getLevel());
    }

    public void play() throws IllegalStateException {
        updateSpriteCoordinates();
        hasCol = checkForCol(currentPuzzle);

        if (hasCol) {
            setCurrentPuzzle();

            sprite.getImageView().setLayoutX(480.0);
            sprite.getImageView().setLayoutY(300.0);

        }
        if (Constants.IS_ANSWER_CORRECT) {
            currentPuzzle.setVisible(false);
            currentPuzzle.setDisable(true);
            rectangles.removeFirst();
            currentPuzzle = getCurrentPuzzleRectangle();
            Constants.IS_ANSWER_CORRECT = false;
            hasToSetPuzzle = true;
        }
    }


    private void setCurrentPuzzle() throws IllegalStateException {
        if (!currentPuzzle.getId().contains("door")) {
            try {
                if (hasToSetPuzzle) {
                    hasToSetPuzzle = false;
                    puzzleManager.setPuzzle();
                    user.setHasBook(true);
                    //TODO pop-up
                }
                stageManager.loadNewStage(Constants.PUZZLE_FXML_PATH);
                keys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            currentLoadedStage.close();
            throw new IllegalStateException("STOP");
        }
    }

    private void updateSpriteCoordinates() {
        if (isPressed(KeyCode.UP)) {
            sprite.moveY(-2);
            checkBounds("U");
        } else if (isPressed(KeyCode.DOWN)) {
            sprite.moveY(2);
            checkBounds("D");
        } else if (isPressed(KeyCode.RIGHT)) {
            sprite.moveX(2);
            checkBounds("R");
        } else if (isPressed(KeyCode.LEFT)) {
            sprite.moveX(-2);
            checkBounds("L");
        }
        //playAudioClip();
    }

    private void checkBounds(String direction) {
        for (Rectangle rectangle : rectCollision) {
            if (checkForCol(rectangle)) {
                switch (direction) {
                    case "U":
                        sprite.getImageView().setLayoutY(sprite.getImageView().getLayoutY() + 2);
                        break;
                    case "D":
                        sprite.getImageView().setLayoutY(sprite.getImageView().getLayoutY() - 2);
                        break;
                    case "R":
                        sprite.getImageView().setLayoutX(sprite.getImageView().getLayoutX() - 2);
                        break;
                    case "L":
                        sprite.getImageView().setLayoutX(sprite.getImageView().getLayoutX() + 2);
                        break;
                }
            }
        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    //TODO union
    private void loadRectanglesPuzzles() {
        ObservableList<Node> listOfAllElements = ((Pane) loader.getRoot()).getChildren();
        for (Node element : listOfAllElements) {
            if (element != null && element.getId().endsWith("Puzzle")) {
                Rectangle current = (Rectangle) element;
                current.setDisable(true);
                current.setVisible(false);
                rectangles.add(current);
            }
        }
        rectangles = rectangles.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .collect(Collectors.toCollection(LinkedList<Rectangle>::new));
        currentPuzzle = getCurrentPuzzleRectangle();
    }

    private void loadRectanglesCollision() {
        ObservableList<Node> listOfAllElements = ((Pane) loader.getRoot()).getChildren();
        for (Node element : listOfAllElements) {
            if (element != null && element.getId().endsWith("Col")) {
                Rectangle current = (Rectangle) element;
                rectCollision.add(current);
            }
        }

    }

    private Rectangle getCurrentPuzzleRectangle() {
        if (rectangles.size() > 0) {
            Rectangle current = rectangles.peekFirst();
            current.setVisible(true);
            current.setDisable(false);
            return current;
        }
        return new Rectangle();
    }

    private boolean checkForCol(Rectangle current) {
        if (current.isDisabled()) {
            return false;
        }
        return current.getBoundsInParent().intersects(sprite.getImageView().getBoundsInParent());
    }

    //TODO EXTRACT NEW CLASS AUDIO MANAGER
    private void playAudioClip() {
        //loadAudioAssets();
        if (isPressed(KeyCode.UP)) {
            iSound2.play();
        }
        if (isPressed(KeyCode.DOWN)) {
            iSound3.play();
        }
        if (isPressed(KeyCode.LEFT)) {
            iSound0.play();
        }
        if (isPressed(KeyCode.RIGHT)) {
            iSound1.play();
        }
        iSoundBackground.setVolume(0.05);
        iSoundBackground.play();
    }

    private void loadAudioAssets() {
        iAudioFile0 = getClass().getResource("../data/sounds/leftmono.wav");
        iSound0 = new AudioClip(iAudioFile0.toString());
        iAudioFile1 = getClass().getResource("../data/sounds/rightmono.wav");
        iSound1 = new AudioClip(iAudioFile1.toString());
        iAudioFile2 = getClass().getResource("../data/sounds/upmono.wav");
        iSound2 = new AudioClip(iAudioFile2.toString());
        iAudioFile3 = getClass().getResource("../data/sounds/downmono.wav");
        iSound3 = new AudioClip(iAudioFile3.toString());
        iAudioFileBackground = getClass().getResource("../data/sounds/snd_music.mp3");
        iSoundBackground = new AudioClip(iAudioFileBackground.toString());
    }


}
