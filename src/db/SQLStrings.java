package db;

public interface SQLStrings {
    String getUserByLoginSQL = "SELECT * FROM app.users WHERE email = ? and password = ?";

    String getMonetaryDonationsSQL = "SELECT * FROM  app.monetary_donations_needed";

    String getCharityById = "SELECT * FROM app.charities where charity_id = ?";
}
