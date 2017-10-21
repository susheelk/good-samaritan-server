package db;

import model.User;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
