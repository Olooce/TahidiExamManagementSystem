import java.sql.*;

public class ExamDAO {
    public boolean createExam(long subjectId, String title) {
        String sql = "CALL create_exam(?, ?)";
        try (Connection conn = Database.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, subjectId);
            stmt.setString(2, title);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean scheduleExam(long examId, Timestamp dateScheduled, int duration, String durationType) {
        String sql = "CALL schedule_exam(?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, examId);
            stmt.setTimestamp(2, dateScheduled);
            stmt.setInt(3, duration);
            stmt.setString(4, durationType);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
