package code.controllers;

import code.insurance.BasicInsurance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import code.main.Main;

import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {

    @FXML
    private ChoiceBox<String> typeBox;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> searchList;

    private String[] types = {"Компенсація", "Ризик", "Ціна"};

    private  List<BasicInsurance> searchedInsurance = new ArrayList<>(10);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeBox.getItems().addAll(types);
    }

    public void find(double val, String type){

        switch (type) {
            case "Компенсація":

                for (BasicInsurance el : Main.derevative) {

                    if (el.getExpCompens() == val) {
                        searchedInsurance.add(el);
                    }
                }

                break;
            case "Ризик":

                for (BasicInsurance el : Main.derevative) {

                    if (el.getRisk() == val) {
                        searchedInsurance.add(el);
                    }
                }

                break;
            case "Ціна":

                for (BasicInsurance el : Main.derevative) {

                    if (el.getPrice() == val) {
                        searchedInsurance.add(el);
                    }
                }

                break;
        }

        updateOutput(searchedInsurance, searchList);
    }

    public void search(ActionEvent event){
        try{
            String type = typeBox.getValue();
            double value = Double.parseDouble(searchField.getText());
            find(value, type);

        }catch(NumberFormatException e){}

    }

    public void updateOutput(List<BasicInsurance> derevative, ListView<String> list){

        ArrayList<String> arr = new ArrayList<String>();

        String info;

        list.getItems().clear();
        for(int i = 0; i < derevative.size(); i++) {

            info = derevative.get(i).toString() + "\n";
            arr.add(info);
        }
        list.getItems().addAll(arr);
    }

    public void goBack(ActionEvent event){
        new MainController().returnToMain(event);
    }

}
