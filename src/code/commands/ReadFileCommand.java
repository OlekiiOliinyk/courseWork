package code.commands;

import code.commands.scan.ScanMenu;
import code.email.Email;
import code.insurance.BasicInsurance;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class ReadFileCommand {

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    public void execute(List<BasicInsurance> derevative, File filepath) {

        try{

            Scanner fileScan = new Scanner(filepath);

            scanFileInfo(derevative, fileScan);

            logger.info("Зчитування з файлу");
            fileScan.close();
        }
        catch (IOException ex) {

            Email.sendEmailLetter(ex.toString());

        }
    }


    public void scanFileInfo(List<BasicInsurance> derevative, Scanner fileScan){

        String type;
        ScanMenu scanMenu = new ScanMenu();
        while (fileScan.hasNext()){

            type = findType(fileScan);

            scanMenu.execute(type, derevative, fileScan);

        }
    }

    public String findType(Scanner fileScan ){

        String type;
        do {
            type = fileScan.nextLine();
        }while (type.equalsIgnoreCase(""));

        return type;
    }
}
