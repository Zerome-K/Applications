package carParkingManagementSystem;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkerDetail{

    String time, status="-", uptime, vehicleNumber, name;
    Scanner in  = new Scanner(System.in);

    static ArrayList<ParkerDetail> data = new ArrayList<>();

    ParkerDetail(){
        this.name = this.getName();
        this.vehicleNumber = this.getVehicleNumber();
        this.time = "-";
        this.uptime = "-";
        ParkerDetail.data.add(this);
    }

    private String getName() {
        System.out.print("\nENTER NAME : ");
        while(true) {
            String name = in.nextLine();
            Pattern p = Pattern.compile("^[A-Za-z]\\w{2,29}$");
            Matcher m = p.matcher(name);
            if(m.find())return name;
            else System.out.println("ENTER VALID NAME : ");
        }
    }

    private String getVehicleNumber() {
        System.out.print("ENTER VEHICLE NUMBER : ");
        jh:
        while(true) {
            boolean flag = false;
            String number = in.nextLine();
            Pattern p = Pattern.compile("^[A-Za-z]{2}\\s?\\d{2}\\s?[A-Za-z]{2}\\s?\\d{4}$");
            Matcher m = p.matcher(number);
            for (ParkerDetail x : ParkerDetail.data) {
                if(Objects.equals(x.vehicleNumber, number)) {
                    System.out.println("\n=-=- NUMBER ALREADY EXISTS! -=-=");
                    System.out.print("ENTER VALID NUMBER : ");
                    continue jh;
                }
            }
            if(m.find()) return number;
            System.out.println("\nENTER VALID NUMBER : ");
        }
    }

    private static String centerString(String s) {
        return String.format("%-" + 14 + "s", String.format("%" + (s.length() + (14 - s.length()) / 2) + "s", s));
    }

    public String toString(){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("| NAME     : " + centerString(this.name) + "|");
        System.out.println("| NUMBER   : " + centerString(this.vehicleNumber) + "|");
        System.out.println("| IN TIME  : " + centerString(this.time) + "|");
        System.out.println("| OUT TIME : " + centerString(this.uptime) + "|");
        System.out.println("| STATUS   : " + centerString(this.status)+ "|");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        return "";
    }
}
