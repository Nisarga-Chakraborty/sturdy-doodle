/*import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends JPanel 
{
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        // Draw clock face
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Draw hour markers
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            int x1 = centerX + (int) (radius * 0.9 * Math.cos(angle));
            int y1 = centerY + (int) (radius * 0.9 * Math.sin(angle));
            int x2 = centerX + (int) (radius * 0.8 * Math.cos(angle));
            int y2 = centerY + (int) (radius * 0.8 * Math.sin(angle));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Get current time
        LocalTime now = LocalTime.now();
        int hours = now.getHour() %12;
        int minutes = now.getMinute();
        int seconds = now.getSecond();

        // Calculate angles for hands
        double hourAngle = Math.toRadians((hours + minutes / 60.0) * 30 - 90);
        double minuteAngle = Math.toRadians((minutes + seconds / 60.0) * 6 - 90);
        double secondAngle = Math.toRadians(seconds * 6 - 90);

        // Draw hour hand
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(centerX, centerY, centerX + (int) (radius * 0.5 * Math.cos(hourAngle)),
                centerY + (int) (radius * 0.5 * Math.sin(hourAngle)));

        // Draw minute hand
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(centerX, centerY, centerX + (int) (radius * 0.7 * Math.cos(minuteAngle)),
                centerY + (int) (radius * 0.7 * Math.sin(minuteAngle)));

        // Draw second hand
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(centerX, centerY, centerX + (int) (radius * 0.9 * Math.cos(secondAngle)),
                centerY + (int) (radius * 0.9 * Math.sin(secondAngle)));
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Round Clock");
        Clock clock = new Clock();

        frame.add(clock);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Timer to repaint clock every second
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                clock.repaint();
            }
        }, 0, 1000);
    }
}*/
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;

public class Clock extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        // Draw clock face
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Draw hour markers
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            int x1 = centerX + (int) (radius * 0.9 * Math.cos(angle));
            int y1 = centerY + (int) (radius * 0.9 * Math.sin(angle));
            int x2 = centerX + (int) (radius * 0.8 * Math.cos(angle));
            int y2 = centerY + (int) (radius * 0.8 * Math.sin(angle));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Get current time
        LocalTime now = LocalTime.now();
        int hours = now.getHour() % 12;
        hours = (hours == 0) ? 12 : hours; // Fix 12-hour format
        int minutes = now.getMinute();
        int seconds = now.getSecond();

        // Debugging time output
        System.out.println("Current Time: " + now.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));

        // Calculate angles for hands
        double hourAngle = Math.toRadians((hours + minutes / 60.0) * 30 - 90);
        double minuteAngle = Math.toRadians((minutes + seconds / 60.0) * 6 - 90);
        double secondAngle = Math.toRadians(seconds * 6 - 90);

        // Draw hour hand
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(centerX, centerY, centerX + (int) (radius * 0.5 * Math.cos(hourAngle)),
                centerY + (int) (radius * 0.5 * Math.sin(hourAngle)));

        // Draw minute hand
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(centerX, centerY, centerX + (int) (radius * 0.7 * Math.cos(minuteAngle)),
                centerY + (int) (radius * 0.7 * Math.sin(minuteAngle)));

        // Draw second hand
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(centerX, centerY, centerX + (int) (radius * 0.9 * Math.cos(secondAngle)),
                centerY + (int) (radius * 0.9 * Math.sin(secondAngle)));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Round Clock");
        Clock clock = new Clock();

        frame.add(clock);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Use Swing Timer for smooth updates
        new Timer(1000, e -> clock.repaint()).start();
    }
}
