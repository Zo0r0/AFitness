package sr.unasat.jdbc.crud.entities;

public class Persoon {
    private int id;
    private String naam;

  public Persoon(int id, String naam){
      this.id = id;
      this.naam = naam;
  }

    public Persoon(String naam) {
        this.naam = naam;
    }

    public Persoon(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "Persoon{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                '}';
    }
}
