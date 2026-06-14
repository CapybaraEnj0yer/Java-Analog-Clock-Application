import javax.swing.*;
import java.awt.*;

public class TimeApplication extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public TimeApplication() {
        setTitle("Time Application");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // borderLayout for the main frame

        // Create tab bar with grid layout
        JPanel tabBar = new JPanel(new GridLayout(1, 3));

        JToggleButton clockBtn = new JToggleButton("Analog Clock");
        JToggleButton stopBtn = new JToggleButton("Stopwatch");
        JToggleButton timerBtn = new JToggleButton("Timer");

        // Group the buttons so only one can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(clockBtn);
        buttonGroup.add(stopBtn);
        buttonGroup.add(timerBtn);

        tabBar.add(clockBtn);
        tabBar.add(stopBtn);
        tabBar.add(timerBtn);

        // Create the content area using CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add Time panels to the card panel
        cardPanel.add(new ClockPanel(), "CLOCK");
        cardPanel.add(new StopwatchPanel(), "STOPWATCH");
        cardPanel.add(new TimerPanel(), "TIMER");

        // 3. Add Actions to switch the cards when tabs are clicked
        clockBtn.addActionListener(e -> cardLayout.show(cardPanel, "CLOCK"));
        stopBtn.addActionListener(e -> cardLayout.show(cardPanel, "STOPWATCH"));
        timerBtn.addActionListener(e -> cardLayout.show(cardPanel, "TIMER"));

        // Select the first tab by default
        clockBtn.setSelected(true);

        add(tabBar, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimeApplication().setVisible(true));
    }
}