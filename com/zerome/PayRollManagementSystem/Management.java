package employee_payroll;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Management {

    double employeesNetPay;

    Scanner in = new Scanner(System.in);

    Attendance attendance = new Attendance();

    ArrayList<Employee> employeeData = new ArrayList<Employee>();

    Management() {
        this.deserialize();
    }

    private void deserialize() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\EmployeeData.bin"));
            this.employeeData = (ArrayList<Employee>) ois.readObject();
        } catch (IOException e) {
            System.out.print("");
        }catch (ClassNotFoundException cnf){
            System.out.println("CLass Cast Found Exception");
        }
    }

    public void dashboard() {

        System.out.println("\n\n-=-=-=-=-=-= DASHBOARD =-=-=-=-=-=-");
        System.out.println(""" 
                    1 -> ADD EMPLOYEE
                    2 -> TAKE ATTENDANCE
                    3 -> VIEW EMPLOYEES
                    4 -> PAY ROLL
                    5 -> WAGES RECORDS
                    6 -> ATTENDANCE SHEET
                    7 -> QUIT
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-""");

        System.out.print("\nENTER OPTION : ");
        int option  = in.nextInt();

        if(option == 7) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                        "C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\EmployeeData.bin"));
                oos.writeObject(employeeData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        this.execution(option);

        dashboard();
    }

    private void execution(int option) {

        switch (option) {
            case 1 -> this.add();
            case 2 -> this.attendance();
            case 3 -> this.employessDetails();
            case 4 -> this.payRoll();
            case 5 -> this.records();
            case 6 -> this.attendance.sheet();
            default -> {
                System.out.println("=-=-=- ENTER VALID OPTION -=-=-=");
                this.dashboard();
            }
        }
    }

    public void add(){

        Employee employee1 = new Employee();
        this.employeeData.add(employee1);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                    "C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\EmployeeData.bin"));
            oos.writeObject(employeeData);
            System.out.println("|- EMPLOYEE ADDED SUCCESSFULLY -|");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void employessDetails() {

        System.out.println("\nACTIVE EMPLOYEES : " + this.employeeData.size());

        while (true) {
            System.out.println("""
                    \n-=-=-=-= SELECT OPTION -=-=-=-=
                        -> 1. VIEW EMPLOYEES
                        -> 2. SEARCH EMPLOYEE
                        -> 3. MAIN MENU
                    -=-=-=-=-=-=-=-=-=-=-=--=-=-=-=""");

            System.out.print("\nCHOOSE OPTION : ");
            int option = in.nextInt();
            if (option == 1) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("|" + centerString(18, "ID") + " | " + centerString(18, "NAME") + " | " + centerString(22, "DESIGNATION") + "|");
                System.out.println("------------------------------------------------------------------");
                for (Employee X : this.employeeData) {
                    System.out.println("|" + centerString(18, String.valueOf(X.id)) + " | " + centerString(18, X.name) + " | " + centerString(22, X.designation) + "|");
                }
                System.out.println("------------------------------------------------------------------");

            } else if (option == 2) {
                System.out.print("ENTER 4 DIGIT EMPLOYEE ID : ");
                String id = in.next();
                for (Employee X : this.employeeData) {
                    Pattern p = Pattern.compile("\\d{4}");
                    Matcher m = p.matcher(id);
                    if (m.find() && id.equals(String.valueOf(X.id))) {
                        System.out.println(X.netpay);
                        System.out.println("\n--------------------------------");
                        System.out.println("| ID   : " + centerString(22, String.valueOf(X.id)) + "|" + "\n| NAME : " + centerString(22, X.name.toUpperCase()) + "|" + "\n| ROLL : " + centerString(22, X.designation) + "|");
                        System.out.println("--------------------------------");
                    }
                }
            } else if (option == 3) break;
            else System.out.println("ENTER VALID OPTION : ");
        }
    }
    private void attendance() {
        attendance.register(this.employeeData);
    }
    public void payRoll() {

        ArrayList<Employee> salarydet = (ArrayList<Employee>) employeeData.clone();

        double[] wageses = wagesAllocation();

        for (Employee salary : salarydet) {

            double i;

            switch (salary.designation) {
                case "DEVELOPER" -> i = wageses[0];
                case "DESIGNER" -> i = wageses[1];
                case "QUALITY ANALYSIS" -> i = wageses[2];
                default -> i = wageses[3];
            }

            salary.grosspay = i * salary.presence;
            salary.netpay = (salary.grosspay * wageses[4]) / 100;
            salary.taxes = wageses[4];
            employeesNetPay += salary.netpay;
        }

        try {
            System.out.print("\nENTER RECORD NAME TO SAVE : ");
            String filename = in.next();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                    "C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\Payroll\\"+filename+".bin"));
            oos.writeObject(salarydet);
            System.out.print("\n-=-=-=-= ADDED SUCCESSFULLY =-=-=-=-");
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double[] wagesAllocation(){

        double[] wages = new  double[5];

        int i = 1;

        System.out.print("=-=-=-=-=- SALARY ALLOCATION -=-=-=-=-=");

        while(i ==1) {

            try {
                System.out.print("\n -> DEVELOPERS WAGES PER DAY       : ");
                double dev = in.nextDouble();
                wages[0] = dev;

                System.out.print("\n -> DESIGNERS WAGES PER DAY        : ");
                double des = in.nextDouble();
                wages[1] = des;

                System.out.print("\n -> NETWORK ARCHITECT WAGES PER DAY : ");
                double qa = in.nextDouble();
                wages[2] = qa;

                System.out.print("\n -> TESTER WAGES PER DAY            : ");
                double tt = in.nextDouble();
                wages[3] = tt;

                System.out.print("\nTAX DEDUCTION % : ");
                double tax = in.nextDouble();
                wages[4] = tax;

                i = 0;

            } catch (InputMismatchException e) {
                System.out.println("\n=-=-=- ENTER VALID AMOUNT -=-=-=");
                in.next();
            }
        }
        return wages;
    }

    private static String centerString(int w, String s) {
        return String.format("%-" + w + "s", String.format("%" + (s.length() + (w - s.length()) / 2) + "s", s));
    }

