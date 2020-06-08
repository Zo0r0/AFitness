package sr.unasat.afitness.crud.repositories;


import sr.unasat.afitness.crud.entities.Client;
import sr.unasat.afitness.crud.entities.Membership;
import sr.unasat.afitness.crud.entities.Role;
import sr.unasat.afitness.crud.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientRepository {
    private Connection connection;

    public ClientRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("De driver is geregistreerd!");

            String URL = "jdbc:mysql://localhost:3306/afitness_db?autoReconnect=true&useSSL=false";
            String USER = "root";
            String PASS = "root";

            connection = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllRecords() {
        List<Client> clientList = new ArrayList<Client>();
        List<Membership> membershipList = new ArrayList<Membership>();
        List<Role> roleList = new ArrayList<Role>();
        List<User> userList = new ArrayList<User>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql ="select " +
                        "cl.id as id, " +
                        "cl.client_name as name, " +
                        "cl.client_surname as surname, " +
                        "cl.client_phonenumber as phonenumber, " +
                        "cl.client_address as address, " +
                        "cl.client_date_of_birth as date_of_birth, " +
                        "cl.client_is_active as is_active, " +
                        "cl.client_membership_id as membership_id, " +
                        "m.membership_period, " +
                        "m.membership_price, " +
                        "cl.client_membership_expiration as membership_expiration, " +
                        "cl.client_user_id as user_id, " +
                        "u.username, " +
                        "u.password, " +
                        "u.role_id, " +
                        "r.role_name, " +
                        "r.access_level" +
                        "from clients as cl " +
                        "join memberships as m on m.id = cl.client_membership_id " +
                        "join users as u on u.id cl.client_user_id " +
                        "join roles as r on r.id = u.role_id";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int client_id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phonenumber = rs.getString("phonenumber");
                Date date_of_birth = rs.getDate("date_of_birth");
                boolean is_active = rs.getBoolean("is_active");

                int membership_id = rs.getInt("membership_id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");
                Date membership_expiration = rs.getDate("client_membership_expiration");

                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");

                Membership membership = new Membership(membership_id, membership_period, membership_price);
                Role role = new Role(role_id, role_name, access_level);
                User user = new User(user_id, username, password, role);

                roleList.add(role);
                userList.add(user);
                membershipList.add(membership);
                clientList.add(new Client(client_id, name, surname, phonenumber,
                        date_of_birth, is_active, membership, membership_expiration, user));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return clientList;
    }

    public Client getClientById(int id) {
        Client client = null;
        Membership membership = null;
        User user = null;
        Role role = null;

        PreparedStatement stmt = null;

        try {
            String sql ="select " +
                        "cl.id as id, " +
                        "cl.client_name as name, " +
                        "cl.client_surname as surname, " +
                        "cl.client_phonenumber as phonenumber, " +
                        "cl.client_address as address, " +
                        "cl.client_date_of_birth as date_of_birth, " +
                        "cl.client_is_active as is_active, " +
                        "cl.client_membership_id as membership_id, " +
                        "m.membership_period, " +
                        "m.membership_price, " +
                        "cl.client_membership_expiration as membership_expiration, " +
                        "cl.client_user_id as user_id, " +
                        "u.username, " +
                        "u.password, " +
                        "u.role_id, " +
                        "r.role_name, " +
                        "r.access_level" +
                        "from clients as cl " +
                        "inner join memberships as m on cl.client_membership_id = m.id" +
                        "inner join users as u on cl.client_user_id = u.id" +
                        "inner join roles as r on u.role_id = r.id where cl.id = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int client_id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phonenumber = rs.getString("phonenumber");
                Date date_of_birth = rs.getDate("date_of_birth");
                boolean is_active = rs.getBoolean("is_active");

                int membership_id = rs.getInt("membership_id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");
                Date membership_expiration = rs.getDate("client_membership_expiration");

                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");

                membership = new Membership(membership_id, membership_period, membership_price);
                role = new Role(role_id, role_name, access_level);
                user = new User(user_id, username, password, role);

                client = new Client(client_id, name, surname, phonenumber, date_of_birth, is_active, membership, membership_expiration, user);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        }
        return client;
    }

    public int insertClientRecord(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try{
            String sql = "insert into clients(client_name, client_surname,client_phonenumber, client_date_of_birth, " +
                    "client_is_active, client_membership_id ,client_membership_expiration, user_id) values(?,?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setDate(4, (java.sql.Date) client.getDOB());
            stmt.setBoolean(5, client.getIsActive());
            stmt.setInt(6, client.getMembershipId());
            stmt.setDate(7, (java.sql.Date) client.getMembershipExpiration());
            stmt.setInt(8, client.getUserId());

            result = stmt.executeUpdate();

        } catch(SQLException e){
            System.out.println("An error has occurred ");
        }

        return result;

    }

    public int updateClientById(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "update clients set client_name  = ? , client_surname = ?, client_phonenumber = ?, client_date_of_birth = ? , client_is_active= ? where id = ?";
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setDate(4, (java.sql.Date) client.getDOB());
            stmt.setBoolean(5, client.getIsActive());
            stmt.setInt(6, client.getId());
            stmt = connection.prepareStatement(sql);
            result = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("An error has occurred ");
        }
        return result;

    }

    public int updateClientMembership(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "update clients set client_membership_id  = ? , client_membership_expiration = ? where id = ?";
            stmt.setInt(1, client.getMembershipId());
            stmt.setDate(2, (java.sql.Date) client.getMembershipExpiration());
            stmt.setInt(3, client.getId());
            stmt = connection.prepareStatement(sql);
            result = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("An error has occurred ");
        }
        return result;

    }

    public int updateClientUser(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "update clients set client_user_id  = ?  where id = ?";
            stmt.setInt(1, client.getUserId());
            stmt.setInt(2, client.getId());
            stmt = connection.prepareStatement(sql);
            result = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("An error has occurred ");
        }
        return result;

    }

    public int deleteClientById(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "delete from clients where id = ?";
            stmt.setInt(1, client.getId());
            stmt = connection.prepareStatement(sql);
            result = stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("An error has occurred ");
        }
        return result;

    }

}