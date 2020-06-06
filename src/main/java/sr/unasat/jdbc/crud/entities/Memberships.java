package sr.unasat.jdbc.crud.entities;

public class Memberships {
    public int membership_id;
    public String membership_period;
    public String membership_price;

    public Memberships(int id, String period, String price){
        this.membership_id = id;
        this.membership_period = period;
        this.membership_price = price;
    }

   /* public Memberships(int id){
        this.membership_id = id;
    }

    public Memberships(String period){
        this.membership_period = period;
    }

    public Memberships(String price){
        this.membership_period = price;
    }*/

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

    @Override
    public String toString() {
        return "Persoon{" +
                "id=" + membership_id +
                ", period='" + membership_period +
                ", price='" + membership_price + '\'' +
                '}';
    }
}
