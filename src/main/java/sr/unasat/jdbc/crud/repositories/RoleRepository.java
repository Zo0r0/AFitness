package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static sr.unasat.jdbc.crud.entities.Role.*;

public class RoleRepository {
    private Connection connection;

    public RoleRepository(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("De driver is geregistreerd!");

            String URL = "jdbc:mysql://localhost/AFitness";
            String USER = "root";
            String PASS = "root";
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println(connection);
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
            String sql ="SELECT * from roles";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");

                //                Note: Explain
                System.out.print("role_id: " + role_id);
                System.out.print(", role_name: " + role_name);
                System.out.println(", access_level:"+ access_level);

                roleList.add(new Role (role_id,role_name,access_level));

                //                Note: Explain
                roleList.add(new Role ( rs.getInt("role_id"),
                                        rs.getString("role_name"),
                                        rs.getString("access-level")));

            }
            rs.close();

        } catch (SQLException e){

        }finally {

        }
        return roleList;

    }
    
    public int insertOneRecord(Role role){
        PreparedStatement stmt= null;
        int result = 0;

        try {
            //                Note: Explain
            String sql = "INSERT INTO roles (role_name,access_level)  values (?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1 , getName());
            stmt.setString(2,getAccess_level());

            result = stmt.executeUpdate("resultset:" + result);
        }

        //                Note: Explain
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Role getRoleById(int id) {
        Role role = null;
        PreparedStatement stmt = null;

        try {
            String sql = "select * from roles where role_id = ?";
            stmt = connection.prepareStatement(sql);

            //                Note: Explain
            int role_id = 0;
            stmt.setInt(1, role_id);

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                //                Note: Correct
                  role_id = rs.getInt("role_id");
                String role_name = rs.getString("role_name");
                String access_level = rs.getString("access_level");
//                role = new Role(role_id, role_name, access_level );
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        }
        return role ;
    }

    public int updateOneRecord(Role role) {
        PreparedStatement stmt = null;
        int result = 0;

        try {

            //                Note: Explain
            String sql = "update roles  set role_id = ?, role_name= ? ,access_level = ?";
            stmt = connection.prepareStatement(sql);

            //                Note: Correct done?
            stmt.setString(1,Role.getName());
            stmt.setString( 2, Role.getAccess_level());

            result = stmt.executeUpdate();
        } catch (SQLException e)
        {

        }
        return result;
    }

    public int deleteOneRecord (Role role) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            String sql = " DELETE FROM roles WHERE role_id=?";
            stmt = connection.prepareStatement(sql);

            //                Note: Explain
            stmt.setString(1, getName());
            stmt.setString(2, getAccess_level());

            result = stmt.executeUpdate();
        }

        catch(SQLException e){
            System.out.println("error. Role not deleted");
        }

        return result;
    }

    }