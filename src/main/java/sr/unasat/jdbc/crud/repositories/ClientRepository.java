package sr.unasat.jdbc.crud.repositories;


import sr.unasat.jdbc.crud.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientRepository {
    private Connection connection;

    public ClientRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("De driver is geregistreerd!");

            String URL = "jdbc:mysql://localhost/afitness_db";
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

    public List<Client> getAllRecords() {
        List<Client> clientList = new ArrayList<Client>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "select * from clients";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("client_name");
                String surname = rs.getString("client_surname");
                String phonenumber = rs.getString("client_phonenumber");
                Date date_of_birth = rs.getDate("client_date_of_birth");
                boolean is_active = rs.getBoolean("client_is_active");

                clientList.add(new Client(id, name, surname, phonenumber, date_of_birth, is_active));
            }
            rs.close();

        } catch (SQLException e) {

        } finally {

        }
        return clientList;
    }

}