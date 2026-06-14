import javax.swing.*;
import java.awt.*;

public class StopwatchPanel extends JPanel {
    private long startTime;
    private long elapsedTime = 0;
    private boolean running = false;
    private JLabel timeLabel;
    private Timer timer;

    public StopwatchPanel() {
        setLayout(new BorderLayout());
        timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        JButton resetBtn = new JButton("Reset");

        timer = new Timer(10, e -> updateDisplay());

        startBtn.addActionListener(e -> {
            if (!running) {
                startTime = System.currentTimeMillis() - elapsedTime;
                timer.start();
                running = true;
            }
        });

        stopBtn.addActionListener(e -> {
            if (running) {
                timer.stop();
                running = false;
            }
        });

        resetBtn.addActionListener(e -> {
            timer.stop();
            running = false;
            elapsedTime = 0;
            timeLabel.setText("00:00:000");
        });

        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);
        buttonPanel.add(resetBtn);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateDisplay() {
        elapsedTime = System.currentTimeMillis() - startTime;
        long millis = elapsedTime % 1000;
        long secs = (elapsedTime / 1000) % 60;
        long mins = (elapsedTime / 60000) % 60;
        timeLabel.setText(String.format("%02d:%02d:%03d", mins, secs, millis));
    }
}