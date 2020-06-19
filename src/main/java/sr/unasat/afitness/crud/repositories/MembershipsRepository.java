package sr.unasat.afitness.crud.repositories;

import sr.unasat.afitness.crud.entities.Membership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipsRepository {
    private Connection connection;
    public MembershipsRepository() {
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

    public List<Membership> getAllRecords() {
        List<Membership> membershipList = new ArrayList<Membership>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM memberships";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int membership_id = rs.getInt("id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");

                membershipList.add(new Membership(membership_id, membership_period, membership_price));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return membershipList;
    }

    public Membership getMembershipById(int id) {
        Membership membership = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM memberships WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int membership_id = rs.getInt("id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");
                membership = new Membership(membership_id, membership_period, membership_price);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }
        return membership;
    }

    public void insertMembershipRecord(Membership membership) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "INSERT INTO memberships (membership_period, membership_price) VALUES(?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, membership.getPeriod());
            stmt.setString(2, membership.getPrice());

            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        if (result == 0) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Record was added successfully!");
        }
    }

    public void updateMembershipRecord(Membership membership) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "UPDATE memberships SET membership_period = ? membership_price = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, membership.getPeriod());
            stmt.setString(2, membership.getPrice());
            stmt.setInt(3, membership.getId());

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        if (result == 0) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Record with id: " + membership.getId()+ "was updated successfully!");
        }
    }

    public void deleteMembershipRecord(int id){
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "DELETE FROM memberships WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred:" + e);
        }

        if (result == 0) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Record with id: " + id+ "was deleted successfully!");
        }
    }



}
