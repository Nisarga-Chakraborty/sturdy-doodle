import javax.swing.*;
import java.awt.*;
import java.io.*;
public class Notepad extends JFrame
{
    JTextArea textArea;// now a class variable
    public Notepad()
    {
         super("NOTEPAD");
         setSize(600,600);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         getContentPane().setBackground(Color.black);// default Color of the Notepad 
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         addGUIComponents();
    }


    public void addGUIComponents()
    {
        addToolBar();
        //add the text area where the user can write
        textArea= new JTextArea();
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Times New Roman",Font.PLAIN,25)); 
        add(textArea,BorderLayout.CENTER);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        // adding the scrolling facility
        JScrollPane scrollPane= new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//can use VERTICAL_SCROLLBAR_AS_NEEDED too
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//can use HORIZONTAL_SCROLLBAR_AS_NEEDED too
        add(scrollPane);
    }

    public void addToolBar()
    {
        JToolBar toolBar= new JToolBar();
        toolBar.setBackground(Color.white);
        toolBar.setFloatable(false);

        JMenuBar menuBar= new JMenuBar();
        toolBar.add(menuBar);
        // creating JMenu
        JMenu fileMenu=new JMenu("File");
        JMenu editMenu= new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu translateMenu = new JMenu("Translate");
        JMenu colorMenu=new JMenu("Color");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(translateMenu);
        menuBar.add(colorMenu);
        menuBar.add(helpMenu);
        //adding thhe items of the menu bar just like in Notepad
        JMenuItem newItem = new JMenuItem("New");
        newItem.setBackground(Color.white);
        newItem.setForeground(Color.black);
        newItem.setFocusable(false);
        JMenuItem openItem=new JMenuItem("Open");
        openItem.setBackground(Color.white);
        openItem.setForeground(Color.black);
        openItem.setFocusable(false);
        JMenuItem saveasItem=new JMenuItem("Save As");
        saveasItem.setBackground(Color.white);
        saveasItem.setForeground(Color.black);
        saveasItem.setFocusable(false);
        JMenuItem exitItem=new JMenuItem("Exit");
        exitItem.setBackground(Color.white);
        exitItem.setForeground(Color.black);
        exitItem.setFocusable(false);
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveasItem);
        fileMenu.add(exitItem);
        JMenuItem cutItem= new JMenuItem("Cut");
        cutItem.setBackground(Color.white);
        cutItem.setForeground(Color.black);
        cutItem.setFocusable(false);
        JMenuItem copyItem= new JMenuItem("Copy");
        copyItem.setBackground(Color.white);
        copyItem.setForeground(Color.black);
        copyItem.setFocusable(false);
        JMenuItem pasteItem= new JMenuItem("Paste");
        pasteItem.setBackground(Color.white);
        pasteItem.setForeground(Color.black);
        pasteItem.setFocusable(false);
        JMenuItem selectallItem= new JMenuItem("Select All");
        selectallItem.setBackground(Color.white);
        selectallItem.setForeground(Color.black);
        selectallItem.setFocusable(false);
        JMenuItem findItem= new JMenuItem("Find");
        findItem.setBackground(Color.white);
        findItem.setForeground(Color.black);
        findItem.setFocusable(false);
        JMenuItem replaceItem= new JMenuItem("Replace");
        replaceItem.setBackground(Color.white);
        replaceItem.setForeground(Color.black);
        replaceItem.setFocusable(false);
        JMenuItem fontItem= new JMenuItem("Font");
        fontItem.setBackground(Color.white);
        fontItem.setForeground(Color.black);
        fontItem.setFocusable(false);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(selectallItem);
        editMenu.add(findItem);
        editMenu.add(replaceItem);
        editMenu.add(fontItem);

        JMenuItem zoominItem=new JMenuItem("Zoom In");
        zoominItem.setForeground(Color.black);
        zoominItem.setBackground(Color.white);
        zoominItem.setFocusable(false);

        JMenuItem zoomoutItem=new JMenuItem("Zoom Out");
        zoomoutItem.setForeground(Color.black);
        zoomoutItem.setBackground(Color.white);
        zoomoutItem.setFocusable(false);

        JMenuItem statusBarItem= new JMenuItem("Status Bar");
        statusBarItem.setBackground(Color.white);
        statusBarItem.setForeground(Color.black);
        statusBarItem.setFocusable(false);

        JMenuItem toolBarItem= new JMenuItem("Tool Bar");
        toolBarItem.setBackground(Color.white);
        toolBarItem.setForeground(Color.black);
        toolBarItem.setFocusable(false);
        viewMenu.add(zoominItem);
        viewMenu.add(zoomoutItem);
        viewMenu.add(statusBarItem);
        viewMenu.add(toolBarItem);

        JMenuItem backgroundColor= new JMenuItem("Background Color");
        backgroundColor.setBackground(Color.white);
        backgroundColor.setForeground(Color.black);
        backgroundColor.setFocusable(false);

        JMenuItem foregroundColor= new JMenuItem("Foreground Color");
        foregroundColor.setBackground(Color.white);
        foregroundColor.setForeground(Color.black);
        foregroundColor.setFocusable(false);

