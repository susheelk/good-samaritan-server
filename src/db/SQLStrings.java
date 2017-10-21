package db;

public interface SQLStrings {
    String getUserByLoginSQL = "SELECT * FROM app.users WHERE email = ? and password = ?";
}
