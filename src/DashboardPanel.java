import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private MainFrame mainFrame;

    public DashboardPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard", SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
    }
}
