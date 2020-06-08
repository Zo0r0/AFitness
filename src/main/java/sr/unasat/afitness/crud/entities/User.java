package sr.unasat.afitness.crud.entities;

public class User {

    public int user_id;
    public String username;
    public String password;
    public Role role;

    public User(int user_id , String username , String password , Role role){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    public void setUserId(int user_id) { this.user_id = user_id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }

}
