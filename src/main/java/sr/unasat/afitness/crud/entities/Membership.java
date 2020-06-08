package sr.unasat.afitness.crud.entities;

public class Membership {
    public int membership_id;
    public String membership_period;
    public String membership_price;

    public Membership(int id, String period, String price){
        this.membership_id = id;
        this.membership_period = period;
        this.membership_price = price;
    }

    public int getId() {
        return membership_id;
    }

    public void setId(int id) {
        this.membership_id = id;
    }

    public String getPeriod() {
        return membership_period;
    }

    public void setPeriod(String period) {
        this.membership_period = period;
    }

    public String getPrice() {
        return membership_price;
    }

    public void setPrice(String price) {
        this.membership_price = price;
    }
}
