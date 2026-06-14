import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class ClockPanel extends JPanel {
    public ClockPanel() {
        Timer timer = new Timer(1000, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2 - 20;

        // Draw Clock Face Background
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw Clock Border
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw 60 Minute/Second Tick Marks
        for (int i = 0; i < 60; i++) {
            // Calculate angle for each tick (360 degrees / 60 = 6 degrees per tick)
            double angle = Math.toRadians((i * 6) - 90);

            // The outer edge of the tick touches the clock border
            int outerX = centerX + (int) (radius * Math.cos(angle));
            int outerY = centerY + (int) (radius * Math.sin(angle));

            // adjust length and thickness based on if it's an hour mark
            boolean isHourMark = (i % 5 == 0);
            int tickLength = isHourMark ? 12 : 5;
            g2d.setStroke(new BasicStroke(isHourMark ? 3 : 1));

            // The inner edge of the tick
            int innerX = centerX + (int) ((radius - tickLength) * Math.cos(angle));
            int innerY = centerY + (int) ((radius - tickLength) * Math.sin(angle));

            g2d.drawLine(innerX, innerY, outerX, outerY);
        }

        // Draw Hour Numbers (1-12)
        g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
        FontMetrics fm = g2d.getFontMetrics();
        // Moved numbers slightly further inside to clear the new hour tick marks
        int textRadius = radius - 32;

        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians((i * 30) - 90);
            int numX = centerX + (int) (textRadius * Math.cos(angle));
            int numY = centerY + (int) (textRadius * Math.sin(angle));

            String numStr = String.valueOf(i);
            int strWidth = fm.stringWidth(numStr);
            int strHeight = fm.getAscent();

            g2d.drawString(numStr, numX - (strWidth / 2), numY + (strHeight / 4));
        }

        // Get System Time
        LocalTime time = LocalTime.now();
        int sec = time.getSecond();
        int min = time.getMinute();
        int hour = time.getHour() % 12;

        // Draw Hands
        drawHand(g2d, centerX, centerY, sec * 6 - 90, radius - 15, 2, Color.RED); // Second hand
        drawHand(g2d, centerX, centerY, min * 6 + sec * 0.1 - 90, radius - 25, 4, Color.DARK_GRAY); // Minute hand
        drawHand(g2d, centerX, centerY, hour * 30 + min * 0.5 - 90, radius - 45, 6, Color.BLACK); // Hour hand
    }

    private void drawHand(Graphics2D g2d, int x, int y, double angle, int length, int stroke, Color color) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(stroke));
        double radians = Math.toRadians(angle);
        int endX = x + (int) (length * Math.cos(radians));
        int endY = y + (int) (length * Math.sin(radians));
        g2d.drawLine(x, y, endX, endY);
    }
}