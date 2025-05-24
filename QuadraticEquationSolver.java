import java.util.Scanner;

public class QuadraticEquationSolver 
{
    public static void main(String[] args) 
    {
        System.out.println("  Hello   BUBKA   AND   CHOTKA  ");
        System.out.println("i am trying to solve any quadratic equation that u enter so please cooperate");
        double root1, root2;
        Scanner ab = new Scanner(System.in);
        System.out.println("Enter the coefficient of x^2 in the quadratic equation:");
        double a = ab.nextDouble();
        System.out.println("Enter the coefficient of x in the equation:");
        double b = ab.nextDouble();
        System.out.println("Enter the constant term in the given equation:");
        double c = ab.nextDouble();
        double discriminant = (b * b) - (4 * a * c);

        if (discriminant > 0) 
        {
            System.out.println("The roots are real and distinct.");
            root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("The roots are: " + root1 + " , " + root2);
        } 
        else if (discriminant == 0) 
        {
            System.out.println("The roots are real and equal.");
            root1 = root2 = -b / (2 * a);
            System.out.println("The roots are: " + root1 + " , " + root2);
        } 
        else 
        {
            System.out.println("The roots are complex and distinct.");
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            System.out.println("The real part of the roots is: " + realPart);
            System.out.println("The imaginary parts are: " + realPart + " + " + imaginaryPart + "i and " + realPart + " - " + imaginaryPart + "i");
        }
    }
}
