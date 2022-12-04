package code.main;


import code.email.Email;
import code.insurance.BasicInsurance;
import code.logger.MyLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main extends Application {

    public static List<BasicInsurance> derevative = new ArrayList<>(10);
    public static Image icon = new Image("/resources/icon/icon.png");

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void start(Stage stage) throws IOException {

        try{

            MyLogger.setupLogger();
            logger.info("Початок роботи програми");

            Parent root = FXMLLoader.load(getClass().getResource("/resources/scenes/MainScene.fxml"));
            Scene scene = new Scene(root);
            stage.setResizable(false);

            stage.getIcons().add(icon);
            stage.setTitle("Дериватив страхових зобов'язань");

            String css = this.getClass().getResource("/resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}