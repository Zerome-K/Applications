package inventory.controller;

import inventory.module.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductController {

    private final DataBase dataBase = DataBase.getInstance();

    public ProductController() {}

    public String itemsTotalQuantity() {

        String sql = " SELECT SUM(quantity) as quantity FROM products;";

        HashMap<String, Object> totalQuantity = dataBase.eject(sql).get(0);

        return  String.valueOf(totalQuantity.get("quantity"));

    }

    public String getLowStockCounts() {

        String sql = "SELECT count(*) AS count FROM products WHERE QUANTITY < RE_LEVEL;";

        return String.valueOf(dataBase.eject(sql).get(0).get("count"));

    }

    public String totalItems() {

        String sql = "SELECT COUNT(*) as count FROM products;";

        return String.valueOf(dataBase.eject(sql).get(0).get("count"));
    }

    public void pushItem(Product product) {


        String sql = "INSERT IGNORE INTO products(ITEM_NAME, TYPE, COST_PRICE, SELLING_PRICE, RE_LEVEL, QUANTITY) " +
                     "VALUES('"+ product.getName() + "'," + product.getType() + "," +
                    product.getCostPrice() + "," + product.getSellingPrice() + "," + product.getReLevel() +","+0+");";

        boolean isAdded = dataBase.inject(sql);

        System.out.println((isAdded) ? "\n*- ITEM ADDED SUCCESSFULLY -* " : "\n*- ITEM EXISTS -* ");

    }

    public boolean kickOutItem(long upc) {

        String sql = "DELETE FROM products WHERE UPC = " + upc + ";";

        return dataBase.inject(sql);

    }

    public ArrayList<Product> showItems(String sql) {

        List<HashMap<String, Object>> items = dataBase.eject(sql);

        ArrayList<Product> products = new ArrayList<>();

        for (HashMap<String, Object> map : items) {

            Product product = new Product();

            product.setName((String) map.get("ITEM_NAME"));
            product.setCostPrice((double) map.get("COST_PRICE"));
            product.setSellingPrice((double) map.get("SELLING_PRICE"));
            product.setType((int) map.get("TYPE"));
            product.setUpc((long) map.get("UPC"));
            product.setQuantity((int) map.get("QUANTITY"));
            product.setReLevel((int) map.get("RE_LEVEL"));

            products.add(product);
        }

        return products;

    }

    public List<HashMap<String, Object>> getCategories() {

        String sql = "SELECT * FROM categories;";

        return dataBase.eject(sql);

    }

    public boolean pushCategory(String category) {

        String sql = "INSERT IGNORE INTO categories(CATEGORY) VALUES('"+category+"');";

        return dataBase.inject(sql);

    }

    public long getUpc(String name) {

        String sql = "SELECT UPC FROM products WHERE ITEM_NAME = '" + name + "';";

        List<HashMap<String, Object>> item = dataBase.eject(sql);

        if(item.isEmpty())return 0;

        return (long) item.get(0).get("UPC");

    }
}
