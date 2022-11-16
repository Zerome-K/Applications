package employee_payroll;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee implements Serializable, Cloneable  {

     String name , designation;
     int id, presence, days;

     double netpay = 0.0, grosspay = 0.0, taxes = 0.0;


    Employee() {
        this.name = getName();
        this.id = Integer.parseInt(getId());
        this.designation = getDesignation();
    }

    private String getDesignation() {
        System.out.println("\n-=-=-=- DESIGNATION -=-=-=");
        System.out.println("""
                    1 -> DEVELOPER
                    2 -> DESIGNER
                    3 -> QUALITY ANALYSIS
                    4 -> TESTER
                -=-=-=-=-=-=-=-=-=-=-=-=-=""");

        System.out.print("\nSELECT OPTION :");

        Scanner in = new Scanner(System.in);

        while (true) {
            int des = in.nextInt();
            if (des < 1 || des > 4) System.out.print("\nENTER VALID NUMBER : ");
            else if (des == 1) return "DEVELOPER";
            else if (des == 2) return "DESIGNER";
            else if (des == 3) return "MARKETING ANALYSIS";
            else return "HUMAN RESOURCE";
        }
    }

    private String getName() {

        Scanner in = new Scanner(System.in);
        System.out.print("\nENTER NAME : ");
        while (true) {
            String name = in.nextLine();
            Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9_\\s]{3,255}$");
            Matcher m = p.matcher(name);
            if (m.find()) return name;
            else System.out.print("ENTER VALID NAME : ");
        }
    }

    private String getId() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("\nENTER NEW ID (xxxx) : ");
            String id = in.next();
            Pattern p = Pattern.compile("^\\d{4}$");
            Matcher m = p.matcher(id);
            if (m.find()) return id;
            System.out.print("ENTER VALID ID : ");
        }
    }

    @Override
    public Employee clone() {
        try {
            Employee clone = (Employee) super.clone();
            clone.name = this.name;
            clone.id = this.id;
            clone.days = this.days;
            clone.designation = this.designation;
            clone.netpay = this.netpay;
            clone.grosspay = this.grosspay;
            clone.presence = this.presence;
            clone.taxes = this.taxes;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


