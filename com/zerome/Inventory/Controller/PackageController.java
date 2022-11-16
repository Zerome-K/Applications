package inventory.controller;

import java.util.HashMap;
import java.util.List;

public class PackageController {

    private final DataBase dataBase;

    public PackageController(){
        dataBase = DataBase.getInstance();
    }

    public String updateStatus(long saleId, byte option) {

        option++;

        String sql = "UPDATE sale_orders SET status = " + option + " WHERE ORDER_ID = " + saleId + ";";

        if (dataBase.inject(sql)) {

            return "*- UPDATED SUCCESSFULLY -*";
        }
        return "*- REFRESHED -*";
    }

    public String getStatus(long saleId) {

        String sql = "SELECT status FROM sale_orders WHERE ORDER_ID = " + saleId + ";";

        List<HashMap<String, Object>> list = dataBase.eject(sql);

        if (list.isEmpty()) return "*- SALE ID NOT FOUND -*";

        int status = (int) list.get(0).get("status");

        if (status == 1) return "*- CONFIRMED -*";

        else if (status == 2) return "*- PACKED -*";

        else if (status == 3) return "*- SHIPPED -*";

        else return "*- DELIVERED -*";

    }

    public int[] salesActivity() {

        String sql = "SELECT STATUS from sale_orders";

        List<HashMap<String, Object>> stat = dataBase.eject(sql);

        int[] statics = {0, 0, 0};

        for (HashMap<String, Object> x : stat) {

            if ((Integer) x.get("status") == 2) statics[0]++;

            else if ((Integer) x.get("status") == 3) statics[1]++;

            else if ((Integer) x.get("status") == 4) statics[2]++;

        }

        return statics;

    }


    public List<HashMap<String, Object>> packageDetails(byte option) {

        String sql = "SELECT * FROM sale_orders WHERE status = " + (option+1) +";";

        return(dataBase.eject(sql));

    }
}
