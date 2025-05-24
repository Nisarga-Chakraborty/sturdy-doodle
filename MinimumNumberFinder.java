import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinimumNumberFinder extends JFrame implements ActionListener 
{

    private JTextField number1Field, number2Field, number3Field, number4Field, resultField;
    private JButton findMinButton;

    public MinimumNumberFinder() 
    {
        setTitle("Minimum Number Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridLayout(6, 2));

        // Initialize components
        JLabel number1Label = new JLabel("Enter number 1:");
        JLabel number2Label = new JLabel("Enter number 2:");
        JLabel number3Label = new JLabel("Enter number 3:");
        JLabel number4Label = new JLabel("Enter number 4:");
        JLabel resultLabel = new JLabel("Minimum number:");
        
        number1Field = new JTextField();
        number2Field = new JTextField();
        number3Field = new JTextField();
        number4Field = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        findMinButton = new JButton("Find Minimum");

        // Set colors
        number1Label.setForeground(Color.WHITE);
        number2Label.setForeground(Color.WHITE);
        number3Label.setForeground(Color.WHITE);
        number4Label.setForeground(Color.WHITE);
        resultLabel.setForeground(Color.WHITE);
        resultField.setForeground(Color.BLACK);

        findMinButton.addActionListener(this);

        // Add components to the frame
        add(number1Label);
        add(number1Field);
        add(number2Label);
        add(number2Field);
        add(number3Label);
        add(number3Field);
        add(number4Label);
        add(number4Field);
        add(resultLabel);
        add(resultField);
        add(findMinButton);
        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == findMinButton) 
        {
            //try {
                double num1 = Double.parseDouble(number1Field.getText());
                double num2 = Double.parseDouble(number2Field.getText());
                double num3 = Double.parseDouble(number3Field.getText());
                double num4 = Double.parseDouble(number4Field.getText());

                double min = Math.min(Math.min(num1, num2), Math.min(num3, num4));
                resultField.setText(String.valueOf(min));
        } //catch (NumberFormatException ex) {
                //JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            //}
    }
    

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(MinimumNumberFinder::new);
        MinimumNumberFinder ok= new MinimumNumberFinder();
    }
}
