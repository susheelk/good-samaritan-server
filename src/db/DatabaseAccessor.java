package db;

import model.Charity;
import model.Donation;
import model.MonetaryDonation;
import model.User;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class DatabaseAccessor implements SQLStrings{

    public static final DatabaseAccessor instance = new DatabaseAccessor();
    public static DatabaseAccessor getInstance() {
        return instance;
    }

    private static final Jdbc3PoolingDataSource poolService = new Jdbc3PoolingDataSource() {{
        setServerName("localhost");
        setDatabaseName("good-samaritan");
        setUser("postgres");
        setPassword("root");
        setMaxConnections(10);
    }};

    public User getUserByLogin(String email, String password) {
        User user = new User();
        try {
            Connection connection = poolService.getConnection();
            PreparedStatement statement = connection.prepareStatement(getUserByLoginSQL);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPoints(rs.getInt("points"));
                user.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Set<MonetaryDonation> getNeededMonetaryDonations() {
        Set<MonetaryDonation> monetaryDonations = new HashSet<>();
        try {
            Connection connection = poolService.getConnection();
            PreparedStatement statement = connection.prepareStatement(getMonetaryDonationsSQL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MonetaryDonation donation = new MonetaryDonation();
                donation.setName(rs.getString("name"));
                donation.setAmount(rs.getInt("amount"));
                donation.setCharity(getCharityById(rs.getInt("charity_id")));
                donation.setPointsAwarded(rs.getInt("points"));
                donation.setDescription(rs.getString("description"));
                monetaryDonations.add(donation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monetaryDonations;
    }

    public Charity getCharityById(int id) {
        Charity charity = new Charity();
        try {
            Connection connection = poolService.getConnection();
            PreparedStatement statement = connection.prepareStatement(getCharityById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                charity.setId(rs.getInt("charity_id"));
                charity.setName(rs.getString("charity_name"));
                charity.setDescription(rs.getString("description"));
                charity.setCategory(rs.getString("category"));
                charity.setLocation(rs.getString("location"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charity;
    }
}
