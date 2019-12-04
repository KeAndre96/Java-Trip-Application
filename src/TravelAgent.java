public class TravelAgent extends Person {

    private int id;

    public TravelAgent(String name, String phone, String email, int id){
        super(name, phone, email);
        this.id = id;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
