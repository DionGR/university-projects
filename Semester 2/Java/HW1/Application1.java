import java.util.Scanner;

/*************************************************************
 *  Compilation:  		javac Application1.java
 *  Execution:    		java Application1
 *
 *	Student Name:		DIONYSIOS RIGATOS
 *  Student ID Number:	P3200262
 *
 *************************************************************/

public class Application1 {

    // ---------------------- Q1 ----------------------------
    public static boolean isPrime (int n) {
        if (n > 1) {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) { return false; }
            }
        }
        return true;
    }//isPrime

    // ---------------------- Q2 ----------------------------
    public static int factorial(int n) {
        if(n <= 1) {
            return 1;
        }else{
            return (n * factorial(n - 1));
        }
    }//factorial

    // ---------------------- Q3 ----------------------------
    public static int combinations(int n, int k) {
        return (factorial(n) / (factorial(k) * factorial(n-k)));
    }// combinations

    // ---------------------- Q4 ----------------------------
    public static int digitsOfInteger (int n) {
        return String.valueOf(n).length();
    }//digitsOfInteger

    // ---------------------- Q5 ----------------------------
    public static void quadraticEquation (double a, double b, double c) {
        double d = Math.pow(b, 2) - 4*a*c;
        if(d < 0){
            System.out.println("There is no real number solution.");
        }else if(d == 0){
            System.out.printf("The only solution is: %.2f\n", (-b)/(2*a));
        }else{
			double secondPart = Math.sqrt(d);
            System.out.printf("The first solution is: %.2f\n", (-b + secondPart)/(2*a));
            System.out.printf("The second solution is: %.2f", (-b - secondPart)/(2*a));
        }
    }//quadraticEquation

    // ---------------------- Q6 ----------------------------
    public static double findArea(double a, double b, double c) {
        double t = (a + b + c)/2;
        double subRoot = t*(t-a)*(t-b)*(t-c);
        if(subRoot >= 0){
            return Math.sqrt(subRoot);
        }else {
            return -1;
        }
    }//findArea

    // ---------------------- Q7 ----------------------------
    public static int reverseDigits (int n) {
        String strNum = String.valueOf(n);
        String finalNum = "";
        for(int i = strNum.length() - 1; i >= 0; i--){
            finalNum += strNum.charAt(i);
        }
        return Integer.parseInt(finalNum);

    }//reverseDigits

    // ---------------------- Q8 ----------------------------
    public static int minDigit(int n) {
        String strNum = String.valueOf(n);
        int min = strNum.charAt(0);
        for(int i = 1; i <= strNum.length() - 1; i++){
            int minNum = Integer.parseInt(String.valueOf(strNum.charAt(i)));
            if(minNum < min){
                min = minNum;
            }
        }
        return min;
    }//minDigit


    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        int n, k;
        double a, b, c;

        // ---------------- Q1 ----------------------------
        System.out.println("\n\nQ1 - Prime Number (n)");
        System.out.print("n (>1) = ");
        if(isPrime(n = input.nextInt())){
            System.out.println("\n" + n + " is a Prime Number");
        }else{
            System.out.println("\n" + n + " is not a Prime Number");
        }

        // ---------------- Q2 ----------------------------
        System.out.println("\n\nQ2 - Factorial (n)");
        System.out.print("n = ");
        System.out.println("\n" + "Factorial (" + (n = input.nextInt()) + ") = " + factorial(n));

        // ---------------- Q3 ----------------------------
        System.out.println("\n\nQ3 - Combinations (n,k)");
        System.out.print("n = ");
        n = input.nextInt();
        System.out.print("k = ");
        k = input.nextInt();
        System.out.println("Combinations(" + n + "," + k + ") = " + combinations(n, k));

        // ---------------- Q4 ----------------------------
        System.out.println("\n\nQ4 - Number of digits");
        System.out.println("Enter a positive integer: ");
        System.out.print("n = ");
        n = input.nextInt();
        System.out.println("Number of digits = " + digitsOfInteger(n));

        // ---------------- Q5 ----------------------------
        System.out.println("\n\nQ5 - Quadratic equation...");
        System.out.print("Enter a(<>0): ");
        a = input.nextDouble();
        System.out.print("Enter b: ");
        b = input.nextDouble();
        System.out.print("Enter c: ");
        c = input.nextDouble();
        quadraticEquation(a, b, c);

        // ---------------- Q6 ----------------------------
        System.out.println("\n\nQ6 - Area of a triangle");
        System.out.println("Enter the length of three sides.");
        System.out.print("Enter a: ");
        a = input.nextDouble();
        System.out.print("Enter b: ");
        b = input.nextDouble();
        System.out.print("Enter c: ");
        c = input.nextDouble();
        System.out.printf("The area of a triangle is: %.2f", findArea(a, b, c));

        // ---------------- Q7 ----------------------------
        System.out.println("\n\nQ7 - Reverse digits");
        System.out.print("Enter a positive integer:\nn = " );
        n = input.nextInt();
        System.out.println("The reversed number is = " + reverseDigits(n));

        // ---------------- Q8 ----------------------------
        System.out.println("\n\nQ8 - Min Digit");
        System.out.print("Enter a positive integer:\nn = " );
        n = input.nextInt();
        System.out.println("Min Digit = " + minDigit(n));

    } //main

} //Application1