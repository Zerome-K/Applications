package inventory.view;

import inventory.controller.TradeController;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TradeView extends Tools {

    private final Scanner scanner = new Scanner(System.in);

    private final TradeController tradeController = new TradeController();

    TradeView() {

    }

    public void sell() {

        long upc = 0;
        int quantity = 0, customerId = 0;

        try {

            System.out.print("ENTER UPC : ");

            upc = scanner.nextLong();

            System.out.println("ENTER QUANTITY : ");
            quantity = scanner.nextInt();


            System.out.print("ENTER CUSTOMER ID : ");
            customerId = scanner.nextInt();


        } catch (InputMismatchException exception) {

            scanner.nextLine();

            System.out.print("*- ENTER VALID INPUT! -*");
        }

        tradeController.sellItem(upc, quantity, customerId);

    }


    public void buy() {

        scanner.nextLine();

        try {

            System.out.print("ENTER VENDOR NAME : ");
            String vendorName = scanner.nextLine();

            System.out.print("ENTER PRODUCT UPC : ");
            int upc = scanner.nextInt();

            System.out.print("ENTER QUANTITY : ");

            int quantity = scanner.nextInt();

            tradeController.buyItem(upc, quantity, vendorName);

        } catch (InputMismatchException exception) {

            System.out.println("ENTER VALID INPUT!");

            buy();

        }

    }

    private void saleRecords() {

        List<HashMap<String, Object>> records = tradeController.getRecord(true);

        System.out.println("+-------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|" + centerString(14, "DATE") + "|" + centerString(12, "ORDER ID") + "|" +
                centerString(20, "CUSTOMER") + "|" + centerString(25, "ITEM") + "|" +
                centerString(12, "QUANTITY") + "|" + centerString(9, "PRICE") + "|" +
                centerString(12, "AMOUNT") + "|" + centerString(10, "STATUS") + "|");
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------+");


        for (HashMap<String, Object> map : records) {


            String date = String.valueOf(map.get("DATE"));
            String orderId = String.valueOf(map.get("ORDER_ID"));
            String customerName = (String) map.get("CUSTOMER_NAME");
            String quantity = String.valueOf(map.get("Quota"));
            String sellingPrice = String.valueOf(map.get("SELLING_PRICE"));
            String item = (String) map.get("ITEM_NAME");
            String amount = String.valueOf(map.get("AMOUNT"));
            String status = (String) map.get("state");


            System.out.println("|" + centerString(14, date) + "|" + centerString(12, orderId) + "|" +
                    centerString(20, customerName) + "|" + centerString(25, item) + "|" +
                    centerString(12, quantity) + "|" + centerString(9, sellingPrice) + "|" +
                    centerString(12, amount) + "|" + centerString(10, status) + "|");

        }
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------+");

    }

    private void purchaseRecords() {

        List<HashMap<String, Object>> records = tradeController.getRecord(false);

        int count = records.size();

        System.out.println("+-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-+");
        System.out.println("|" + centerString(14, "DATE") + "|" + centerString(14, "PURCHASE ID") + "|" +
                centerString(15, "VENDOR") + "|" + centerString(18, "ITEM NAME") + "|" + centerString(12, "COST PRICE") + "|" +
                centerString(12, "QUANTITY") + "|" + centerString(12, "AMOUNT") + "|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");

        for (HashMap<String, Object> map : records) {

            String date = String.valueOf(map.get("DATE"));
            String poOrderId = String.valueOf(map.get("PO_ORDER_ID"));
            String customerName = String.valueOf(map.get("VENDOR_NAME"));
            String name = String.valueOf(map.get("ITEM_NAME"));
            String costPrice = String.valueOf(map.get("COST_PRICE"));
            String quantity = String.valueOf(map.get("QUANTITY"));
            String amount = String.valueOf(map.get("AMOUNT"));

            System.out.println("|" + centerString(14, date) + "|" + centerString(14, poOrderId) + "|" +
                    centerString(15, customerName) + "|" + centerString(18, name) + "|" + centerString(12, costPrice) + "|" +
                    centerString(12, quantity) + "|" + centerString(12, amount) + "|");

        }
        System.out.println("+-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-+");

        System.out.println("| TOTAL ORDERS : " + count + " |\n--------------------");

    }

    public void create() {

        System.out.print("""
                                
                +---------------------------+
                |   1   | SELL ITEM         |
                |   2   | BUY ITEM          |
                |   3   | SALES ORDER       |
                |   4   | PURCHASE RECORD   |
                |   5   | MAIN MENU         |
                +---------------------------+
                                
                """);

        System.out.print("ENTER OPTION : ");

        byte option;

        try {

            option = scanner.nextByte();
            if (option == 1) sell();
            else if (option == 2) buy();
            else if (option == 3) saleRecords();
            else if (option == 4) purchaseRecords();
            else if (option == 5) return;
            create();
        } catch (InputMismatchException exception) {
            scanner.next();
            create();
        }
    }
}
