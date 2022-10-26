package inventory.module;

import inventory.controller.AdminController;

import java.util.regex.Pattern;

public class Admin {

    private final AdminController adminController;

    public Admin() {
        adminController = new AdminController();
    }

    public boolean signUpValidator(String name, String password) {

        boolean nameMatcher = Pattern.matches("^[a-zA-Z]+([._]?[a-zA-Z0-9]+)*$", name);

        boolean passMatcher = Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", password);

        if (nameMatcher && passMatcher) {

            return adminController.pushUpAdmin(name, password);

        }
        return false;
    }

    public boolean checkCredentials(String name, String password) {

            return adminController.loginVerification(name,password);
    }

}
