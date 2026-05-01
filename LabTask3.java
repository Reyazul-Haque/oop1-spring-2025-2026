import java.util.Scanner;

public class LabTask3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a, b, c;
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();

        int max2;

        if ((a >= c && a <= b) || (a >= b && a <= c)) {
            max2 = a;
        } else if ((b >= a && b <= c) || (b <= a && b >= c)) {
            max2 = b;
        } else {
            max2 = c;
        }

        System.out.println("The 2nd largest number is: " + max2);

        sc.close();
    }
}