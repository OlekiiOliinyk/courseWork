package code.commands.scan;

import code.insurance.BasicInsurance;

import java.util.*;

public class ScanMenu {
    private Map<String, Scan> type;

    public ScanMenu(){
        type = new HashMap<>();
        type.put("car",       new ScanCar());
        type.put("med",       new ScanMed());
        type.put("est",       new ScanEst());

    }

    public void execute(String command, List<BasicInsurance> derevative, Scanner fileScan) {
        type.getOrDefault(command, (List<BasicInsurance> error, Scanner errorScan) ->
                System.out.println("Некоректний тип страхування")).execute(derevative, fileScan);
    }
}
