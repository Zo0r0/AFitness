package sr.unasat.afitness.crud.repositories;

import sr.unasat.afitness.crud.entities.Role;
import sr.unasat.afitness.crud.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private Connection connection;

    public UsersRepository(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("De driver is geregistreerd!");

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

    public List<User> findAllRecords() {
        List<User> userList = new ArrayList<User>();
        List<Role> roleList = new ArrayList<Role>();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT users.*, roles.role_name, roles.access_level FROM users " +
                    "INNER JOIN roles ON users.role_id = roles.id";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int user_id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");

                Role role = new Role(role_id, role_name, access_level);
                roleList.add(role);
                userList.add(new User(user_id , username , password , role));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        return userList;
    }

    public User findUserByUsername(String usernm) {
        User user = null;
        Role role = null;
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT users.*, roles.role_name, roles.access_level FROM users " +
                    "INNER JOIN roles ON users.role_id = roles.id WHERE users.username = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usernm);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int user_id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");

                role = new Role(role_id, role_name, access_level);
                user = new User(user_id , username , password , role);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return user;
    }



    public int insertOneRecord(User user) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "INSERT INTO users (username , password, role_id) VALUES(? , ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRoleId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        return result;
    }

    public void updateUserRecord(User user) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "UPDATE users SET username = ? password = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        if (result == 0) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Record with id: " + user.getUserId() + "was updated successfully!");
        }
    }

    public void deleteOneRecord(int id){
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        if (result == 0) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Record with id: " + id + "was deleted successfully!");
        }
    }




}
