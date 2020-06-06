package sr.unasat.jdbc.crud.entities;

public class Role {
private int role_id;
private static String role_name;
private static String access_level;



    public Role (int role_id, String name, String access_level)
{
    this.role_id =       role_id;
    role_name =          role_name;
    Role.access_level =   access_level;
}

public Role(int role_id)
{ this.role_id=role_id;
}

    public static void getaccess_level() {

    }


    public static void access_level() {

    }

    public static String getName() {
        return role_name;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void role_name (String role_name)
    { Role.role_name =role_name;
}
public void access_level(String access_level)
{ Role.access_level =access_level;
}

public void setRole_id(int role_id)
{   this.role_id=role_id;

}

    public void setName(String name) {
    role_name = role_name;
}
public void setAcces_level(String access_level) {
    Role.access_level = access_level;

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
