package carParkingManagementSystem;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {
    private HashMap<String, ParkerDetail> data = new HashMap<>();

    private int totalSlots = 18, usedSlots = 0, freeSlots = 18;

    private double revenue = 0.0;
    private Floor floor;

    Admin() {
        floor = new Floor();
    }

    public void getStart() {
        Scanner in = new Scanner(System.in);
        Admin admin = new Admin();
        System.out.println("=-=-=-=-=-=-=- ZOHO PARKING -=-=-=-=-=-=-=");
        while (true) {
            System.out.println(
                    """

                            1. ASSIGN SLOT
                            2. DE-ASSIGN SLOT
                            3. VIEW AVAILABILITY
                            4. PARKERS RECORDS
                            5. VIEW PARKING AREA
                            6. EXIT""");

            System.out.print("\nENTER OPTION : ");
            int option;
            while (true) {
                try{ option = in.nextInt();
                    if(option>= 1 && option <= 6)break;
                } catch (InputMismatchException e){System.out.println("=- ENTER VALID INPUT -=");}
            }
            if (option == 1) {
                String slot = admin.checkIn();
                System.out.println("SLOT ASSIGNED SUCCESSFULLY : ID : " + slot);
            } else if (option == 2) {
                System.out.print("ENTER ID : ");
                while(true) {
                    String id = in.next();
                    Pattern pattern = Pattern.compile("\\d{3}");
                    Matcher match = pattern.matcher(id);
                    if (match.find()) {admin.checkOut(id);break;}
                    System.out.println("ENTER VALID ID : ");
                }
            } else if (option == 3) {
                admin.records();
            } else if (option == 4) {
                admin.parkerdetails();
            } else if (option == 5) {
                admin.floor.platform();
            } else break;
        }
    }

    public String checkIn() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ParkerDetail parkerDetail = new ParkerDetail();
        String slot = floor.getSlot();
        parkerDetail.time = time.format(formatter);
        parkerDetail.status = "IN";
        usedSlots++;
        freeSlots--;
        data.put(slot, parkerDetail);
        return slot;
    }

    public void checkOut(String id) {

        ParkerDetail parkerDetail = data.get(id);
        String time = parkerDetail.time;
        parkerDetail.status = "OUT";
        LocalTime time1 = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = time1.format(formatter);
        parkerDetail.uptime = currentTime;
        double total = (double) timeCalculator(time, currentTime) / 60;
        revenue += total;
        usedSlots--;
        freeSlots++;
        bill(parkerDetail, currentTime, total);
        floor.set(id);
    }

    static int timeCalculator(String time, String currtime) {

        String[] bookingTime = time.split(":");
        String[] leavingTime = currtime.split(":");
        int bookingHour = Integer.parseInt(bookingTime[0]), bookingmin = Integer.parseInt(bookingTime[1]), bookingsec = Integer.parseInt(bookingTime[2]);
        int leavingHour = Integer.parseInt(leavingTime[0]), leavingMin = Integer.parseInt(leavingTime[1]), leavingSec = Integer.parseInt(leavingTime[2]);
        int seconds = 0;
        while (leavingSec < bookingsec) {
            leavingMin -= 1;
            leavingSec += 60;
        }
        seconds += leavingSec - bookingsec;
        while (leavingMin < bookingmin) {
            leavingMin += 60;
            leavingHour -= 1;
        }
        seconds += (leavingMin - bookingmin) * 60;
        if (leavingHour <= bookingHour)
            return seconds;
        else
            return (seconds + (leavingHour - bookingHour) * 60 * 60);
    }

    public void records() {
        System.out.println("\n-=-=-==--=-=-=-=-=-=-=-=-==--=");
        System.out.println("|  TOTAL SLOTS : " + totalSlots + "          |");
        System.out.println("|  USED SLOTS : " + usedSlots + "            |");
        System.out.println("|  FREE SLOTS : " + freeSlots + "           |");
        System.out.println("|  ONE DAY REVENUE : " + revenue + "     |");
        System.out.println("-=-=-==--=-=-=-=-=-=-=-=-==--=");
    }

    public void parkerdetails() {

        if (data.isEmpty()) System.out.println("\n-=-=- NO RECORDS FOUND PRESS 1 TO ADD -=-=-");
        for (Map.Entry<String, ParkerDetail> x : data.entrySet()) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("|       SLOT NO: " + x.getKey() + "       |");
            System.out.print(x.getValue());
        }
    }

    private void bill(ParkerDetail p, String currenttime, double price) {
        String duration = String.format("%.2fMin's", price);
        String fair = String.format("%.2f$", price * 10);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.print("| NAME           : " + centerString(p.name) + " |" + "\n| VEHICLE NUMBER : " + centerString(p.vehicleNumber) + " |" + "\n| ENTRY TIME     : " + centerString(p.time) + " |");
        System.out.printf("\n| EXIT TIME      : " + centerString(currenttime) + " |" + "\n| WITHSTAND TIME : " + centerString(duration) + " |" + "\n| FAIR           : " + centerString(fair) + " |");
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    private static String centerString(String s) {
        return String.format("%-" + 16 + "s", String.format("%" + (s.length() + (16 - s.length()) / 2) + "s", s));
    }
}