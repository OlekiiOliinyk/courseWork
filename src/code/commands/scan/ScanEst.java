package code.commands.scan;

import code.insurance.BasicInsurance;
import code.insurance.EstateInsurance;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScanEst implements Scan{



    public void execute(List<BasicInsurance> derevative, Scanner fileScan){


        scan(derevative, fileScan);

    }


    public void scan(List<BasicInsurance> derevative, Scanner fileScan){

        try{
        EstateInsurance insurance = (EstateInsurance) EstateInsurance.builder()
                .location(fileScan.nextLine())
                .area(fileScan.nextDouble())
                .risk(fileScan.nextDouble())
                .expCompens(fileScan.nextDouble())
                .build();

        derevative.add(insurance);
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    }
}
