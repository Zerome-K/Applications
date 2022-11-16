package inventory.view;

import inventory.controller.CustomerController;
import inventory.module.Customer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerView extends Tools {

    private final Scanner scanner = new Scanner(System.in);

    private final CustomerController customerController = new CustomerController();

    public void addCustomer() {

        Customer customer = new Customer();

        scanner.nextLine();

        while (true) {

            try {

                System.out.print("ENTER NAME : ");
                customer.setName(scanner.nextLine());

                System.out.print("ENTER COMPANY NAME : ");
                customer.setCompanyName(scanner.nextLine());

                System.out.print("ENTER EMAIL : ");

                while (true) {

                    String email = scanner.nextLine();

                    if (customer.setEmail(email)) break;

                    System.out.print("ENTER VALID EMAIL-ID : ");

                }

                System.out.print("ENTER MOBILE NUMBER : ");

                while (true) {

                    String mobileNumber = scanner.next();

                    if (customer.setMobile(mobileNumber)) break;

                    System.out.print("ENTER VALID MOBILE : ");

                }

                break;

            } catch (InputMismatchException x) {
                scanner.nextLine();
                System.out.println("*- ENTER VALID INPUT -*");
            }
        }
        customerController.pushCustomer(customer);
    }

    public void removeCustomer() {

        System.out.print("ENTER CUSTOMER MOBILE NUMBER : ");

        String number = scanner.next();

        if (customerController.kickOutCustomer(number)) System.out.println("*- REMOVED SUCCESSFULLY -*");

        else System.out.println("*- NUMBER NOT FOUND -*");
    }

    public void customerInfo(String mobile) {

        ArrayList<Customer> customers;

        customers = customerController.listCustomers();

        if (customers.isEmpty()) {

            System.out.println("*- CUSTOMER NOT FOUND -*");

            return;
        }

        for (Customer customer : customers) {

            if (customer.getMobile().equals(mobile)) {

                System.out.println(customer);

                return;

            }

        }

        System.out.println("*- CUSTOMER NOT FOUND -*");

    }

    public void viewCustomers() {

        ArrayList<Customer> customers;

        customers = customerController.listCustomers();

        System.out.println("+---------------------------------------------------------------------------------------------------+");

        System.out.println("|" + centerString(15, "ID") + "|" + centerString(15, "NAME") + "|" + centerString(25, "COMPANY NAME") + "|" + centerString(14, "MOBILE") +
                "|" + centerString(25, "E-MAIL") + "|");

        System.out.println("+---------------------------------------------------------------------------------------------------+");
        for (Customer customer : customers) {

            System.out.println("|" + centerString(15, String.valueOf(customer.getId())) + "|" + centerString(15, customer.getName()) + "|" +
                    centerString(25, customer.getCompanyName()) + "|" + centerString(14, customer.getMobile()) + "|" + centerString(25, customer.getEmail()) + "|");
        }

        System.out.println("+---------------------------------------------------------------------------------------------------+");

    }

    public void create() {

        System.out.println("""
                +---------------------------+
                |   1   | ADD CUSTOMER      |
                |   2   | REMOVE CUSTOMER   |
                |   3   | VIEW CUSTOMERS    |
                |   4   | CUSTOMER INFO     |
                |   5   | MAIN MENU         |
                +---------------------------+
                """);

        System.out.print("ENTER OPTION : ");
        byte option;
        try {
            option = scanner.nextByte();
            if (option == 1) addCustomer();
            else if (option == 2) removeCustomer();
            else if (option == 3) viewCustomers();
            else if (option == 4) {
                while (true) {
                    try {
                        System.out.print("ENTER CUSTOMER MOBILE : ");
                        String number = scanner.next();
                        customerInfo(number);
                        break;
                    } catch (InputMismatchException exception) {
                        scanner.next();
                        System.out.println("*-- ENTER VALID INPUT --*");
                    }
                }
            } else if (option == 5) return;
            create();
        } catch (InputMismatchException exception) {
            scanner.next();
            create();
        }
    }
}