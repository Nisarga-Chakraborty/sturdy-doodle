import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Notepad1 extends JFrame {
    JTextArea textArea;

    public Notepad1() 
    {
        super("NOTEPAD ");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        addGUIComponents();
    }

    public void addGUIComponents() {
        addToolBar();
        textArea = new JTextArea();
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(Color.white);
        toolBar.setFloatable(false);

        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);

        // Menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu aiMenu = new JMenu("AI");
        JMenu colorMenu = new JMenu("Color");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(aiMenu);
        menuBar.add(colorMenu);
        menuBar.add(helpMenu);

        // File Menu Items
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveasItem = new JMenuItem("Save As");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveasItem);
        fileMenu.add(exitItem);

        // Edit Menu Items
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem selectallItem = new JMenuItem("Select All");
        JMenuItem findItem = new JMenuItem("Find");
        JMenuItem replaceItem = new JMenuItem("Replace");
        JMenuItem fontItem = new JMenuItem("Font");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(selectallItem);
        editMenu.add(findItem);
        editMenu.add(replaceItem);
        editMenu.add(fontItem);

        // View Menu Items
        JMenuItem zoominItem = new JMenuItem("Zoom In");
        JMenuItem zoomoutItem = new JMenuItem("Zoom Out");
        JMenuItem statusBarItem = new JMenuItem("Status Bar");
        JMenuItem toolBarItem = new JMenuItem("Tool Bar");
        viewMenu.add(zoominItem);
        viewMenu.add(zoomoutItem);
        viewMenu.add(statusBarItem);
        viewMenu.add(toolBarItem);

        // AI Menu Items
        JMenuItem grammarCheckItem = new JMenuItem("Check Grammar");
        aiMenu.add(grammarCheckItem);

        // Color Menu Items
        JMenuItem backgroundColor = new JMenuItem("Background Color");
        JMenuItem foregroundColor = new JMenuItem("Foreground Color");
        colorMenu.add(backgroundColor);
        colorMenu.add(foregroundColor);

        // Help Menu Items
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem feedbackItem = new JMenuItem("Feedback");
        helpMenu.add(aboutItem);
        helpMenu.add(feedbackItem);

        add(toolBar, BorderLayout.NORTH);

        // File actions
        saveasItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(textArea.getText());
                    fileWriter.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
        });

        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    textArea.setText("");
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    bufferedReader.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
                }
            }
        });

        selectallItem.addActionListener(e -> textArea.selectAll());
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        exitItem.addActionListener(e -> System.exit(0));
        newItem.addActionListener(e -> {
            textArea.setText("");
            JOptionPane.showMessageDialog(this, "New file created");
        });

        findItem.addActionListener(e -> {
            String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
            if (searchText != null) {
                String text = textArea.getText();
                int index = text.indexOf(searchText);
                if (index != -1) {
                    textArea.select(index, index + searchText.length());
                } else {
                    JOptionPane.showMessageDialog(this, "Text not found");
                }
            }
        });

        replaceItem.addActionListener(e -> {
            String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
            if (searchText != null) {
                String replaceText = JOptionPane.showInputDialog(this, "Enter text to replace with:");
                if (replaceText != null) {
                    String text = textArea.getText();
                    text = text.replace(searchText, replaceText);
                    textArea.setText(text);
                }
            }
        });

        fontItem.addActionListener(e -> {
            String fontName = JOptionPane.showInputDialog(this, "Enter the font Name (e.g., Calibri, Arial):");
            if (fontName != null) {
                String fontSize = JOptionPane.showInputDialog(this, "Enter the font size (e.g., 12, 14):");
                if (fontSize != null) {
                    String fontchoice = JOptionPane.showInputDialog(this, "Enter the choice of font (BOLD, ITALIC, PLAIN):");
                    fontchoice = fontchoice == null ? "PLAIN" : fontchoice.toUpperCase();
                    int style = Font.PLAIN;
                    if (fontchoice.equals("BOLD")) style = Font.BOLD;
                    else if (fontchoice.equals("ITALIC")) style = Font.ITALIC;
                    Font font = new Font(fontName, style, Integer.parseInt(fontSize));
                    textArea.setFont(font);
                }
            }
        });

        zoominItem.addActionListener(e -> {
            Font font = textArea.getFont();
            int size = font.getSize();
            if (size < 100) {
                size = size + 5;
                textArea.setFont(new Font(font.getFontName(), font.getStyle(), size));
            } else {
                JOptionPane.showMessageDialog(this, "The font size is too large (more than 100)");
            }
        });

        zoomoutItem.addActionListener(e -> {
            Font font = textArea.getFont();
            int size = font.getSize();
            if (size > 5) {
                size = size - 5;
                textArea.setFont(new Font(font.getFontName(), font.getStyle(), size));
            } else {
                JOptionPane.showMessageDialog(this, "The font size is too small (less than 5)");
            }
        });

        // Set background color
        backgroundColor.addActionListener(e -> {
            String bgc = JOptionPane.showInputDialog("Enter the background color (e.g., black, white, green):");
            Color color = getColorFromString(bgc);
            if (color != null) {
                textArea.setBackground(color);
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, couldn't set the color as Background");
            }
        });

        // Set foreground color
        foregroundColor.addActionListener(e -> {
            String fgc = JOptionPane.showInputDialog("Enter the text color (e.g., black, white, green):");
            Color color = getColorFromString(fgc);
            if (color != null) {
                textArea.setForeground(color);
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, couldn't set the color as Foreground");
            }
        });

        // About
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Notepad with AI Grammar Check\nPowered by Java Swing and LanguageTool API"));

        // Feedback
        feedbackItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Thank you for your feedback!"));

        // AI Grammar Check
        grammarCheckItem.addActionListener(e -> checkGrammar());
    }

    // Helper to convert color name to Color object
    private Color getColorFromString(String colorName) {
        if (colorName == null) return null;
        switch (colorName.toLowerCase()) {
            case "black": return Color.black;
            case "white": return Color.white;
            case "green": return Color.green;
            case "gray": return Color.gray;
            case "blue": return Color.blue;
            case "red": return Color.red;
            case "yellow": return Color.yellow;
            case "orange": return Color.orange;
            case "pink": return Color.pink;
            case "dark gray": return Color.darkGray;
            case "light gray": return Color.lightGray;
            case "cyan": return Color.cyan;
            case "magenta": return Color.magenta;
            default: return null;
        }
    }

    // AI Grammar Check using LanguageTool API
    public void checkGrammar() {
        try {
            String text = textArea.getText();
            if (text.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No text to check!");
                return;
            }
            String urlParameters = "text=" + URLEncoder.encode(text, "UTF-8") + "&language=en-US";
            URL url = new URL("https://api.languagetool.org/v2/check");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.getOutputStream().write(urlParameters.getBytes("UTF-8"));

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) response.append(line);
            in.close();

            // Simple extraction of matches (for demonstration)
            Pattern p = Pattern.compile("\"message\":\"(.*?)\"");
            Matcher m = p.matcher(response.toString());
            StringBuilder issues = new StringBuilder();
            int count = 0;
            while (m.find()) {
                issues.append("- ").append(m.group(1)).append("\n");
                count++;
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(this, "No grammar issues found!");
            } else {
                JOptionPane.showMessageDialog(this, "Grammar Issues Found:\n" + issues.toString());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error during grammar check: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notepad1().setVisible(true));
    }
}

