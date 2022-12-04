package code.commands;
import code.email.Email;
import code.insurance.BasicInsurance;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class SaveCommand {

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    public void execute(List<BasicInsurance> derevative, File filePath){

        saveFile(derevative, filePath);
        logger.info("Збереження в файл");

    }

    public void saveFile(List<BasicInsurance> derevative, File filePath){

        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println("Дериватив:");

            for(int i = 0; i < derevative.size(); i++) {
                out.print(i+1 + ". ");
                out.println(derevative.get(i));
            }
        }catch (IOException e) {
            Email.sendEmailLetter(e.toString());
        }
    }
}
