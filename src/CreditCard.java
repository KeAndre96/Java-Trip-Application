public class CreditCard extends Payment{
    private String cardNumber;
    private String expirationDate;
    private double amount;

    public CreditCard(){
        cardNumber = "1234567891";
        expirationDate = "12/04/2019";
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