private void records(){

    File folder = new File("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\Payroll");
    String[] contents = folder.list();
    if(contents == null) {
        System.out.println("-=-=-=-= NO RECORDS FOUND -=-=-=-=");
        return;
    }
        System.out.print("\n-=-=-=-= AVAILABLE RECORDS -=-=-=-=");
        for (String X : contents) {
            System.out.print("\n       -> " + X.substring(0,X.lastIndexOf(".")));
    }
    List<String> con = new ArrayList<>(List.of(contents));
        System.out.print("\n\nENTER RECORD NAME :");
    String name = in.next();
        if(! con.contains(name+".bin")) {
        System.out.println("\n=-=-=- RECORD NOT FOUND -=-=-=");
        return;
    }
        try {
            ArrayList<Employee> ae ;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\Payroll\\"+name+".bin"));
            ae = (ArrayList<Employee>) ois.readObject();
            System.out.println(ae);
            System.out.println("-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=-=-=-=-=-=-=-=");
            System.out.println("| "+centerString(10, "NAME")+"|"+centerString(6,"ID")+"|"+centerString(18,"DESIGNATION")+
                "|"+centerString(6,"DAYS")+"|"+centerString(10,"PRESENCE")+"|"+centerString(8,"GROSS")+
                "|"+centerString(8,"TAX %")+"|"+centerString(10,"NET PAY")+"|");
            System.out.println("-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=-=-=-=-=-=-=-=");

            for (Employee X:ae) {
                System.out.println("| "+centerString(10, X.name)+"|"+centerString(6,String.valueOf(X.id))+"|"+centerString(18,X.designation)+
                    "|"+centerString(6,String.valueOf(X.days))+"|"+centerString(10,String.valueOf(X.presence))+"|"+centerString(8,String.valueOf(X.grosspay))+
                    "|"+centerString(8,String.valueOf(X.taxes))+"|"+centerString(8,String.valueOf(X.netpay))+"|");
        }
            System.out.println("-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=--=-=-=-=-=-=-=-=");

        } catch (IOException e) {
            System.out.print("File Not Found");
        }catch (ClassNotFoundException cnf){
            System.out.println("CLass Cast Found Exception");
        }
    }
}