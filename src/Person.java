public abstract class Person {
    protected String name;
    private String phone;
    private String email;
    public Person(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone;
    }
}
