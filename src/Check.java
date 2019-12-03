public class Check extends Payment{
    private String checkNunber;
    private double amount;
    public Check(){
        checkNunber = "12345678910";
        amount = 0.0;
    }
    public String getCheckNunber(){
        return checkNunber;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public double getAmount(){
        return amount;
    }

    @Override
    public void addDownPayment(double amount) {
        this.amount = amount;
    }
}
