package sr.unasat.jdbc.crud.entities;

import java.util.Date;

public class Client {
    private int id;
    public String name;
    public String surname;
    public String phonenumber;
    public Date date_of_birth;
    public boolean is_active;

    public Client(int id, String name, String surname, String phonenumber, Date date_of_birth, boolean is_active) {
       this.id = id;
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.date_of_birth = date_of_birth;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return name+","+surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
