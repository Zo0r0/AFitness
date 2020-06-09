package sr.unasat.afitness.crud.repositories;


import sr.unasat.afitness.crud.entities.Client;
import sr.unasat.afitness.crud.entities.Membership;
import sr.unasat.afitness.crud.entities.Role;
import sr.unasat.afitness.crud.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ClientRepository {
    private Connection connection;

    public ClientRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
            String sql ="SELECT " +
                    "cl.id AS id, " +
                    "cl.client_name AS name, " +
                    "cl.client_surname AS surname, " +
                    "cl.client_phonenumber AS phonenumber, " +
                    "cl.client_address AS address, " +
                    "cl.client_date_of_birth AS date_of_birth, " +
                    "cl.client_is_active AS is_active, " +
                    "cl.client_membership_id AS membership_id, " +
                    "m.membership_period, " +
                    "m.membership_price, " +
                    "cl.client_membership_expiration AS membership_expiration, " +
                    "cl.client_user_id AS user_id, " +
                    "u.username, " +
                    "u.password, " +
                    "u.role_id, " +
                    "r.role_name, " +
                    "r.access_level " +
                    "FROM " +
                    "((clients AS cl " +
                    "JOIN memberships AS m ON m.id = cl.client_membership_id) " +
                    "JOIN users AS u ON u.id = cl.client_user_id) " +
                    "JOIN " +
                    "roles AS r ON r.id = u.role_id";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int client_id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phonenumber = rs.getString("phonenumber");
                Date date_of_birth = rs.getDate("date_of_birth");
                String is_active = rs.getString("is_active");

                int membership_id = rs.getInt("membership_id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");
                Date membership_expiration = rs.getDate("membership_expiration");

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
            String sql = "SELECT " +
                    "cl.id AS id, " +
                    "cl.client_name AS name, " +
                    "cl.client_surname AS surname, " +
                    "cl.client_phonenumber AS phonenumber, " +
                    "cl.client_address AS address, " +
                    "cl.client_date_of_birth AS date_of_birth, " +
                    "cl.client_is_active AS is_active, " +
                    "cl.client_membership_id AS membership_id, " +
                    "m.membership_period, " +
                    "m.membership_price, " +
                    "cl.client_membership_expiration AS membership_expiration, " +
                    "cl.client_user_id AS user_id, " +
                    "u.username, " +
                    "u.password, " +
                    "u.role_id, " +
                    "r.role_name, " +
                    "r.access_level " +
                    "FROM " +
                    "((clients AS cl " +
                    "JOIN memberships AS m ON m.id = cl.client_membership_id) " +
                    "JOIN users AS u ON u.id = cl.client_user_id) " +
                    "JOIN " +
                    "roles AS r ON r.id = u.role_id " +
                    "WHERE cl.id = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int client_id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phonenumber = rs.getString("phonenumber");
                Date date_of_birth = rs.getDate("date_of_birth");
                String is_active = rs.getString("is_active");

                int membership_id = rs.getInt("membership_id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");
                Date membership_expiration = rs.getDate("membership_expiration");

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
            System.out.println("An error has occurred:" + e);
        }
        return client;
    }

    public int insertClientRecord(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try{
            String sql = "INSERT INTO clients(client_name, client_surname,client_phonenumber, client_date_of_birth, " +
                    "client_is_active, client_membership_id ,client_membership_expiration, client_user_id) VALUES(?,?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setDate(4, (java.sql.Date) client.getDOB());
            stmt.setString(5, client.getIsActive());
            stmt.setInt(6, client.getMembershipId());
            stmt.setDate(7, (java.sql.Date) client.getMembershipExpiration());
            stmt.setInt(8, client.getUserId());

            result = stmt.executeUpdate();

        } catch(SQLException e){
            System.out.println("An error has occurred:" + e);
        }

        return result;

    }

    public int updateClientById(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "UPDATE clients SET " +
                        "client_name  = ? , client_surname = ?, client_phonenumber = ?, client_date_of_birth = ? , " +
                        "client_is_active= ? WHERE id = ?";
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setDate(4, (java.sql.Date) client.getDOB());
            stmt.setString(5, client.getIsActive());
            stmt.setInt(6, client.getId());
            stmt = connection.prepareStatement(sql);

            result = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("An error has occurred:" + e);
        }
        return result;

    }

    public int updateClientMembership(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "UPDATE clients SET client_membership_id  = ? , client_membership_expiration = ? WHERE id = ?";
            stmt.setInt(1, client.getMembershipId());
            stmt.setDate(2, (java.sql.Date) client.getMembershipExpiration());
            stmt.setInt(3, client.getId());
            stmt = connection.prepareStatement(sql);

            result = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("An error has occurred:" + e);
        }
        return result;

    }

    public int updateClientUser(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "UPDATE clients SET client_user_id  = ?  WHERE id = ?";
            stmt.setInt(1, client.getUserId());
            stmt.setInt(2, client.getId());
            stmt = connection.prepareStatement(sql);

            result = stmt.executeUpdate();


        }catch (SQLException e){
            System.out.println("An error has occurred:" + e);
        }
        return result;

    }

    public int deleteClientById(Client client) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = "DELETE FROM clients WHERE id = ?";
            stmt.setInt(1, client.getId());
            stmt = connection.prepareStatement(sql);

            result = stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("An error has occurred:" + e);
        }
        return result;

    }

}