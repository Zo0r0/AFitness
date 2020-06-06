package sr.unasat.jdbc.crud.repositories;

import com.mysql.jdbc.Driver;
import sr.unasat.jdbc.crud.entities.Memberships;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipsRepository {
    private Connection connection;
    public MembershipsRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("De driver is geregistreerd!");

            String URL = "jdbc:mysql://localhost/afitness_db";
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


}
