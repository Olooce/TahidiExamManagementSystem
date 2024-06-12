import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class MainGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField dobField;
    private UserDAO userDAO;
    private ExamDAO examDAO;

    public MainGUI() {
        userDAO = new UserDAO();
        examDAO = new ExamDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("School Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblUsername = new JLabel("Username");
        panel.add(lblUsername);

        usernameField = new JTextField();
        panel.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        JLabel lblName = new JLabel("Name");
        panel.add(lblName);

        nameField = new JTextField();
        panel.add(nameField);
        nameField.setColumns(10);

        JLabel lblDob = new JLabel("Date of Birth (YYYY-MM-DD)");
        panel.add(lblDob);

        dobField = new JTextField();
        panel.add(dobField);
        dobField.setColumns(10);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                Date dob = Date.valueOf(dobField.getText());

                boolean success = userDAO.registerUser(username, password, "PUPIL", name, dob);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "User registered successfully");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error registering user");
                }
            }
        });
        panel.add(btnRegister);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGUI window = new MainGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
