public class Cash extends Payment{
    private double amount;
    public Cash(double amount){
        amount = 0.0;
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
