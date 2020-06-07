package sr.unasat.jdbc.crud.entities;

import java.util.Date;

public class Client {
    public int id;
    public String name;
    public String surname;
    public String phonenumber;
    public Date date_of_birth;
    public boolean is_active;
    public Membership membership;
    public Date membership_expiration;
    public User user;

    public Client(int id, String name, String surname, String phonenumber, Date date_of_birth, boolean is_active, Membership membership, Date membership_expiration, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.date_of_birth = date_of_birth;
        this.is_active = is_active;
        this.membership = membership;
        this.membership_expiration = membership_expiration;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getDOB() {
        return date_of_birth;
    }

    public void setDOB(Date dob) {
        this.date_of_birth = dob;
    }

    public boolean getIsActive() {
        return is_active;
    }

    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }

    public Membership getMembership() {
        return membership;
    }

    public int getMembershipId() {
        return membership.getId();
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Date getMembershipExpiration() {
        return membership_expiration;
    }

    public void setMembershipExpiration(Date membership_expiration) {
        this.membership_expiration = membership_expiration;
    }

    public User getUser() {
        return user;
    }

    public int getUserId() {
        return user.getUser_id();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
