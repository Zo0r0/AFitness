package sr.unasat.afitness.crud.repositories;

import sr.unasat.afitness.crud.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    private Connection connection;

    public RoleRepository(){
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

    public List<Role> findAllRecords (){
        java.util.List<Role> roleList = new ArrayList<Role>();
        Statement stmt = null;

        try{
            stmt = connection.createStatement();
            String sql ="SELECT * FROM roles";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int role_id = rs.getInt("id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");

                roleList.add(new Role (role_id,role_name,access_level));
            }
            rs.close();

        } catch (SQLException e){
            System.out.println("An error has occurred:" + e);
        }
        return roleList;

    }
    
    public int insertOneRecord(Role role){
        PreparedStatement stmt= null;
        int result = 0;

        try {
            String sql = "INSERT INTO roles (role_name,access_level)  VALUES (?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1 , role.getRoleName());
            stmt.setString(2, role.getAccessLevel());

            result = stmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return result;
    }

    public Role getRoleById(int id) {
        Role role = null;
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM roles WHERE id = ?";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int  role_id = rs.getInt("id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");
                role = new Role(role_id, role_name, access_level );
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return role ;
    }

    public int updateOneRecord(Role role) {
        PreparedStatement stmt = null;
        int result = 0;

        try {

            String sql = "UPDATE roles  SET role_name= ? ,access_level = ? WHERE id= ?";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, role.getRoleName());
            stmt.setString( 2, role.getAccessLevel());
            stmt.setInt(3, role.getRoleId());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return result;
    }

    public int deleteOneRecord (Role role) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = " DELETE FROM roles WHERE id=?";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, role.getRoleId());
            result = stmt.executeUpdate();
        }

        catch(SQLException e){
            System.out.println("An error has occurred:" + e);
        }
        return result;
    }

}