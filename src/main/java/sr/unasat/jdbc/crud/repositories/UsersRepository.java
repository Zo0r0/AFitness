package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private Connection connection;
    public UsersRepository(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("De driver is geregistreerd!");

        String URL = "jdbc:mysql://localhost/adres_boek";
        String USER = "root";
        String PASS = "";
        connection = DriverManager.getConnection(URL, USER, PASS);
        System.out.println(connection);
    } catch (ClassNotFoundException ex) {
        System.out.println("Error: unable to load driver class!");
        System.exit(1);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public List<Users> findAllRecords() {
        List<Users> usersList = new ArrayList<Users>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "select * from users";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("resultset: " + rs);
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                usersList.add(new Users(user_id , username , password , role_id));
            }
            rs.close();


        } catch (SQLException e) {

        } finally {

        }
        return usersList;
    }

    public int insertOneRecord(Users users) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "insert into users (username , password) values(? , ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, users.getUsername());
            stmt.setString(2, users.getPassword());
            result = stmt.executeUpdate();
            System.out.println("resultset: " + result);

        } catch (SQLException e) {

        } finally {

        }
        return result;
    }

    public int deleteOneRecord(Users users){
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, users.getUser_id());
            result = stmt.executeUpdate();
            System.out.println("deleted: " + users.getUser_id());

        } catch (SQLException e) {

        } finally {

        }
        return result;
    }
    public int updateMembershipRecord(Users users) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "update users set username = ? password = ? where user_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, users.getUsername());
            stmt.setString(2, users.getPassword());
            stmt.setInt(3, users.getUser_id());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        } finally {

        }
        return result;
    }




}
