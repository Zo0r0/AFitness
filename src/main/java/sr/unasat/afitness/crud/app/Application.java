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

        List<User> users = userRepo.findAllRecords();
        List<Role> roles = roleRepo.findAllRecords();
        List<Membership> memberships = memberRepo.getAllRecords();


//      INSERT NEW RECORD INTO T3 TABEL
        Client client = new Client(0, "Maarten", "Narain", "8684665", "Kersenstraat 41", Date.valueOf("1975-05-15"), "T",
                memberships.get(2), Date.valueOf("2021-06-01"), users.get(1) );
        clientRepo.insertClientRecord(client);

//      INSERT NEW USER
        Role role = roles.get(4);
        User user = new User(0, "mnarain", "lUk3sF@thEr", role);
        userRepo.insertOneRecord(user);

        User UserNarain = userRepo.findUserByUsername("mnarain");

//      SELECT + UPDATE T3
        Client clientNarain = clientRepo.getClientById(5);
        clientNarain.setMembership(memberships.get(1));
        clientNarain.setUser(UserNarain);

        clientRepo.updateClientById(clientNarain);

//      DELETE FROM T3
        clientRepo.deleteClientById(3);

//      SELECT ALL FROM T3
        List<Client> clients = clientRepo.getAllRecords();
        System.out.println(clients);
    }


}
