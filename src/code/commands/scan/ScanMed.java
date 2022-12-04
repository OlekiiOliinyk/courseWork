package code.commands.scan;

import code.insurance.BasicInsurance;
import code.insurance.MedicalInsurance;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScanMed implements Scan{


    public void execute(List<BasicInsurance> derevative, Scanner fileScan){



        scan(derevative,fileScan);



    }


    public void scan(List<BasicInsurance> derevative, Scanner fileScan){

        try {

            MedicalInsurance insurance = (MedicalInsurance) MedicalInsurance
                    .builder()
                    .name(fileScan.nextLine())
                    .age(fileScan.nextInt())
                    .risk(fileScan.nextDouble())
                    .expCompens(fileScan.nextDouble())
                    .build();
                derevative.add(insurance);

        }catch (InputMismatchException e){
            e.printStackTrace();
        }

    }
}
