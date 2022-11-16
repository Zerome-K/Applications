package inventory.view;

import inventory.controller.PackageController;
import inventory.controller.ProductController;
import inventory.controller.RevenueController;
import inventory.controller.TradeController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DashBoard extends Tools {

    private final Scanner scanner = new Scanner(System.in);

    private final PackageController packageController = new PackageController();

    private final ProductController productController = new ProductController();

    private int[] packageStatus;
    private String itemsTotalQuantity, lowStockItems, allItems, orders;
    private String revenue;
    private void dashBoard() {

        System.out.println("+--------------------------------------+--------------------------------------+");
        System.out.println("|" + centerString(38, "SALES ACTIVITY") + "|" + centerString(38, "INVENTORY SUMMARY") + "|");
        System.out.println("+--------------------------------------+--------------------------------------+");
        System.out.println("|" + centerString(12, "PACKED") + "|" + centerString(12, "SHIPPED") + "|" +
                centerString(12, "DELIVERED") + "|" + centerString(24, "QUANTITY IN HAND") + ":" +
                centerString(13, String.valueOf(itemsTotalQuantity)) + "|");
        System.out.println("+  ---------   ----------   ---------  +--------------------------------------+");
        System.out.println("|" + centerString(12, String.valueOf(packageStatus[0])) + "|" + centerString(12, String.valueOf(packageStatus[1]))
                + "|" + centerString(12, String.valueOf(packageStatus[2])) + "|" + centerString(24, "ORDER PLACED") + ":" +
                centerString(13, orders) + "|");
        System.out.println("+--------------------------------------+--------------------------------------+");

        System.out.println("|" + centerString(23, "        ALL ITEMS        :") + centerString(12, String.valueOf(allItems))
                + "|" + centerString(24, "    LOW STOCK ITEMS     :") + centerString(13, String.valueOf(lowStockItems)) + "|");

        System.out.println("+--------------------------------------+--------------------------------------+");

        System.out.println("|" + centerString(38, "TOTAL COST") + ":" +centerString(38,revenue)+"|");
        System.out.println("+--------------------------------------+--------------------------------------+");

    }
    private void refreshDashBoard() {

        packageStatus = packageController.salesActivity();

        itemsTotalQuantity = productController.itemsTotalQuantity();

        allItems = productController.totalItems();

        lowStockItems = productController.getLowStockCounts();

        orders = new TradeController().saleOrdersCount();

        revenue = new RevenueController().oneDayRevenue();

    }

    public void create() {

        this.refreshDashBoard();

        this.dashBoard();

        System.out.println(" 1 -> REFRESH / 2 -> MAIN MENU : ");

        byte refresh;

        while (true) {

            try {

                refresh = scanner.nextByte();

                break;

            } catch (InputMismatchException exception) {

                scanner.nextLine();

                System.out.print("ENTER VALID INPUT : ");

            }
        }

        if (refresh == 1) create();

    }

}