        colorMenu.add(backgroundColor);
        colorMenu.add(foregroundColor);

        JMenuItem aboutItem=new JMenuItem("About");
        aboutItem.setBackground(Color.white);
        aboutItem.setForeground(Color.black);
        JMenuItem feedbackItem=new JMenuItem("Feedback");
        feedbackItem.setBackground(Color.white);
        feedbackItem.setForeground(Color.black);

        helpMenu.add(aboutItem);
        helpMenu.add(feedbackItem);
     
        add(toolBar,BorderLayout.NORTH);
        // saving the file and the writings in JTextArea
        saveasItem.addActionListener(e->
{
    
        JFileChooser fileChooser= new JFileChooser();
    int result=fileChooser.showSaveDialog(this);
    if(result== JFileChooser.APPROVE_OPTION)
    {
        try
        {
            File file=fileChooser.getSelectedFile();
            FileWriter fileWriter= new FileWriter(file);
            fileWriter.write(textArea.getText());
            fileWriter.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Error saving file: "+ex.getMessage());
        }
    }
}); 

        // opening the file
        openItem.addActionListener(e->
{
    JFileChooser fileChooser= new JFileChooser();
    int result=fileChooser.showOpenDialog(this);
    if(result== JFileChooser.APPROVE_OPTION)
    {
        try
        {
            File file=fileChooser.getSelectedFile();
            BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
            textArea.setText("");
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                textArea.append(line+"\n");
            }
            bufferedReader.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Error opening file: "+ex.getMessage());
        }
    }
});
// selecting all the text
        selectallItem.addActionListener(e->
        {
            textArea.selectAll();
        });
        //cutting the text
        cutItem.addActionListener(e->
        {
            textArea.cut();
        });
        //copying the text
        copyItem.addActionListener(e->
        {
            textArea.copy();
        });
        // pasting the text
        pasteItem.addActionListener(e->
        {
            textArea.paste();
        });
        //exiting the notepad
        exitItem.addActionListener(e->
        {
            System.exit(0);
        });
        //new file
        newItem.addActionListener(e->
        {
            textArea.setText("");
            JOptionPane.showMessageDialog(this,"New file created");
        });
        // finding text
        findItem.addActionListener(e->
        {
            String searchText=JOptionPane.showInputDialog(this,"Enter text to find:");
            if(searchText!=null)
            {
                String text=textArea.getText();
                int index=text.indexOf(searchText);
                if(index!=-1)
                {
                    textArea.select(index,index+searchText.length());
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Text not found");
                }
            }
        });
        // replacing text
        replaceItem.addActionListener(e->
        {
            String searchText=JOptionPane.showInputDialog(this,"Enter text to find:");
            if(searchText!=null)
            {
                String replaceText=JOptionPane.showInputDialog(this,"Enter text to replace with:");
                if(replaceText!=null)
                {
                    String text=textArea.getText();
                    text=text.replace(searchText,replaceText);
                    textArea.setText(text);
                }
            }
        });
        // font
        fontItem.addActionListener(e->
        {
            String fontName=JOptionPane.showInputDialog(this,"Enter the font Name....Calibri,Arial,etc");
            if(fontName!=null)
            {
                String fontSize= JOptionPane.showInputDialog(this,"Enter the font size....11,12,13, etc.");
                if(fontSize!=null)
                {
                    String fontchoice=JOptionPane.showInputDialog(this,"enter the choice of font....BOLD,ITALIC, etc");
                    fontchoice=fontchoice.toUpperCase();
                    if(fontchoice.equalsIgnoreCase("BOLD"))
                    {
                        Font font = new Font(fontName,Font.BOLD,Integer.parseInt(fontSize));
                        textArea.setFont(font);
                    }
                    else if(fontchoice.equalsIgnoreCase("ITALIC"))
                    {
                        Font font = new Font(fontName,Font.ITALIC,Integer.parseInt(fontSize));
                        textArea.setFont(font);
                    }
                    else
                    {
                        Font font = new Font(fontName,Font.PLAIN,Integer.parseInt(fontSize));
                        textArea.setFont(font);
                    }
                }
            }
        });
        // zoom in 
        zoominItem.addActionListener(e->
        {
            Font font=textArea.getFont();
            int size=font.getSize();
            if(size<100)
            {
                size=size+5;
                textArea.setFont(new Font(font.getFontName(),font.getStyle(),size));
            }
            else
            JOptionPane.showMessageDialog(this,"the font size is too large more than 100");
        });
        // zoom out
        zoomoutItem.addActionListener(e->
        {
            Font font = textArea.getFont();
            int size= font.getSize();
            if(size>5)
            {
                size=size-5;
                textArea.setFont(new Font(font.getFontName(),font.getStyle(),(font.getSize())-2));
            }
            else
            {
                JOptionPane.showMessageDialog(this,"the font size is too small less than 5");
            }
        });
        // adding color to text
        /*foregroundColor.addActionListener(e->
        {
            String fgc=JOptionPane.showInputDialog("enter the text color");
            if(fgc.equalsIgnoreCase("black"))
            {
                textArea.setForeground(Color.black);
            }
            else if(fgc.equalsIgnoreCase("white"))
            {
                textArea.setForeground(Color.white);
            }
            else if(fgc.equalsIgnoreCase("green"))
            {
                textArea.setForeground(Color.green);
            }
            else if(fgc.equalsIgnoreCase("gray"))
            {
                textArea.setForeground(Color.gray);
            }
            else if(fgc.equalsIgnoreCase("blue"))
            {
                textArea.setForeground(Color.blue);
            }
            else if(fgc.equalsIgnoreCase("red"))
            {
                textArea.setForeground(Color.red);
            }
            else if(fgc.equalsIgnoreCase("yellow"))
            {
                textArea.setForeground(Color.yellow);
            }
            else if(fgc.equalsIgnoreCase("orange"))
            {
                textArea.setForeground(Color.orange);
            }
            else if(fgc.equalsIgnoreCase("pink"))
            {
                textArea.setForeground(Color.pink);
            }
            else if(fgc.equalsIgnoreCase("dark grey"))
            {
                textArea.setForeground(Color.darkGray);
            }
            else if(fgc.equalsIgnoreCase("light grey"))
            {
                textArea.setForeground(Color.lightGray);
            }
            else if(fgc.equalsIgnoreCase("cyan"))
            {
                textArea.setForeground(Color.cyan);
            }
            else if(fgc.equalsIgnoreCase("magenta"))
            {
                textArea.setForeground(Color.magenta);
            }
            else
            {
                JOptionPane.showMessageDialog(this," Sorry couldn't set the color as Foreground");
            }

        });
        //set Background color
        backgroundColor.addActionListener(e->
        {
            String bgc=JOptionPane.showInputDialog("enter the background color");
            if(bgc.equalsIgnoreCase("black"))
            {
                textArea.setBackground(Color.black);
            }
            else if(bgc.equalsIgnoreCase("white"))
            {
                textArea.setBackground(Color.white);
            }
            else if(bgc.equalsIgnoreCase("green"))
            {
                textArea.setBackground(Color.green);
            }
            else if(bgc.equalsIgnoreCase("gray"))
            {
                textArea.setBackground(Color.gray);
            }
            else if(bgc.equalsIgnoreCase("blue"))
            {
                textArea.setBackground(Color.blue);
            }
            else if(bgc.equalsIgnoreCase("red"))
            {
                textArea.setBackground(Color.red);
            }
            else if(bgc.equalsIgnoreCase("yellow"))
            {
                textArea.setBackground(Color.yellow);
            }
            else if(bgc.equalsIgnoreCase("orange"))
            {
                textArea.setBackground(Color.orange);
            }
            else if(bgc.equalsIgnoreCase("pink"))
            {
                textArea.setBackground(Color.pink);
            }
            else if(bgc.equalsIgnoreCase("dark grey"))
            {
                textArea.setBackground(Color.darkGray);
            }
            else if(bgc.equalsIgnoreCase("light grey"))
            {
                textArea.setBackground(Color.lightGray);
            }
            else
            {
                JOptionPane.showMessageDialog(this," Sorry couldn't set the color as Background ");
            }
        });*/
        // For text (foreground) color
