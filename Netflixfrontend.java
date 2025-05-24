import javax.swing.*;
import java.awt.*;

public class Netflixfrontend 
{
    public static void main(String[] args) 
    {
        // Create JFrame
        JFrame frame = new JFrame("Netflix-Inspired Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());

        // Title Panel
        JLabel title = new JLabel("Welcome to Netflix Inspired", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.RED);
        frame.add(title, BorderLayout.NORTH);

        // Movies Panel (Center)
        JPanel moviesPanel = new JPanel();
        moviesPanel.setBackground(Color.BLACK);
        moviesPanel.setLayout(new GridLayout(2, 5, 10, 10)); // 2 rows, 5 columns

        for (int i = 1; i <= 10; i++) 
        {
            JButton movieButton = new JButton("Movie " + i);
            movieButton.setFont(new Font("Arial", Font.BOLD, 16));
            movieButton.setForeground(Color.WHITE);
            movieButton.setBackground(Color.DARK_GRAY);
            movieButton.setFocusable(false);
            moviesPanel.add(movieButton);
        }
        frame.add(moviesPanel, BorderLayout.CENTER);

        // Footer Panel
        JLabel footer = new JLabel("Enjoy Streaming! | No Backend Yet 😉", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 16));
        footer.setForeground(Color.GRAY);
        frame.add(footer, BorderLayout.SOUTH);

        // Wrap the movies panel inside a scroll pane
      JScrollPane scrollPane = new JScrollPane(moviesPanel);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      // Add scroll pane to frame instead of moviesPanel
      frame.add(scrollPane, BorderLayout.CENTER);


        // Make Frame Visible
        frame.setVisible(true);
    }
}