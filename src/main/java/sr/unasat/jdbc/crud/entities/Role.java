package sr.unasat.jdbc.crud.entities;

public class Role {
private int role_id;
private String name;
private String acces_level;


public Role (int role_id, String name, String acces_level){
    this.role_id=role_id;
    this.name=name;
    this.acces_level=acces_level;
}
public Role(int role_id){ this.role_id=role_id;
}
public Role(String name){this.name=name;
}
public Role(String acces_level){ this.acces_level=acces_level;
}

public void setRole_id(int role_id){this.role_id=role_id;
}
public String getName(){return name;
}
public String getacces_level(){this.acces_level=acces_level;
}
public void setName(String name) {this.name = name;
}
public void setAcces_level(String acces_level) {this.acces_level = acces_level;
}

    @Override
    public String toString(){
    return "Role{"+"role_id="+role_id+
                ", name='" + name+",acces_level='"+ acces_level + '\'' + '}';
}

}
