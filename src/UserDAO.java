import java.sql.*;

public class UserDAO {
    public boolean registerUser(String username, String passwordHash, String role, String name, Date dob) {
        String sql = "CALL register_user(?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL)";
        try (Connection conn = Database.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            stmt.setString(3, role);
            stmt.setString(4, name);
            stmt.setDate(5, dob);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(String username, String newPasswordHash) {
        String sql = "CALL update_password(?, ?)";
        try (Connection conn = Database.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, newPasswordHash);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean activateUser(long credentialId, long staffId) {
        String sql = "CALL activate_user(?, ?)";
        try (Connection conn = Database.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, credentialId);
            stmt.setLong(2, staffId);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
