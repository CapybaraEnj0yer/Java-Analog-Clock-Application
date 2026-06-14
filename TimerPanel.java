import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    private int timeLeftInSeconds = 0;
    private JLabel displayLabel;
    private Timer timer;
    private JTextField minField, secField;

    public TimerPanel() {
        setLayout(new BorderLayout());
        displayLabel = new JLabel("00:00", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
        add(displayLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        minField = new JTextField("0", 3);
        secField = new JTextField("0", 3);
        inputPanel.add(new JLabel("Mins:"));
        inputPanel.add(minField);
        inputPanel.add(new JLabel("Secs:"));
        inputPanel.add(secField);

        JButton startBtn = new JButton("Start");

        timer = new Timer(1000, e -> {
            if (timeLeftInSeconds > 0) {
                timeLeftInSeconds--;
                updateDisplay();
            } else {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(this, "Time's up!");
            }
        });

        startBtn.addActionListener(e -> {
            try {
                int mins = Integer.parseInt(minField.getText());
                int secs = Integer.parseInt(secField.getText());
                timeLeftInSeconds = (mins * 60) + secs;
                updateDisplay();
                timer.start();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers.");
            }
        });

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(inputPanel);
        bottomPanel.add(startBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateDisplay() {
        int m = timeLeftInSeconds / 60;
        int s = timeLeftInSeconds % 60;
        displayLabel.setText(String.format("%02d:%02d", m, s));
    }
}