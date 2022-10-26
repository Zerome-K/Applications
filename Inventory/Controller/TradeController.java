package inventory.controller;

import inventory.module.Product;
import java.util.HashMap;
import java.util.List;

public class TradeController {

    private final DataBase dataBase;

    public TradeController() {

        dataBase = DataBase.getInstance();
    }

    public void sellItem(long upc, int quantity, int customerId) {

        String sql = "SELECT * FROM products where UPC = " + upc + ";";

        String sql1 = "SELECT * FROM customers WHERE CUSTOMER_ID = " + customerId + ";";

        if (dataBase.eject(sql1).isEmpty()){

            System.out.println("*- CUSTOMER ID INVALID ! -*");

            return;
        }

        List<HashMap<String, Object>> items = dataBase.eject(sql);

        if (items.isEmpty()) {

            System.out.println("*- NO SUCH PRODUCT FOUND -*");

            return;
        }

        HashMap<String, Object> item = items.get(0);

        int itemQuantity = (int) item.get("QUANTITY");

        if (itemQuantity - quantity < 0) System.out.println("*- QUANTITY EXCEEDS -*");

        else {

            String updateProductSql = "UPDATE products SET QUANTITY = " + (itemQuantity - quantity) + " WHERE UPC = '" + item.get("UPC") + "';";

            if (dataBase.inject(updateProductSql)) {

                System.out.println("\n*- SALE CONFIRMED -*");

                updateSalesOrder(quantity, item, customerId);

            }
        }
    }

    private void updateSalesOrder(int quantity, HashMap<String, Object> item, int customerId) {

        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        double price = (double) item.get("SELLING_PRICE") * quantity;

        String sql = "INSERT INTO sale_orders(DATE,PRODUCT_ID,CUSTOMER_ID,status,AMOUNT,Quota) VALUES ('"
                + mySQLDate + "'," + item.get("UPC") + "," + customerId + "," + 1 + "," + price + "," + quantity + ");";

        if (dataBase.inject(sql)) System.out.println("*- UPDATED SALES ORDER -*");

    }

    public void buyItem(int upc, int quantity, String vendorName) {

        String sql = "SELECT UPC,QUANTITY,COST_PRICE FROM products WHERE UPC = '" + upc + "';";


        List<HashMap<String, Object>> items = dataBase.eject(sql);


        if (items.isEmpty()) {

            System.out.println("\n*- UPC INVALID / ITEM NOT FOUND -*");

            return;

        }

        HashMap<String, Object> item = items.get(0);

        Product product = new Product();
        product.setQuantity((int) item.get("QUANTITY"));
        product.setCostPrice((double) item.get("COST_PRICE"));
        product.setUpc((long) item.get("UPC"));

        quantity += product.getQuantity();

        String productSQL = "UPDATE products SET QUANTITY = " + quantity + " WHERE UPC = " + upc + ";";

        if (dataBase.inject(productSQL)) {
            updatePurchaseOrder(product, vendorName, quantity);
            System.out.println("*- PURCHASE UPDATED -*");
        } else System.out.println("*- UPDATE FAILED -*");

    }

    private void updatePurchaseOrder(Product product, String vendorName, int quantity) {

        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        double price = (product.getCostPrice() * quantity);

        String sql = "INSERT INTO purchase_orders(DATE,VENDOR_NAME,PRODUCT_UPC,QUANTITY,AMOUNT) " +
                "VALUES ('" + mySQLDate + "','" + vendorName + "'," +
                product.getUpc() + "," + quantity + "," + price + ");";

        if (dataBase.inject(sql)) System.out.println("*- UPDATED PURCHASE ORDER -*");

    }
    public List<HashMap<String, Object>> getRecord(boolean flag) {


        String salesSql = """
                    SELECT sale_orders.ORDER_ID, customers.CUSTOMER_NAME, products.ITEM_NAME, products.SELLING_PRICE, sale_orders.Quota, packageinfo.state, sale_orders.DATE, sale_orders.AMOUNT
                FROM (((sale_orders
                INNER JOIN customers ON sale_orders.CUSTOMER_ID = customers.CUSTOMER_ID)
                INNER JOIN products ON sale_orders.PRODUCT_ID = products.UPC)
                INNER JOIN packageinfo ON sale_orders.status = packageinfo.id);""";

        String purchaseSql = """
                SELECT po.DATE, po.PO_ORDER_ID, po.VENDOR_NAME, item.ITEM_NAME, item.COST_PRICE,po.QUANTITY,po.AMOUNT
                FROM purchase_orders AS po\s
                INNER JOIN products as item\s
                ON (po.PRODUCT_UPC = item.UPC);""";

        return dataBase.eject((flag) ? salesSql : purchaseSql);

    }

    public String saleOrdersCount(){

        String sql = "SELECT COUNT(*) as count FROM sale_orders;";

        return String.valueOf(dataBase.eject(sql).get(0).get("count"));


    }



}