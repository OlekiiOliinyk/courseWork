package code.insurance;

public class CarInsurance extends BasicInsurance {
    private String model;
    private double kmrun;

    public CarInsurance(double price, double expCompens,double risk, String model, double kmrun){
        super(price, expCompens, risk);
        this.model = model;
        this.kmrun = kmrun;
    }

    public String getModel() {
        return model;
    }

    public double getKmrun() {
        return kmrun;
    }

    public String getInsuranceType(){
        return "car";
    }


    @Override
    public String toString() {

        return String.format("Страхування: %-18s %-8s %-40s %-8s %-12.1f Ризик: %-5.1f%%    Компенсація: %-10.0f  Ціна: %-10.1f",
                "Авто", "Модель:", getModel(), "Пробіг:", getKmrun(), getRisk(), getExpCompens(),getPrice());
    }



    public static CarInsuranceBuilder builder() {
        return new CarInsuranceBuilder();
    }

    public static class CarInsuranceBuilder extends BasicInsurance.BasicInsuranceBuilder {


        private String model;
        private double kmrun;

        CarInsuranceBuilder() {
        }


        public CarInsuranceBuilder model(String model) {
            this.model = model;
            return this;
        }


        public CarInsuranceBuilder kmrun(double kmrun) {
            this.kmrun = kmrun;
            return this;
        }

        @Override
        public CarInsurance build() {

            double res = this.expCompens / (this.kmrun / ((this.kmrun) / (100 - this.risk)));

            price(res);

            return new CarInsurance(price, expCompens, risk, model, kmrun);
        }
    }
}
