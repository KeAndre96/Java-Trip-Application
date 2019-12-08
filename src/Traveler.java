public class Traveler extends Person {
    String address;
    public Traveler(String name, String phone, String email, String address){
        super(name, phone, email);
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return name;
    }
}
