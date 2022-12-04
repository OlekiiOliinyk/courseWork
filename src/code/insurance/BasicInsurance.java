package code.insurance;

public class BasicInsurance {
    private double price;
    private double expCompens;
    private double risk;

    public BasicInsurance(double price, double expCompens,double risk){
        this.price = price;
        this.expCompens = expCompens;
        this.risk = risk;
    }

    public double getPrice() {
        return price;
    }

    public double getExpCompens() {
        return expCompens;
    }

    public double getRisk() {
        return risk;
    }

    public static BasicInsuranceBuilder builder() {
        return new BasicInsuranceBuilder();
    }

    public String getInsuranceType(){
        return "basic";
    }


    public static class BasicInsuranceBuilder{

        protected double price;
        protected double expCompens;
        protected double risk;

        BasicInsuranceBuilder(){}


        public BasicInsuranceBuilder risk(double risk) {

            this.risk = risk;
            return this;
        }

        public BasicInsuranceBuilder expCompens(double expCompens) {
            this.expCompens = expCompens;
            return this;
        }

        protected BasicInsuranceBuilder price(double price) {

            this.price = price;
            return this;
        }

        public BasicInsurance build() {
            return new BasicInsurance(price, expCompens, risk);
        }
    }
}
