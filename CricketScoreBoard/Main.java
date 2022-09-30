package cricket_score;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        welcome();

        System.out.print("\nENTER OVERS : ");

        int overs = in.nextInt();

        Ground play = new Ground(overs);

        play.start();
    }

    static void welcome(){
        System.out.print("\n+-----------------------------------------------+");
        System.out.println("\n|------------>     MATCH BEGINS    <------------| ");
        System.out.println("+-----------------------------------------------+");
    }
}
