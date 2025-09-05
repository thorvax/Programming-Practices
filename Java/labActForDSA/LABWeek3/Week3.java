import java.util.Scanner;

public class Week3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your grade:");
        byte grade = sc.nextByte();

        if (grade >= 75){
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
        sc.close();
    }
}
