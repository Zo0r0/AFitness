package sr.unasat.afitness.crud.app;
import sr.unasat.afitness.crud.entities.Client;
import sr.unasat.afitness.crud.entities.Membership;
import sr.unasat.afitness.crud.entities.Role;
import sr.unasat.afitness.crud.entities.User;
import sr.unasat.afitness.crud.repositories.ClientRepository;
import sr.unasat.afitness.crud.repositories.MembershipsRepository;
import sr.unasat.afitness.crud.repositories.RoleRepository;
import sr.unasat.afitness.crud.repositories.UsersRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        MembershipsRepository memberRepo = new MembershipsRepository();
        RoleRepository roleRepo = new RoleRepository();
        ClientRepository clientRepo = new ClientRepository();
        UsersRepository userRepo = new UsersRepository();

//        Client client = clientRepo.getClientById(3);
//        System.out.println(client.getId());
//        System.out.println(clientRepo.deleteClientById(client));

//        Membership membership = memberRepo.getMembershipById(2);
//        Role role = roleRepo.getRoleById(5);
//
//        User user = new User(0, "mnarain", "lUk3sF@thEr", role);
//        int resultUser = userRepo.insertOneRecord(user);
//        User mnarain = userRepo.findUserByUsername("mnarain");
//
//        Client client = new Client(0, "Maarten", "Narain", "8684665", Date.valueOf("1975-05-15"), "T", membership, Date.valueOf("2021-06-01"),  mnarain );
//
//        int resultClient = clientRepo.insertClientRecord(client);



    }


}
