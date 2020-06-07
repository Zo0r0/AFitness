package sr.unasat.jdbc.crud.app;
import sr.unasat.jdbc.crud.entities.Role;
import sr.unasat.jdbc.crud.repositories.RoleRepository;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        RoleRepository roleRepository = new RoleRepository();
        List <Role> Rolelist = roleRepository.findAllRecords();
        for (Role role : Rolelist){
            System.out.println(role);
        }

    }


}
