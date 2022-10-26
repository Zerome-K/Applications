package inventory.view;

import inventory.controller.PackageController;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PackageView extends Tools {

    private final Scanner scanner = new Scanner(System.in);

    private final PackageController packageController = new PackageController();

    private void updateStatus() {

        System.out.print("ENTER SALE ID : ");

        long saleId;

        while (true) {

            try {

                saleId = scanner.nextLong();

                break;

            } catch (InputMismatchException exception) {

                System.out.print("ENTER VALID INPUT : ");

            }
        }

        System.out.println("""
                    1. PACKED
                    2. SHIPPED
                    3. DELIVERED
                """);

        System.out.println("ENTER OPTION : ");

        byte option = 0;

        while (true) {

            try {

                option = scanner.nextByte();

                if (option >= 1 && option <= 3) break;

                System.out.print("ENTER VALID OPTION : ");

            } catch (InputMismatchException exception) {

                System.out.print("ENTER VALID INPUT : ");
            }

        }

        String result = packageController.updateStatus(saleId, option);

        System.out.println(result);
    }

    private void checkStatus() {

        System.out.println("ENTER SALE ID : ");

        long saleId = 0L;

        while (true) {

            try {

                saleId = scanner.nextLong();

                if (saleId >= 20000) break;

                System.out.print("ENTER VALID SALE ID : ");

            } catch (InputMismatchException exception) {

                scanner.nextLine();

                System.out.print("ENTER VALID INPUT :");
            }
        }

        System.out.println(packageController.getStatus(saleId));
    }

    private void getPackages(byte option) {

        List<HashMap<String, Object>> packages = packageController.packageDetails(option);

        if(packages.isEmpty()){

            System.out.println("\n*- NO PACKAGES IN STATE -*");

            return;

        }

        int count = packages.size();

        String status;

        if (option == 1) status = "PACKED";
        else if (option == 2) status = "SHIPPED";
        else status = "DELIVERED";

        System.out.println("+-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-+");
        System.out.println("|" + centerString(14, "DATE") + "|" + centerString(12, "ORDER ID") + "|" + centerString(15, "CUSTOMER") + "|" + centerString(16, "STATUS") + "|" + centerString(12, "AMOUNT") + "|");
        System.out.println("+-------------------------------------------------------------------------+");


        for (HashMap<String, Object> eachPackage : packages) {

            String date = String.valueOf(eachPackage.get("DATE"));
            String orderId = String.valueOf(eachPackage.get("ORDER_ID"));
            String customer = String.valueOf(eachPackage.get("CUSTOMER_ID"));
            String amount = String.valueOf(eachPackage.get("AMOUNT"));

            System.out.println("|" + centerString(14, date) + "|" + centerString(12, orderId) + "|" +
                    centerString(15, customer) + "|" + centerString(16, status) + "|" + centerString(12, amount) + "|");
        }

        System.out.println("+-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-+");

        System.out.println("| TOTAL PACKAGES : " + count + " |\n----------------------");

    }


    public void create() {

        System.out.println("""
                +------------------------------+
                |   1 -> PACKED                |
                |   2 -> SHIPPED               |
                |   3 -> DELIVERED             |
                |   4 -> UPDATE STATUS         |
                |   5 -> CHECK PACKAGE STATUS  |
                |   6 -> MAIN MENU             |
                +------------------------------+
                """);

        System.out.print("ENTER OPTION : ");

        byte option;

        try {

            option = scanner.nextByte();

            if (option == 1) getPackages(option);

            else if (option == 2) getPackages(option);

            else if (option == 3) getPackages(option);

            else if (option == 4) updateStatus();

            else if (option == 5) checkStatus();

            else if (option == 6) return;

            create();

        } catch (InputMismatchException exception) {

            scanner.nextLine();

            System.out.print("| ENTER VALID INPUT! |");

            create();
        }

    }
}
