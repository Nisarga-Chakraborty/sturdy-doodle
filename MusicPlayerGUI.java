import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MusicPlayerGUI extends JFrame 
{
    JButton playButton;
    JButton pauseButton;
    public static final Color FRAME_COLOR= Color.BLACK;
     
    public MusicPlayerGUI()
    {
        
        super("MUSIC PLAYER");
        setSize(600,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.black);

        addGUIComponents();
    }

    private void addGUIComponents()
    {
        addToolbar();

        //create song label
        JLabel songTitle=new JLabel("Song Title");
        songTitle.setBounds(0,285,getWidth()-10,38);
        songTitle.setFont(new Font("Dialog",Font.BOLD,20));
        songTitle.setForeground(Color.WHITE);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);
        //display song artist
        JLabel songArtist= new JLabel("Artist");
        songArtist.setBounds(0,320,getWidth()-10,30);
        songArtist.setFont(new Font("Calibri",Font.PLAIN,20));
        songArtist.setForeground(Color.WHITE);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);
        //display song duration
        /*JProgressBar songprogressbar=new JProgressBar();
        songprogressbar.setBounds(30,350,getWidth()-10,20);
        songprogressbar.setValue(0);
        songprogressbar.setStringPainted(true);
        songprogressbar.setForeground(Color.GREEN);
        songprogressbar.setBackground(Color.BLACK);
        songprogressbar.setFont(new Font("Dialog",Font.BOLD,15));
        songprogressbar.setString("0.00/0.00");
        add(songprogressbar);*/
        //create play back slider
        JSlider playbackSlider=new JSlider(JSlider.HORIZONTAL,0,100,0);
        playbackSlider.setBounds(getWidth()/2-150,400,300,50);
        playbackSlider.setValue(0);
        playbackSlider.setMinimum(0);
        playbackSlider.setMaximum(100);
        playbackSlider.setBackground(null);
        playbackSlider.setForeground(Color.WHITE);
        playbackSlider.setMajorTickSpacing(10);;
        playbackSlider.setMinorTickSpacing(1);
        playbackSlider.setPaintTicks(true);
        playbackSlider.setPaintLabels((true));
        add(playbackSlider); 
        addPlaybackBtns();  
    }
    private void addToolbar()
    {
        JToolBar toolBar=new JToolBar();
        toolBar.setBounds(0,0,getWidth(),20);
        toolBar.setFloatable(false);
        toolBar.setBackground(FRAME_COLOR);
        add(toolBar);
        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);
        JMenu songMenu=new JMenu("Song");
        menuBar.add(songMenu);
        JMenu playlistMenu = new JMenu("Play List");
        menuBar.add(playlistMenu);
        JMenuItem playSong=new JMenuItem("Play Song");
        songMenu.add(playSong);
        JMenuItem loadSong=new JMenuItem("Load Song");
        songMenu.add(loadSong);
        JMenuItem createPlaylist=new JMenuItem("Create Playlist");
        playlistMenu.add(createPlaylist);
        JMenuItem loadPlaylist= new JMenuItem("Load Playlist");
        playlistMenu.add(loadPlaylist);
        

    }
    private  void addPlaybackBtns()
    {
        
            // Create a panel to hold playback buttons
            JPanel playbackBtns = new JPanel();
            playbackBtns.setBounds(0, 470, getWidth() - 10, 80);
            playbackBtns.setBackground(null);
            playbackBtns.setForeground(Color.WHITE);
        
            // Previous Button
            JButton prevButton = new JButton("<<");
            prevButton.setBackground(Color.BLACK);
            prevButton.setForeground(Color.WHITE);
            prevButton.setFont(new Font("Dialog", Font.BOLD, 30));
            prevButton.setBorderPainted(false);
            prevButton.setFocusPainted(false);
            prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            prevButton.setPreferredSize(new Dimension(100, 50));
            playbackBtns.add(prevButton);
        
            // Play Button
            JButton playButton = new JButton("P"); // Play icon 
            playButton.setBackground(Color.BLACK);
            playButton.setForeground(Color.WHITE);
            playButton.setFont(new Font("Dialog", Font.BOLD, 30));
            playButton.setBorderPainted(false);
            playButton.setFocusPainted(false);
            playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            playButton.setPreferredSize(new Dimension(100, 50));
            playbackBtns.add(playButton);
        
            // Next Button
            JButton nextButton = new JButton(">>");
            nextButton.setBackground(Color.BLACK);
            nextButton.setForeground(Color.WHITE);
            nextButton.setFont(new Font("Dialog", Font.BOLD, 30));
            nextButton.setBorderPainted(false);
            nextButton.setFocusPainted(false);
            nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            nextButton.setPreferredSize(new Dimension(100, 50));
            playbackBtns.add(nextButton);
        
            add(playbackBtns);
    }
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                 new MusicPlayerGUI().setVisible(true);
            }
        });
    }
}
