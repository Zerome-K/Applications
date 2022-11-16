package inventory.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    private final Scanner scanner = new Scanner(System.in);
    private final TradeView tradeView;
    private final ProductView itemView;
    private final CustomerView customerView;
    private final DashBoard dashBoard;
    private final PackageView packageView;

    enum Menus {
        DASH, ITEMS, CUSTOMERS, PACKAGES, TRADE
    }

    Runner() {

        this.packageView = new PackageView();
        this.tradeView = new TradeView();
        this.itemView = new ProductView();
        this.customerView = new CustomerView();
        this.dashBoard = new DashBoard();

    }

    public void startUp() {

        System.out.print("""
                                
                +----------------------------------------------+
                |                     MENU                     |
                +----------------------------------------------+
                |   1 -> DASHBOARD          2 -> ITEMS         |
                |   3 -> CUSTOMERS          4 -> PACKAGES      |
                |   5 -> TRADE              6 -> LOGOUT        |
                +----------------------------------------------+
                                
                CHOOSE OPTION : """);

        byte option;

        while (true) {

            try {

                option = scanner.nextByte();

                break;

            } catch (InputMismatchException exception) {

                scanner.nextLine();

                System.out.print("ENTER VALID INPUT : ");
            }
        }

        if (option >= 1 && option <= 6) {

            if (option != 6) {

                Menus menu = Menus.values()[option - 1];

                executeMenu(menu);

                startUp();
            }

        }else {

            System.out.println("\n| ENTER VALID OPTION |");

            startUp();

        }


    }

    private void executeMenu(Menus menu) {

        switch (menu) {

            case DASH -> dashBoard.create();
            case ITEMS -> itemView.create();
            case CUSTOMERS -> customerView.create();
            case TRADE -> tradeView.create();
            case PACKAGES -> packageView.create();
            default -> System.out.println("ENTER VALID OPTION");
        }
    }
}
