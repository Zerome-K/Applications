package inventory.controller;

import java.util.HashMap;
import java.util.List;

public class AdminController {

    private final DataBase dataBase = DataBase.getInstance();

    public AdminController() {
    }

    public boolean pushUpAdmin(String name, String password) {
        String sql = "INSERT INTO admins " + "VALUES(" + 0 + ",'" + name + "',sha1('" + password + "'));";
        return dataBase.inject(sql);
    }

    public boolean loginVerification(String name, String password) {
        String sql = "SELECT UserName FROM admins WHERE UserPassword = sha1('" + password + "') and UserName = '" + name + "';";
        List<HashMap<String, Object>> data;
        data = dataBase.eject(sql);
        return !data.isEmpty();
    }
}