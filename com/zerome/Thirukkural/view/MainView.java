package thirukkural.view;

import thirukkural.controller.MainController;
import thirukkural.model.Kural;
import java.util.ArrayList;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);
    private final MainController mainController;

    MainView() {
        mainController = new MainController(this);
    }

    public void dashboard() {
        System.out.println("""
                \n---------THIRUKKURAL---------
                            
                    1 -> SEARCH BY NUMBER
                    2 -> SEARCH BY WORD
                    3 -> SEARCH BY CATEGORY
                    4 -> KURAL GAME
                    5 -> GET ALL THIRUKKURAL
                    6 -> EXIT
                """);

        System.out.print("CHOOSE OPTION : ");

        int option = getNumericInput();
        if (option == 6) return;
        else menuExecution(option);
        dashboard();
    }

    private void menuExecution(int option) {
        switch (option) {
            case 1 -> {
                while (true) {
                    System.out.print("\nENTER KURAL NUMBER : ");
                    int number = getNumericInput();
                    if (number < 1 || number > 1330) {
                        System.out.println("\n*--- ENTER BETWEEN 1-1330 ---*");
                        continue;
                    }
                    mainController.searchByNumber(number);
                    break;
                }
            }
            case 2 -> {
                System.out.print("ENTER WORD : ");
                String word = scanner.next();
                mainController.searchByWord(word);
            }
            case 3 -> {
                System.out.println("1. அறத்துப்பால் / Virtue");
                System.out.println("2. பொருட்பால் / Wealth");
                System.out.println("3. காமத்துப்பால் / Love");
                System.out.println("ENTER OPTION : ");
                int choice = getNumericInput();
                subCategories(choice);
            }
            case 4 -> mainController.kuralGame();
            case 5 -> mainController.getAllKural();
            default -> System.out.println("*--- ENTER VALID NUMBER ---*");
        }
    }

    private void subCategories(int option) {
        switch (option) {
            case 1 -> {
                System.out.println("""
                        -=-=-= இயல் =-=-=-
                        1. பாயிரவியல் / Prologue
                        2. இல்லறவியல் / Domestic Virtue
                        3. துறவறவியல் / Ascetic Virtue
                        4. ஊழியல் / Fate
                                            
                        CHOOSE OPTION :\s""");

                Scanner in = new Scanner(System.in);
                String choice = in.next();
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\nபால் / Section : அறத்துப்பால் / Virtue");
                switch (choice) {
                    case "1" -> mainController.boundaryPrinter(1, 40);
                    case "2" -> mainController.boundaryPrinter(41, 240);
                    case "3" -> mainController.boundaryPrinter(241, 370);
                    case "4" -> mainController.boundaryPrinter(371, 380);
                }
            }
            case 2 -> {
                System.out.println("""
                        *---------  இயல்  --------*
                        1. அரசியல் / Royalty
                        2. அமைச்சியல் / Ministers of State
                        3. அரணியல் / The Essentials of a State
                        4. கூழியல் / Way of Making Wealth
                        5. படையில் / The Excellence of an Army
                        6.நட்பியல் / Friendship
                        7.குடியியல் / Miscellaneous""");
                int choice = getNumericInput();
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\nபால் / Section : அறத்துப்பால் / Virtue");
                switch (choice) {
                    case 1 -> mainController.boundaryPrinter(381, 630);
                    case 2 -> mainController.boundaryPrinter(631, 730);
                    case 3 -> mainController.boundaryPrinter(731, 750);
                    case 4 -> mainController.boundaryPrinter(751, 760);
                    case 5 -> mainController.boundaryPrinter(761, 780);
                    case 6 -> mainController.boundaryPrinter(781, 950);
                    case 7 -> mainController.boundaryPrinter(951, 1080);
                    default -> System.out.println("*-- INVALID OPTION --*");
                }
            }
            case 3 -> {
                System.out.println("""
                        -=-=-= இயல் =-=-=-
                        1. களவியல்	The Pre-marital love
                        2. கற்பியல்	The Post-marital love
                                                
                        CHOOSE OPTION :\s""");
                Scanner in = new Scanner(System.in);
                String choice = in.next();
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\nபால் / Section : அறத்துப்பால் / Virtue");
                switch (choice) {
                    case "1" -> mainController.boundaryPrinter(1081, 1050);
                    case "2" -> mainController.boundaryPrinter(1051, 1330);
                }
            }
            default -> System.out.println("*--- OPTION IN-VALID ---*");
        }
    }

    public void printKural(Kural kural) {
        System.out.println("\n----------------------------");
        System.out.println("NUMBER        : " + kural.getNumber());
        System.out.println("குறள்         : " + kural.getLine1());
        System.out.println("                " + kural.getLine2());
        System.out.println("விளக்கம்      : " + kural.getDefinition3());
        System.out.println("ENGLISH       : " + kural.getTranslation());
        System.out.println("EXPLANATION   : " + kural.getExplanation());
        System.out.println("------------------------------");
    }

    public void message(String message) {
        System.out.println(message);
    }

    public void gamePrinter(ArrayList<String> array) {
        int count = 1;
        System.out.print("OPTION : ");
        for (String x : array) {
            System.out.print((count) + " - " + x + ",");
            count++;
        }
    }

    public int getNumericInput() {
        while (true) {
            try {
                String input = scanner.next();
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                System.out.println("*-- ENTER VALID INPUT --*");
            }
        }
    }
}

