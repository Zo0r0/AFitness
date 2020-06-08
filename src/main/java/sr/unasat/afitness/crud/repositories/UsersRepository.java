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

    public List<User> findAllRecords() {
        List<User> userList = new ArrayList<User>();
        List<Role> roleList = new ArrayList<Role>();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            String sql = "select user.*, role.role_name, role.access_level from users " +
                    "inner join roles on users.role_id = role.id";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int user_id = rs.getInt("user_id");
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

        } finally {

        }
        return userList;
    }

    public User findUserById(int id) {
        User user = null;
        Role role = null;
        PreparedStatement stmt = null;

        try {
            String sql = "select users.*, roles.role_name, roles.access_level from users " +
                    "inner join roles on users.role_id = roles.id where users.id = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery(sql);

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



    public int insertOneRecord(User user, Role role) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "insert into users (username , password, role_id) values(? , ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, role.getRoleId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {

        }

        return result;
    }

    public int deleteOneRecord(User user){
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getUserId());
            result = stmt.executeUpdate();
            System.out.println("deleted: " + user.getUserId());

        } catch (SQLException e) {

        }
        return result;
    }
    public int updateUserRecord(User user) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "update users set username = ? password = ? where id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        } finally {

        }
        return result;
    }




}