foregroundColor.addActionListener(e -> 
{
    Color current = textArea.getForeground();
    Color newColor = JColorChooser.showDialog(Notepad.this,"Choose Text Color",current);
        
    if (newColor != null) {  // Only change if user didn't cancel
        textArea.setForeground(newColor);
    }
});

// For background color
backgroundColor.addActionListener(e -> 
{
    Color current = textArea.getBackground();
    Color newColor = JColorChooser.showDialog(this,"Choose Background Color",current);
        
    if (newColor != null) 
    {
        textArea.setBackground(newColor);
    }
});
        //showing the status bar
        statusBarItem.addActionListener(e->
        {
            JOptionPane.showMessageDialog(this," Sorry Status Bar is not yet implemented");
        });
        //showing the tool bar
        toolBarItem.addActionListener(e->
        {
            JOptionPane.showMessageDialog(this," Sorry Tool Bar is not yet implemented");
        });
        
        //showing about
        aboutItem.addActionListener(e->
        {
            JOptionPane.showMessageDialog(this," Notepad is a simple text editor for Windows, included in most versions of Microsoft Windows. It is a basic text-editing program that enables you to create and edit plain text files. \n\n This is a simple implementation of Notepad using Java. ");
        });
        //showing feedback
        feedbackItem.addActionListener(e->
        {
            String s=JOptionPane.showInputDialog(this,"FEEL FREE TO SUGGEST SOME IMPROVEMENTS \n We look forward to it");
            if(s.equals(""))
            {
                JOptionPane.showMessageDialog(this," Please enter a feedback");
            }
            else
            {
                JOptionPane.showMessageDialog(this," thank you for your feedback");
                System.out.println("Feedback: "+s);
            }
            
        });
        //translate 
        translateMenu.addActionListener(e->
        {
            JOptionPane.showMessageDialog(this," Sorry Translate is not yet implemented");
        });

}

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Notepad().setVisible(true);
            }
        });
    }
}
