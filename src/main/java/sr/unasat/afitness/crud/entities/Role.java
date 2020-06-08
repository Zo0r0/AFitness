package sr.unasat.afitness.crud.entities;

public class Role {
    public int role_id;
    public String role_name;
    public String access_level;

    public Role (int role_id, String role_name, String access_level) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.access_level =   access_level;
    }

    public int getRoleId() {return role_id;}
    public String getRoleName() {return role_name;}
    public String getAccessLevel() { return access_level;}


    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    public void setAccessLevel(String access_level) {
        this.access_level = access_level;
    }
}


