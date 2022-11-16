package employee_payroll;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attendance {

        Scanner input = new Scanner(System.in);
        Attendance() {
        }

        public void register(ArrayList<Employee> employee) {
                
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = myDateObj.format(myFormatObj);
                File f = new File("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\Attendance\\" + formattedDate + ".txt");
                try {
                        boolean flag = f.createNewFile();
                        if(flag) {
                                System.out.println("-=-=-=-=- ATTENDANCE -=-=-=-=");
                                System.out.println("\nPRESS [P-PRESENT or A-ABSENT]");
                                FileWriter writer = new FileWriter(f);
                                for (Employee X : employee) {
                                        System.out.print("\nNAME - " + X.name + " | ID - " + X.id + " : ");
                                        while(true) {
                                                String status = input.next().toUpperCase();
                                                if (status.equals("P") || status.equals("A")) {
                                                        if(status.equals("P"))X.presence++;
                                                        X.days++;
                                                        writer.write(X.id + "-" + X.name.toUpperCase() + "-" + status + "\n");
                                                        writer.flush();
                                                        break;
                                                } else System.out.print("\nENTER P/A : ");
                                        }
                                }
                                writer.close();
                        } else System.out.print("\n=-=-=-= ATTENDANCE TAKEN =-=-=-=");
                } catch (FileNotFoundException e) {
                        System.out.println("register");
                } catch (IOException e){
                        System.out.println("register 1");
                }
        }
        
        public void sheet() {

                File folder = new File("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\Attendance");
                String[] contents = folder.list();
                System.out.print("=-=-=- AVAILABLE DATES -=-=-");
                assert contents != null;
                for (String X : contents) {
                        System.out.print("\n       -> " + X.substring(0,10));
                }
                System.out.println("\n-----------------------------------");
                System.out.print("ENTER DATE-> xx-xx-xxxx :");
                String dates = input.next();
                Pattern p = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
                Matcher matcher = p.matcher(dates);
                if(matcher.find()){
                        try {
                                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\employee_payroll\\Attendance\\"+dates+".txt"));
                                String line;
                                System.out.println("-----------------------------------");
                                System.out.println("|" + centerString(8,"ID") +" | " +centerString(12,"NAME") + " | "+centerString(6,"STATUS")+" |");
                                System.out.println("-----------------------------------");
                                while((line = br.readLine()) != null){
                                        String[] s = line.split("-");
                                        System.out.println("|" + centerString(8,s[0]) + " | "+centerString(12,s[1]) +" | "+centerString(6, s[2]) + " |");
                                }
                                System.out.println("-----------------------------------");

                        } catch (IOException e) {
                                System.out.println("\nENTER CORRECT DATE / TAKE AN ATTENDANCE");;
                        }
                }
        }
        private static String centerString(int w,String s) {
                return String.format("%-" + w + "s", String.format("%" + (s.length() + (w - s.length()) / 2) + "s", s));
        }

}
