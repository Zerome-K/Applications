package inventory.controller;


public class RevenueController {

    private DataBase dataBase ;

    public RevenueController(){
        dataBase = DataBase.getInstance();
    }

    public String oneDayRevenue(){

        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        String Sql = """
                SELECT SUM(AMOUNT) as TotalAmount
                FROM sale_orders
                WHERE DATE = '%s';""".formatted(mySQLDate);

        return String.valueOf(dataBase.eject(Sql).get(0).get("TotalAmount"));

    }

    public static void main(String[] args) {
        RevenueController rc = new RevenueController();
        System.out.println(rc.oneDayRevenue());
    }

}
