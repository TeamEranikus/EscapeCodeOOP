package escape.code.core;

import escape.code.enums.Level;
import escape.code.models.User;
import escape.code.services.userservices.UserService;
import escape.code.services.userservices.UserServiceImpl;
import escape.code.utils.Constants;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class Game {
    private static User user;
    private static Engine engine;
    private static FXMLLoader fxmlLoader;
    private static StageManager stageManager;
    private static AnimationTimer timeline;
    private static UserService userService;
    private static Stage currentStage;

    public Game(Stage stage) {
        currentStage = stage;
        stageManager = new StageManager();
        userService = new UserServiceImpl();
        login();
    }

    public static void run() {
        fxmlLoader = stageManager.loadSceneToPrimaryStage(currentStage, Level.getByNum(user.getLevel()).getPath());
        engine = new Engine(fxmlLoader, user);
        timeline = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    engine.play();
                } catch (IllegalStateException e) {
                    timeline.stop();
                    try {
                        user.setLevel(user.getLevel() + 1);
                        //TODO pop - up to ask do you want to continue
                        stageManager.loadNewStage(Level.getByNum(user.getLevel()).getPath());
                        userService.updateUser(user);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        timeline.start();
    }

    public static void setUser(User currentUser) {
        user = currentUser;
    }

    public static void loadMainMenu() {
        fxmlLoader = stageManager.loadSceneToPrimaryStage(currentStage, Constants.MENU_FXML_PATH);
    }

    private void login(){
        fxmlLoader = stageManager.loadSceneToPrimaryStage(currentStage,Constants.LOGIN_FXML_PATH);
    }
}
