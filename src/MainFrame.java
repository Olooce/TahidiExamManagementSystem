import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        super("Tahidi School");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different views
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new DashboardPanel(this), "Dashboard");

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Show login panel initially
        showPanel("Login");
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
