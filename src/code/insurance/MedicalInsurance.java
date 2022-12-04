package code.insurance;

public class MedicalInsurance extends BasicInsurance{
    private String name;
    private int age;

    public MedicalInsurance(double price, double expCompens,double risk, String name, int age){
        super(price, expCompens, risk);
        this.name =  name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getInsuranceType(){
        return "medical";
    }

    public String toString() {

        return String.format("Страхування: %-18s %-8s %-40s %-8s %-12d Ризик: %-5.1f%%    Компенсація: %-10.0f  Ціна: %-10.0f",
                "Медичне", "ПІБ:", this.name, "Вік:", this.age, getRisk(), getExpCompens(), getPrice());
    }


    public static MedicalInsuranceBuilder builder() {
        return new MedicalInsuranceBuilder();
    }

    public static class MedicalInsuranceBuilder extends BasicInsurance.BasicInsuranceBuilder {
        private String name;
        private int age;
        MedicalInsuranceBuilder() {}


        public MedicalInsuranceBuilder name(String name) {
            this.name = name;
            return this;
        }


        public MedicalInsuranceBuilder age(int age) {

            this.age = age;
            return this;
        }

        @Override
        public MedicalInsurance build() {

            double res = (this.expCompens/(1000.0/this.age)) - ((100*this.risk)/(this.age));
            price(res);

            return new MedicalInsurance(price, expCompens, risk, name, age);
        }
    }

}
