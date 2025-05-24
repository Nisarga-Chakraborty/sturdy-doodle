import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class FLIPCLOCK 
{
    public static void main(String[] args) 
    {
        Font myfont=new Font("Times New Roman",Font.BOLD,48);
        // Create JFrame
        JFrame frame = new JFrame();
        frame.setTitle("CUSTOM MADE CLOCK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(1, 8)); // 6 slots: HH:MM:SS
        frame.getContentPane().setBackground(Color.black);

        // Create Labels for Hours, Minutes, Seconds
        JLabel hourLabel = new JLabel("00", SwingConstants.CENTER);
        JLabel minuteLabel = new JLabel("00", SwingConstants.CENTER);
        JLabel secondLabel = new JLabel("00", SwingConstants.CENTER);
        
        hourLabel.setFont(new Font("Times New Roman", Font.BOLD, 68));
        minuteLabel.setFont(new Font("Times New Roman", Font.BOLD, 68));
        secondLabel.setFont(new Font("Times New Roman", Font.BOLD, 68));
        
        hourLabel.setForeground(Color.white);
        minuteLabel.setForeground(Color.white);
        secondLabel.setForeground(Color.white);
        
        JLabel one=new JLabel(":",SwingConstants.CENTER);
        JLabel two= new JLabel(":",SwingConstants.CENTER);
        one.setFont(myfont);
        two.setFont(myfont);
        one.setForeground(Color.white);
        two.setForeground(Color.white);

        frame.add(hourLabel);
        frame.add(one);
        frame.add(minuteLabel);
        frame.add(two);
        frame.add(secondLabel);

        // Timer to Update Clock
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() 
        {
            @Override
            public void run() 
            {
                
                LocalTime now = LocalTime.now();
                
                hourLabel.setText(String.format("%02d", now.getHour()));
                minuteLabel.setText(String.format("%02d", now.getMinute()));
                secondLabel.setText(String.format("%02d", now.getSecond()));
            }
        }, 0, 1000);// Update every second

        // Make Frame Visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}



