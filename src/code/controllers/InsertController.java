package code.controllers;

import code.insurance.CarInsurance;
import code.insurance.EstateInsurance;
import code.insurance.MedicalInsurance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import code.main.Main;

import java.util.logging.Logger;

public class InsertController{

    @FXML
    private RadioButton radioButton1, radioButton2,radioButton3;
    @FXML
    private TextField textField1, textField2, textField3, textField4;
    @FXML
    Label errorText;
    @FXML
    Button insertButton;

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    public void changeVarSign(ActionEvent event){
        textField3.setPromptText("Введіть рівень ризику у відсотках");
        textField4.setPromptText("Введіть суму очікуваної компенсації");

        if (radioButton1.isSelected()){
            textField1.setPromptText("Введіть своє ПІБ");
            textField2.setPromptText("Введіть свій вік");

        }
        else if (radioButton2.isSelected()){
            textField1.setPromptText("Введіть локація нерухомості");
            textField2.setPromptText("Введіть площу");

        }
        else if (radioButton3.isSelected()){
            textField1.setPromptText("Введіть модель машини");
            textField2.setPromptText("Введіть пробіг в кілометрах");
        }
    }

    public boolean insertMed(){
        try{
            String name = textField1.getText();
            int age = Integer.parseInt(textField2.getText());
            double risk = Double.parseDouble(textField3.getText());
            double expComp =  Double.parseDouble(textField4.getText());

            MedicalInsurance insurance = (MedicalInsurance) MedicalInsurance
                    .builder()
                    .name(name)
                    .age(age)
                    .risk(risk)
                    .expCompens(expComp)
                    .build();

            Main.derevative.add(insurance);
            return true;

        }catch (NumberFormatException e) {
            errorText.setText("Дані введено неправильно");
            return false;
        }

    }
    public boolean insertCar(){
        try{
            String model = textField1.getText();
            double kmRun = Double.parseDouble(textField2.getText());
            double risk = Double.parseDouble(textField3.getText());
            double expComp =  Double.parseDouble(textField4.getText());

            CarInsurance insurance = (CarInsurance) CarInsurance
                    .builder()
                    .model(model)
                    .kmrun(kmRun)
                    .risk(risk)
                    .expCompens(expComp)
                    .build();

            Main.derevative.add(insurance);
            return true;

        }catch (NumberFormatException e) {
            errorText.setText("Дані введено неправильно");
            return false;
        }
    }
    public boolean insertEstate(){

        try{
            String location = textField1.getText();
            double area = Double.parseDouble(textField2.getText());
            double risk = Double.parseDouble(textField3.getText());
            double expComp =  Double.parseDouble(textField4.getText());

            EstateInsurance insurance = (EstateInsurance) EstateInsurance.builder()
                    .location(location)
                    .area(area)
                    .risk(risk)
                    .expCompens(expComp)
                    .build();

            Main.derevative.add(insurance);
            return true;
        }catch (NumberFormatException e) {
            errorText.setText("Дані введено неправильно");
            return false;
        }
    }

    public void insert(ActionEvent event) {

        boolean isInserted = false;
        if (radioButton1.isSelected()){
            isInserted = insertMed();
        }
        else if (radioButton2.isSelected()){
            isInserted = insertEstate();
        }
        else if (radioButton3.isSelected()){
            isInserted = insertCar();
        }

        if(isInserted){
            logger.info("Вставлення зобов'язання");
            goBack(event);
        }
    }


    public void goBack(ActionEvent event){

        new MainController().returnToMain(event);
    }

}
