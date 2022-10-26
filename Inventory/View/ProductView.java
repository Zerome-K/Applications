package inventory.view;

import inventory.controller.ProductController;
import inventory.module.Product;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ProductView extends Tools {

    private final ProductController productController;

    private final Scanner scanner = new Scanner(System.in);

    private int categoriesCount;

    ProductView() {

        productController = new ProductController();

    }

    private void addItem() {

        Product product = new Product();

        scanner.nextLine();

        while (true) {

            try {

                System.out.print("ENTER ITEM NAME : ");
                String name = scanner.nextLine();
                product.setName(name);

                categories();

                System.out.print("\nSELECT TYPE FROM CATEGORIES : ");

                int type;

                while (true) {

                    try {

                        type = scanner.nextInt();

                        if (type > categoriesCount || type < 0)

                            System.out.print("ENTER VALID CATEGORY ID : ");

                        else break;

                    } catch (InputMismatchException exception) {

                        System.out.println("ENTER VALID INPUT");
                    }
                }
                product.setType(type);

                System.out.print("ENTER COST PRICE : ");
                double costPrice = scanner.nextDouble();
                product.setCostPrice(costPrice);

                System.out.print("ENTER SELLING PRICE : ");
                double sellingPrice = scanner.nextDouble();
                product.setSellingPrice(sellingPrice);

                System.out.print("ENTER REORDER LEVEL : ");
                int reLevel = scanner.nextInt();
                product.setReLevel(reLevel);
                break;

            } catch (InputMismatchException x) {
                scanner.nextLine();
                System.out.println("*- ENTER VALID INPUT -*");
            }

        }

        productController.pushItem(product);

    }

    private void removeItem() {

        System.out.print("ENTER UPC NUMBER : ");

        long upc;

        while (true) {

            try {

                upc = scanner.nextLong();

                break;

            } catch (InputMismatchException exception) {

                scanner.nextLine();

                System.out.print("ENTER VALID INPUT : ");
            }
        }
        if (productController.kickOutItem(upc)) System.out.println("\n*- ITEM REMOVED SUCCESSFULLY -*");

        else System.out.println("\n*- UPC NOT FOUND! -*");

    }

    private void upc() {

        scanner.nextLine();

        System.out.print("ENTER PRODUCT NAME : ");

        String name = scanner.nextLine();

        long upc = productController.getUpc(name);

        if (upc == 0) System.out.println("*- NO SUCH NAME FOUND -*");

        else System.out.println("\nUPC CODE -> " + upc);

    }

    private void viewItems() {

        String sql = "SELECT * FROM products;";

        List<Product> items = productController.showItems(sql);

        itemPrinter(items);

    }

    private void itemPrinter(List<Product> items) {

        System.out.println("+------------------------------------------------------------------------------------------------------+");
        System.out.println("|" + centerString(12, "UPC") + "|" + centerString(20, "NAME") + "|" +
                centerString(15, "CATEGORY ID") + "|" + centerString(10, "QUANTITY") + "|" +
                centerString(14, "COST PRICE") + "|" + centerString(15, "SELLING PRICE") + "|" +
                centerString(10, "RE LEVEL") + "|");
        System.out.println("+------------------------------------------------------------------------------------------------------+");


        for (Product product : items) {

            System.out.println("|" + centerString(12, String.valueOf(product.getUpc())) + "|" + centerString(20, product.getName())
                    + "|" + centerString(15, String.valueOf(product.getType())) + "|" + centerString(10, String.valueOf(product.getQuantity()))
                    + "|" + centerString(14, String.valueOf(product.getCostPrice())) + "|" + centerString(15, String.valueOf(product.getSellingPrice()))
                    + "|" + centerString(10, String.valueOf(product.getReLevel())) + "|");
        }

        System.out.println("+------------------------------------------------------------------------------------------------------+");

    }

    private void categories() {


        List<HashMap<String, Object>> categories = productController.getCategories();

        this.categoriesCount = categories.size();

        System.out.println("+-----+------------------------------+");
        System.out.println("|" + centerString(5, "ID") + "|" + centerString(30, "CATEGORY") + "|");
        System.out.println("+-----+------------------------------+");

        for (HashMap<String, Object> category : categories) {
            System.out.println("|" + centerString(5, String.valueOf(category.get("ID"))) + "|" + centerString(30, (String) category.get("CATEGORY")) + "|");
        }
        System.out.println("+-----+------------------------------+");
    }

    private void addCategory() {

        System.out.print("ENTER CATEGORY : ");

        String category = scanner.nextLine();

        if (productController.pushCategory(category)) System.out.println("*- ADDED SUCCESSFULLY -*");

        else System.out.println("*- CATEGORY EXIST -*");

    }

    private void lowStockItems() {

        String sql = "SELECT *  FROM products WHERE QUANTITY < RE_LEVEL;";

        List<Product> items = productController.showItems(sql);

        if(items.isEmpty()) System.out.println("\n*- ALL STOCKS IN LEVEL -*");

        itemPrinter(items);

    }


    public void create() {

        System.out.println("""
                                
                +---------------------------+
                |   1   | ADD ITEM          |
                |   2   | REMOVE ITEM       |
                |   3   | VIEW CATEGORIES   |
                |   4   | ADD CATEGORY      |
                |   5   | GET ITEM UPC      |
                |   6   | VIEW ITEMS        |
                |   7   | LOW STOCK ITEMS   |
                |   8   | MAIN MENU         |
                +---------------------------+
                """);

        System.out.print("ENTER OPTION : ");

        byte option;

        try {

            option = scanner.nextByte();

            if (option == 1) addItem();

            else if (option == 2) removeItem();

            else if (option == 3) categories();

            else if (option == 4) addCategory();

            else if (option == 5) upc();

            else if (option == 6) viewItems();

            else if (option == 7) lowStockItems();

            else if (option == 8) return;

            create();

        } catch (InputMismatchException exception) {

            scanner.next();

            create();
        }

    }
}
