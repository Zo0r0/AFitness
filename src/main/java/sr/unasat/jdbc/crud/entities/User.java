package sr.unasat.jdbc.crud.entities;

public class User {

    public int user_id;
    public String username;
    public String password;
    public Role role;

    public User(int user_id , String username , String password , int role_id){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public int getUser_id() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(User user) { this.role = role; }

    /*@Override
    public String toString() {
        return "Users{" + "user_id =" + user_id + ", username =" + username + ", password = " + password +
                ", role_id = " + role_id };
  */
}
