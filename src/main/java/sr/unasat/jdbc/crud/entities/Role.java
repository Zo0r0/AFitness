package sr.unasat.jdbc.crud.entities;

public class Role {
    private static String role_name;
private static String access_level;

public Role (int role_id, String role_name, String access_level)
{
    Role.role_name = role_name;
    Role.access_level =   access_level;
}

    public Role(int role_id){
    }

    public static void access_level() {
    }
    public static void getaccess_level() {
    }
    public static String getName() {
        return role_name;
    }
    public static String getAccess_level() {
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


