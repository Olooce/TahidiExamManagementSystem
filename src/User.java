import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private long credentialId;
    private String username;
    private String passwordHash;
    private String role;
    private String name;
    private Date dob;
    private String userStatus;
    private String userStatusDescription;
    private String passwordStatus;
    private String passwordStatusDescription;
    private String passwordResetCode;
    private Timestamp passwordLastSet;
    private long groupId;

    // Getters and setters for all fields
}
