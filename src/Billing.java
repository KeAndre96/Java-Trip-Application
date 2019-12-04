public class Billing {
    private double total;
    private boolean paidInFull;

    public Billing(){
        total = 0.0;
        paidInFull = false;
    }
    public void setTotal(double total){
        this.total = total;
    }
    public double getTotal(){
        return total;
    }
    public void setPaidInFull(boolean paidInFull){
        this.paidInFull = paidInFull;
    }
    public boolean getPaidInFull(){
        return paidInFull;
    }
}
