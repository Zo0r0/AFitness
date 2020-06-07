package sr.unasat.jdbc.crud.entities;

public class Users {

    private int user_id;
    private String username;
    private String password;
    private int role_id;

    public Users(int user_id , String username , String password , int roll_id){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;

    }

    public void setuser_Id(int user_id) {
        this.user_id = user_id;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setpassword (String password) {
        this.password = password;
    }

    public void setrole_id(int roll_id) {
        this.role_id = role_id;
    }

    public int getUser_id() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getRole_id() { return role_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole_id(int role_id) { this.role_id = role_id; }

    /*@Override
    public String toString() {
        return "Users{" + "user_id =" + user_id + ", username =" + username + ", password = " + password +
                ", role_id = " + role_id };
  */
}
