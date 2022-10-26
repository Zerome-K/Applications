package inventory.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminView {

    private final Scanner scanner = new Scanner(System.in);

    private final inventory.module.Admin adminValidation;

    AdminView() {
        adminValidation = new inventory.module.Admin();
    }

    public void init() {

        System.out.println("|_______WELCOME TO INVENTORY_______|");

        execute();

    }

    private void signUp() {

        System.out.print("ENTER USERNAME : ");

        String name = scanner.nextLine();

        System.out.print("ENTER PASSWORD : ");

        String pass = scanner.nextLine();

        if (adminValidation.signUpValidator(name, pass)) {

            System.out.println("\n*- SIGN-UP SUCCESSFULLY -*");

        } else {

            System.out.println("! (Pass must contain a number & upperCase & lowercase)");

            signUp();
        }

    }

    private void logIn() {

        System.out.print("ENTER USERNAME : ");

        String name = scanner.nextLine();

        System.out.print("ENTER PASSWORD : ");

        String pass = scanner.nextLine();

        if (adminValidation.checkCredentials(name, pass)) {

            System.out.println("\n*- LOG-IN SUCCESSFULLY -*");

            menu();

        } else {

            System.out.println("\n*- INVALID USERNAME/PASSWORD -*");

        }
    }

    private void menu() {

        Runner runner = new Runner();

        runner.startUp();
    }

    private void execute() {

        while (true) {

            System.out.println("""
                               
                               +------------+
                               | 1. LOG-IN  |
                               | 2. SIGN-UP |
                               | 3. EXIT    |
                               +------------+
                    """);

            System.out.print("ENTER OPTION : ");

            try {

                int choice = scanner.nextInt();

                scanner.nextLine();

                if (choice == 1) logIn();

                else if (choice == 2) signUp();

                else if (choice == 3) break;

                else System.out.print("ENTER VALID OPTION !");

            } catch (InputMismatchException exception) {

                scanner.next();

                System.out.print("ENTER VALID INPUT !");

            }
        }
    }

}