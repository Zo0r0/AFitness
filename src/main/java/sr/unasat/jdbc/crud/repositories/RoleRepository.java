package sr.unasat.jdbc.crud.repositories;

import com.sun.tools.javac.util.List;
import sr.unasat.jdbc.crud.entities.Role;

import java.sql.*;
import java.util.ArrayList;

public class RoleRepository<result> {
 private Connection connection;
    private String role_name;

    public RoleRepository(){
     try {
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println("De driver is geregistreerd!");


         String URL = "jdbc:mysql://localhost/AFitness";
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
    public List<Role> findAllRecords(){
        List<Role> roleList = new Listrole>();
        Statement stmt = null;
        try{
            stmt = connection.createStatement();
            String sql ="SELECT * from roles";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("resultset:"+ rs );
            // Extract
            while (rs.next()){
                //retrieve by colum name
                int role_id = rs.getInt("role_id");
                String name = rs.getString("role_name");
                String acces_level = rs.getString("acces_level");
                 //display values
               System.out.print("role_id: " + role_id);
               System.out.print(", role_name: " + role_name);
               System.out.println(", access_level:"+ acces_level);

                roleList.add(new Role (role_id,name,acces_level));
                roleList.add(new Role(rs.getInt("role_id"), rs.getString("role_name"),
                        rs.getString("acces-level"));

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
        try {String sql = "INSERT INTO roles (role_name) values (?)";
            stmt= connection.prepareStatement(sql);
            stmt.setString(role.getName(), role.getacces_level());
            result=stmt.executeUpdate("resultset:" + result);

        } catch (SQLException) {

        } return result;

    }
    public int deleteOneRecord(Role role) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = " DELETE FROM roles WHERE role_id=?";
            stmt = connection.prepareStatement(sql)
            stmt.setString(role.getName(), role.getacces_level());
            result = stmt.executeUpdate();
            System.out.println("deleted:" + role.getName());
        }

    } catch public (SQLException e){

    } finally

    }return result
}
