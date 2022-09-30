package carParkingManagementSystem;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {
    HashMap<String, ParkerDetail> data = new HashMap<>();

    int totalSlots = 18, usedSlots = 0, freeSlots = 18;

    double revenue = 0.0;
    Floor floor;

    Admin() {
        floor = new Floor();
    }

    public String add() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        ParkerDetail p = new ParkerDetail();

        String slot = floor.getSlot();

        p.time = time.format(formatter);
        p.status = "IN";
        usedSlots++;
        freeSlots--;

        data.put(slot, p);

        return slot;

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

    public void leave(String id) {

        ParkerDetail p = data.get(id);

        String time = p.time;
        p.status = "OUT";


        LocalTime t = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String currenttime = t.format(formatter);

        p.uptime = currenttime;

        double total = (double) timeCalculator(time, currenttime) / 60;

        revenue += total;

        usedSlots--;
        freeSlots++;

        bill(p, total, currenttime, total);
        floor.set(id);
    }

    private void bill(ParkerDetail p, double total, String currenttime, double i) {
        String wtime = String.format("%.2fMin's", i);
        String fair = String.format("%.2f$", i * 10);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.print("| NAME           : " + centerString(p.name) + " |" + "\n| VEHICLE NUMBER : " + centerString(p.vehicleNumber) + " |" + "\n| ENTRY TIME     : " + centerString(p.time) + " |");
        System.out.printf("\n| EXIT TIME      : " + centerString(currenttime) + " |" + "\n| WITHSTAND TIME : " + centerString(wtime) + " |" + "\n| FAIR           : " + centerString(fair) + " |");
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    private static String centerString(String s) {
        return String.format("%-" + 16 + "s", String.format("%" + (s.length() + (16 - s.length()) / 2) + "s", s));
    }

    static int timeCalculator(String time, String currtime) {

        String[] btime = time.split(":");

        String[] ltime = currtime.split(":");

        int bookinghr = Integer.parseInt(btime[0]), bookingmin = Integer.parseInt(btime[1]), bookingsec = Integer.parseInt(btime[2]);

        int leavinghr = Integer.parseInt(ltime[0]), leavingmin = Integer.parseInt(ltime[1]), leavingsec = Integer.parseInt(ltime[2]);

        int sec = 0;

        while (leavingsec < bookingsec) {
            leavingmin -= 1;
            leavingsec += 60;
        }

        sec += leavingsec - bookingsec;
        while (leavingmin < bookingmin) {
            leavingmin += 60;
            leavinghr -= 1;
        }

        sec += (leavingmin - bookingmin) * 60;
        if (leavinghr <= bookinghr)
            return sec;
        else
            return (sec + (leavinghr - bookinghr) * 60 * 60);
    }

    public void getStart() {

        Scanner in = new Scanner(System.in);

        Admin admin = new Admin();

        System.out.println("=-=-=-=-=-=-=- ZOHO PARKING -=-=-=-=-=-=-=");
        while (true) {
            System.out.println(
                    "\n1. ASSIGN SLOT" +
                            "\n2. DE-ASSIGN SLOT" +
                            "\n3. VIEW AVAILABILITY" +
                            "\n4. PARKERS RECORDS" + "\n5. VIEW PARKING AREA" + "\n6. EXIT");

            System.out.print("\nENTER OPTION : ");
            int option = in.nextInt();
            if (option == 1) {
                String slot = admin.add();
                System.out.println("SLOT ASSIGNED SUCCESSFULLY : ID : " + slot);
            } else if (option == 2) {
                System.out.print("ENTER ID : ");
                String id = in.next();
                admin.leave(id);
            } else if (option == 3) {
                admin.records();
            } else if (option == 4) {
                admin.parkerdetails();
            } else if (option == 5) {
                admin.floor.display();
            } else if (option == 6) break;
        }
    }
}