package code.controllers;

import code.commands.*;
import code.email.Email;
import code.insurance.BasicInsurance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import code.main.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    @FXML
    private ListView<String> derevativeList;
    @FXML
    private Label sumLabel;

    @FXML
    private Label labelList;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateOutput(Main.derevative, derevativeList);
    }


    public void saveToFile(ActionEvent event){
        logger.info("Збереження в файл");
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT File", "*txt"));
            File file = fc.showOpenDialog(stage);
            SaveCommand saveCommand = new SaveCommand();
            saveCommand.execute(Main.derevative, file);
        }catch (Exception e){
            Email.sendEmailLetter(e.toString());
        }
    }

    public void saveToDB(ActionEvent event){

            SaveDBCommand saveDBCommand = new SaveDBCommand();
            saveDBCommand.execute(Main.derevative);

    }

    public void readDB(ActionEvent event){



            ReadDBCommand readDBCommand = new ReadDBCommand();
            readDBCommand.execute(Main.derevative);
            updateOutput(Main.derevative, derevativeList);

    }

    public void readFile(ActionEvent event){

        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT File", "*txt"));
            File file = fc.showOpenDialog(stage);
            ReadFileCommand readfileCommand = new ReadFileCommand();
            readfileCommand.execute(Main.derevative, file);
            updateOutput(Main.derevative, derevativeList);
        }catch (Exception e){
            Email.sendEmailLetter(e.toString());
            e.printStackTrace();
        }
    }

    public void insert(ActionEvent event){

        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/scenes/InsertScene.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.getIcons().add(Main.icon);
            stage.setTitle("Вставлення страхового зобов'язання");

            String css = this.getClass().getResource("/resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sort(ActionEvent event){

        logger.info("Сортування деривативу");
        SortCommand sortCommand = new SortCommand();
        sortCommand.execute(Main.derevative);
        updateOutput(Main.derevative, derevativeList);

    }

    public void delete(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/scenes/DeleteScene.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.getIcons().add(Main.icon);
            stage.setTitle("Видалення страхового зобов'язання");

            String css = this.getClass().getResource("/resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void search(ActionEvent event){

        try {

            logger.info("Пошук зобов'язання");

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/scenes/SearchScene.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.getIcons().add(Main.icon);
            stage.setTitle("Пошук страхового зобов'язання");

            String css = this.getClass().getResource("/resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent event){
        logger.info("Вихід з програми");
        System.exit(0);
    }

    public void returnToMain(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/scenes/MainScene.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setTitle("Дериватив страхових зобов'язань");
            String css = this.getClass().getResource("/resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateOutput(List<BasicInsurance> derevative, ListView<String> list){

        ArrayList<String> arr = new ArrayList<String>();
        String info;
        double sum = 0;

        list.getItems().clear();
        for(int i = 0; i < derevative.size(); i++) {
            sum += derevative.get(i).getPrice();

            String num = String.format("%5d", i+1);

            info = num + ". "+ derevative.get(i).toString() + "\n";
            arr.add(info);
        }
        list.getItems().addAll(arr);
        sumLabel.setText("Загальна ціна: " + Math.round(sum));
    }
}
