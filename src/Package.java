public class Package {
    private String travelsFrom;
    private String travelsTo;
    private double price;
    private double hours_of_travel;

    public Package(){
        travelsFrom = "";
        travelsTo = "";
        price = 0.0;
        hours_of_travel = 0.0;
    }
    public void setTravelsFrom(String travelsFrom){
        this.travelsFrom = travelsFrom;
    }
    public String getTravelsFrom(){
        return travelsFrom;
    }
    public void setTravelsTo(String travelsTo){
        this.travelsTo = travelsTo;
    }
    public String getTravelsTo(){
        return travelsTo;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    public void setHours_of_travel(double hours_of_travel){
        this.hours_of_travel = hours_of_travel;
    }
    public double getHours_of_travel(){
        return hours_of_travel;
    }
}
