import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExamManagementPanel extends JPanel {
    private MainFrame mainFrame;

    private JTextField examTitleField;
    private JComboBox<String> subjectComboBox;

    public ExamManagementPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        JLabel examTitleLabel = new JLabel("Exam Title:");
        JLabel subjectLabel = new JLabel("Subject:");

        examTitleField = new JTextField(20);
        subjectComboBox = new JComboBox<>();

        JButton createExamButton = new JButton("Create Exam");
        createExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createExam();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(examTitleLabel, gbc);

        gbc.gridx = 1;
        add(examTitleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(subjectLabel, gbc);

        gbc.gridx = 1;
        add(subjectComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        add(createExamButton, gbc);

        loadSubjects();
    }

    private void loadSubjects() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT subject_id, subject_title FROM subjects";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    subjectComboBox.addItem(rs.getString("subject_title"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createExam() {
        String examTitle = examTitleField.getText();
        String subjectTitle = (String) subjectComboBox.getSelectedItem();

        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO exams (subject_id, title) VALUES ((SELECT subject_id FROM subjects WHERE subject_title = ?), ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, subjectTitle);
                stmt.setString(2, examTitle);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Exam created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
