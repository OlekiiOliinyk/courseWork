package code.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import code.main.Main;

import java.util.logging.Logger;

public class DeleteController {

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    @FXML
    private TextField deleteField;

    public void delete(ActionEvent event){

        try {

            int index = Integer.parseInt(deleteField.getText());

            for(int i = 0; i < Main.derevative.size(); i++) {

                if (i == index-1){
                    Main.derevative.remove(i);
                }
            }
            logger.info("Видалення зобов'язання");

            new MainController().returnToMain(event);
        }catch(NumberFormatException e){}


    }

    public void goBack(ActionEvent event){
        new MainController().returnToMain(event);
    }
}
