package code.commands;

import code.email.Email;
import code.insurance.*;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class ReadDBCommand{

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    public void execute(List<BasicInsurance> derevative) {

        String dbCon = "jdbc:sqlserver://localhost\\SQLEXPRESS:49683;databaseName=insurance;encrypt=true;trustServerCertificate=true";

        try{
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Connection connection = DriverManager.getConnection(dbCon, "test_owner", "1111");
            Statement statement = connection.createStatement();

            readDBCarInsurance(derevative, statement);
            readDBMedicalInsurance(derevative, statement);
            readDBEstateInsurance(derevative, statement);

            logger.info("Зчитування з бази даних");

            connection.close();
        }
        catch(Exception e){
            Email.sendEmailLetter(e.toString());
        }
    }

    public void readDBMedicalInsurance(List<BasicInsurance> derevative,Statement statement){
        try {
            String selectSql = "Select * from medicalInsurance";

            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                MedicalInsurance insurance = (MedicalInsurance) MedicalInsurance
                        .builder()
                        .name(resultSet.getString(1))
                        .age(resultSet.getInt(2))
                        .risk(resultSet.getDouble(3))
                        .expCompens(resultSet.getDouble(4))
                        .build();
                derevative.add(insurance);
            }
            resultSet.close();
        }catch(Exception e){
            Email.sendEmailLetter(e.toString());
        }
    }


    public void readDBCarInsurance(List<BasicInsurance> derevative,Statement statement){
        try {
            String selectSql = "Select * from carInsurance";

            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {

                    CarInsurance insurance = (CarInsurance) CarInsurance
                            .builder()
                            .model(resultSet.getString(1))
                            .kmrun(resultSet.getDouble(2))
                            .risk(resultSet.getDouble(3))
                            .expCompens(resultSet.getDouble(4))
                            .build();
                    derevative.add(insurance);


            }
            resultSet.close();
        }
        catch(Exception e){
            Email.sendEmailLetter(e.toString());
        }
    }

    public void readDBEstateInsurance(List<BasicInsurance> derevative,Statement statement){
        try{
            String selectSql = "Select * from estateInsurance";

            ResultSet resultSet = statement.executeQuery(selectSql);
            while(resultSet.next()) {

                EstateInsurance insurance = (EstateInsurance) EstateInsurance
                        .builder()
                        .location(resultSet.getString(1))
                        .area(resultSet.getDouble(2))
                        .risk(resultSet.getDouble(3))
                        .expCompens(resultSet.getDouble(4))
                        .build();
                derevative.add(insurance);
            }
            resultSet.close();
        } catch(Exception e){
            Email.sendEmailLetter(e.toString());
        }
    }


}
