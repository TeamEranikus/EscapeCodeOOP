package escape.code;

import escape.code.core.StageManager;
import escape.code.daos.userdaos.UserDao;
import escape.code.daos.userdaos.UserDaoImpl;
import escape.code.utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppRun extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StageManager stageManager = new StageManager();
        stageManager.setPrimaryStage(primaryStage,Constants.MENU_FXML_PATH);
        UserDao userDao = new UserDaoImpl();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
