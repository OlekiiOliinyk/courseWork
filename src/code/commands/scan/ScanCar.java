package code.commands.scan;

import code.insurance.BasicInsurance;
import code.insurance.CarInsurance;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScanCar implements Scan{


    public void execute(List<BasicInsurance> derevative, Scanner fileScan){


        scan(derevative,fileScan);

    }

    public void scan(List<BasicInsurance> derevative, Scanner fileScan){

        try{

        CarInsurance insurance = (CarInsurance) CarInsurance
                .builder()
                .model(fileScan.nextLine())
                .kmrun(fileScan.nextDouble())
                .risk(fileScan.nextDouble())
                .expCompens(fileScan.nextDouble())
                .build();
        derevative.add(insurance);

        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    }
}
