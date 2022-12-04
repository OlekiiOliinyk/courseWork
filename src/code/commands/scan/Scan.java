package code.commands.scan;

import code.insurance.BasicInsurance;

import java.util.List;
import java.util.Scanner;

public interface Scan {
    void execute(List<BasicInsurance> derevative, Scanner fileScan);
}
