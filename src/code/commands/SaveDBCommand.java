package code.commands;

import code.email.Email;
import code.insurance.*;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class SaveDBCommand{

    String dbCon = "jdbc:sqlserver://localhost\\SQLEXPRESS:49683;databaseName=insurance;encrypt=true;trustServerCertificate=true";

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    public void execute(List<BasicInsurance> derevative) {

            try{

                DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                Connection connection = DriverManager.getConnection(dbCon, "test_owner", "1111");


                for (BasicInsurance el : derevative) {

                    if(el.getInsuranceType().equals("medical")){
                        saveDBMedicalInsurance(el, connection);
                    }
                    if (el.getInsuranceType().equals("car")){
                        saveDBCarInsurance(el, connection);
                    }
                    if (el.getInsuranceType().equals("estate")) {
                        saveDBEstateInsurance(el, connection);
                    }
                }

                logger.info("Збереження в базу даних");

                connection.close();
            }
            catch(Exception e){
                Email.sendEmailLetter(e.toString());
            }

    }

    public void saveDBEstateInsurance(BasicInsurance el, Connection connection){

        try {
            String sql = " insert into estateInsurance (Location, Area, Risk, [Expected Compensation], Price)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString  (1,     ((EstateInsurance) el).getLocation());
            preparedStmt.setDouble  (2,     ((EstateInsurance) el).getArea());
            preparedStmt.setDouble  (3,     (el).getRisk());
            preparedStmt.setDouble  (4,     (el).getExpCompens());
            preparedStmt.setDouble  (5,     (el).getPrice());

            preparedStmt.execute();
            preparedStmt.close();

        } catch (Exception e) {
            Email.sendEmailLetter(e.toString());
        }
    }

    public void saveDBCarInsurance(BasicInsurance el, Connection connection){

        try {
            String sql = " insert into carInsurance (Model, KmRun, Risk, [Expected Compensation], Price)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString  (1,     ((CarInsurance) el).getModel());
            preparedStmt.setDouble  (2,     ((CarInsurance) el).getKmrun());
            preparedStmt.setDouble  (3,     (el).getRisk());
            preparedStmt.setDouble  (4,     (el).getExpCompens());
            preparedStmt.setDouble  (5,     (el).getPrice());

            preparedStmt.execute();
            preparedStmt.close();

        } catch (Exception e) {
            Email.sendEmailLetter(e.toString());
        }
    }


    public void saveDBMedicalInsurance(BasicInsurance el, Connection connection){

        try {
            String sql = " insert into medicalInsurance (Name, Age, Risk, [Expected Compensation], Price)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString  (1,     ((MedicalInsurance) el).getName());
            preparedStmt.setInt     (2,     ((MedicalInsurance) el).getAge());
            preparedStmt.setDouble  (3,     (el).getRisk());
            preparedStmt.setDouble  (4,     (el).getExpCompens());
            preparedStmt.setDouble  (5,     (el).getPrice());

            preparedStmt.execute();
            preparedStmt.close();

        } catch (Exception e) {
            Email.sendEmailLetter(e.toString());
        }
    }

}
