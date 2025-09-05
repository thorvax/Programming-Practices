package LAB_Integrative_programming;
import java.util.ArrayList;
import  java.util.Scanner;

public class IF_StatementLab {
    static Scanner sc = new Scanner(System.in);



    public static void main(String[] args) {
        ArrayList<Short> answers = new ArrayList<Short>();
        short answer = 0;
        short correctAnswer = 0;

        System.out.println("Quiz eme eme");


        System.out.println("1. 1+1=?"); //2
        answer = sc.nextShort();
        answers.add(answer);
        if(answers.get(0) == 2){
            System.out.println("1. Correct");
            correctAnswer++;
        }else {
            System.out.println("1. Wrong");
            return;
        }



        System.out.println("2. 2+4=?"); //6
        answer = sc.nextShort();
        answers.add(answer);

        if(answers.get(1) == 6){
            System.out.println("2. Correct");
            correctAnswer++;

        }else {
            System.out.println("2. Wrong");
            return;

        }

        System.out.println("3. 5+9=?"); //14
        answer = sc.nextShort();
        answers.add(answer);
        if(answers.get(2) == 14){
            System.out.println("3. Correct");
            correctAnswer++;

        }else {
            System.out.println("3. Wrong");
            return;
        }


        System.out.println("4. 10+15=?"); //25
        answer = sc.nextShort();
        answers.add(answer);
        if(answers.get(3) == 25){
            System.out.println("4. Correct");
            correctAnswer++;

        }else {
            System.out.println("4. Wrong");
            return;

        }


        System.out.println("5. 7+3=?"); //10
        answer = sc.nextShort();
        answers.add(answer);
        if(answers.get(4) == 10){
            System.out.println("5. Correct");
            correctAnswer++;

        }else {
            System.out.println("5. Wrong");
            return;

        }



        System.out.println("Press any key to check your answers");









        System.out.println("You got a total score of: " + correctAnswer);

    }

}
