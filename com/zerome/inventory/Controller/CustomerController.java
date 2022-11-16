package inventory.controller;

import inventory.module.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerController {

    private static final DataBase dataBase = DataBase.getInstance();
    public void pushCustomer(Customer customer) {
        String sql = "INSERT IGNORE INTO customers (CUSTOMER_NAME,MOBILE,E_MAIL,COMPANY) VALUES('"+customer.getName() +"','"+customer.getMobile()+"','"+customer.getEmail()
                +"','"+customer.getCompanyName()+"');";
        boolean isAdded = dataBase.inject(sql);
        System.out.println((isAdded) ? "\n*- CUSTOMER ADDED SUCCESSFULLY -* " : "\n*- CAN'T ABLE TO ADD -* ");

    }
    public ArrayList<Customer> listCustomers() {
        List<HashMap<String, Object>> result;
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers;";
        result = dataBase.eject(sql);
        for (HashMap<String, Object> x : result) {
            Customer customer = new Customer();
            customer.setId((Integer) x.get("CUSTOMER_ID"));
            customer.setName((String) x.get("CUSTOMER_NAME"));
            customer.setEmail((String) x.get("E_MAIL"));
            customer.setMobile((String) x.get("MOBILE"));
            customer.setCompanyName((String) x.get("COMPANY"));
            customers.add(customer);
        }
        return customers;
    }
    public boolean kickOutCustomer(String number) {
        String sql = "DELETE FROM customers WHERE MOBILE = '" + number + "';";
        return dataBase.inject(sql);
    }
}