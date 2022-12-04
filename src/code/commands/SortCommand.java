package code.commands;

import code.insurance.BasicInsurance;


import java.util.List;

public class SortCommand {

    public void execute(List<BasicInsurance> derevative){
        sortDerevative(derevative);
    }
    public void sortDerevative(List<BasicInsurance> derevative){

        derevative.sort((x, y) -> Double.compare(y.getRisk(), x.getRisk()));

    }


}
