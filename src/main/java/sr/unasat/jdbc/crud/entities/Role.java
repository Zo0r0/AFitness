package sr.unasat.jdbc.crud.entities;

public class Role {
    public static String role_name;
    public  String access_level;
    public int role_id;
    public int getrole_id;

    public Role (int role_id, String role_name, String access_level)
{
    this.role_name = role_name;
    this.access_level =   access_level;
}

    public Role(int role_id){
    }

    public  void access_level() {
    }
    public  void getaccess_level() {
    }
    public  String getName() {
        return role_name;
    }

    public Integer getrole_id() {
        return role_id;
    }

    public String getAccess_level() {
        return access_level;
    }
    public void role_name (String role_name)
    { Role.role_name =role_name;
    }
    public void setRole_id(int role_id)
    {
    }
    public void setName(String name) {
    role_name =  role_name;
    }

        /*
    @Override
    public String toString(){
    return "Role{"+"role_id="+role_id+
                ", role_name='" + role_name+",access_level='"+ access_level+ '\'' +
                '}'  ;

}
*/


}


