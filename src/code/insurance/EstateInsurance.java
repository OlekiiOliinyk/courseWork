package code.insurance;

public class EstateInsurance extends BasicInsurance{
    private String location;
    private double area;

    public EstateInsurance(double price, double expCompens,double risk, String location, double area){
        super(price, expCompens, risk);
        this.location = location;
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public double getArea() {
        return area;
    }

    public String getInsuranceType(){
        return "estate";
    }

    public String toString() {
        return String.format("Страхування: %-18s %-8s %-40s %-8s %-12.0f Ризик: %-5.1f%%    Компенсація: %-10.0f  Ціна: %-10.0f",
                "Нерухосмість", "Адреса:", this.location, "Площа:", this.area, getRisk(), getExpCompens(), getPrice());
    }

    public static EstateInsuranceBuilder builder() {
        return new EstateInsuranceBuilder();
    }

    public static class EstateInsuranceBuilder extends BasicInsurance.BasicInsuranceBuilder {


        private String location;
        private double area;

        EstateInsuranceBuilder() {}


        public EstateInsuranceBuilder location(String location) {
            this.location = location;
            return this;
        }


        public EstateInsuranceBuilder area(double area) {
            this.area = area;
            return this;
        }

        @Override
        public EstateInsurance build() {

            double res = this.expCompens * (this.area * this.risk)/(10000);
            price(res);

            return new EstateInsurance(price, expCompens, risk, location, area);
        }
    }
}
