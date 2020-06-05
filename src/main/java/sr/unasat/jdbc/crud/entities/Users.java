package sr.unasat.jdbc.crud.entities;

public class Users {

    private int user_id;
    private String username;
    private String password;
    private int roll_id;

    public Users(int user_id , String username , String password , int roll_id){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.roll_id = roll_id;

    }
    public Users (int user_id) {this.user_id = user_id; }
    public Users (String username) {this.username = username; }
    public Users (String password) {this.password = password; }
    public Users (int roll_id) {this.roll_id = roll_id; }

    public int getUser_id() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getRoll_id() { return roll_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRoll_id(int roll_id) { this.roll_id = roll_id; }

    @Override
    public String toString() {
        return "Users{" + "user_id =" + user_id + ", username =" + username + ", password = " + password +
                ", roll_id = " + roll_id };
    }
}
