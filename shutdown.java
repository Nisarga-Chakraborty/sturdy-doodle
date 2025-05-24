/*import java.io.IOException;
import java.util.*;
public class shutdown
{
    public static void main(String[] args) 
    {
        String s;
        Scanner ab =new Scanner(System.in);
        System.out.println(".........................................");
        System.out.println("AT FIRST EITHER CLOSE OR COMPLETE ALL THE USEFUL THINGS U ARE DOING IN YOUR COMPUTER RIGHT NOW.CAUSE U DO NOT WANT TO LOOSE IT");
        System.out.println("later do not blame me");
        System.out.println(" DO YOU WANT TO SHUTDOWN YOUR COMPUTER IMMEDIATELY?....type yes or no");
        s=ab.nextLine();
        if(s.equalsIgnoreCase("yes"))
        {
            try {
                // Command for Windows
                String shutdownCmd = "shutdown -s -t 10"; 
    
                Runtime.getRuntime().exec(shutdownCmd);
                System.out.println("Security has been compromised!..your antivirus is not upto the date...your computer will shutdown in 10 seconds");
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(s.equalsIgnoreCase("no"))
        {
            System.out.println("the computer will not shutdown ");
            System.exit(0);
        }
        else
        {
            System.out.println("invalid Input\n please type yes or no");
        }
        }
        
}*/
import java.io.IOException;
import java.util.*;

public class shutdown {
    public static void main(String[] args) throws IOException {
        String s;
        Scanner ab = new Scanner(System.in);
        System.out.println(".........................................");
        System.out.println("AT FIRST, CLOSE OR COMPLETE ALL USEFUL WORK ON YOUR COMPUTER RIGHT NOW. YOU DON'T WANT TO LOSE IT!");
        System.out.println("Later, do not blame me!");
        System.out.println("DO YOU WANT TO SHUT DOWN YOUR COMPUTER IMMEDIATELY? Type 'yes' or 'no'.");
        s = ab.nextLine();

        if (s.equalsIgnoreCase("yes")) {
            // Command for Windows
            String shutdownCmd = "shutdown -s -t 10";

            Runtime.getRuntime().exec(shutdownCmd);  // No error handling
            System.out.println("Your computer will shut down in 10 seconds.");
        } else if (s.equalsIgnoreCase("no")) {
            System.out.println("The computer will not shut down.");
            System.exit(0);
        } else {
            System.out.println("Invalid input. Please type 'yes' or 'no'.");
        }
    }
}