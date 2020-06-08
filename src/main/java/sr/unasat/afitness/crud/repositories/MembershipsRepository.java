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

    public List<Membership> getAllRecords() {
        List<Membership> membershipList = new ArrayList<Membership>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "select * from memberships";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int membership_id = rs.getInt("membership_id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");

                membershipList.add(new Membership(membership_id, membership_period, membership_price));
            }
            rs.close();

        } catch (SQLException e) {

        } finally {

        }
        return membershipList;
    }

    public Membership getMembershipById(int id) {
        Membership membership = null;
        PreparedStatement stmt = null;
        try {
            String sql = "select * from memberships where id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int membership_id = rs.getInt("id");
                String membership_period = rs.getString("membership_period");
                String membership_price = rs.getString("membership_price");
                membership = new Membership(membership_id, membership_period, membership_price);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        }
        return membership;
    }

    public int insertMembershipRecord(Membership membership) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "insert into memberships (membership_period, membership_price) values(?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, membership.getPeriod());
            stmt.setString(2, membership.getPrice());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        } finally {

        }
        return result;
    }

    public int updateMembershipRecord(Membership membership) {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "update memberships set membership_period = ? membership_price = ? where membership_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, membership.getPeriod());
            stmt.setString(2, membership.getPrice());
            stmt.setInt(3, membership.getId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        } finally {

        }
        return result;
    }

    public int deleteMembershipRecord(Membership membership){
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "delete from memberships where membership_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, membership.getId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occurred ");
        } finally {

        }
        return result;
    }



}