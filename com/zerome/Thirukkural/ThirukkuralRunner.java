package thirukkural;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ThirukkuralRunner {

    private JSONArray list;

    private final JSONParser parser = new JSONParser();



    ThirukkuralRunner() {jsonExecution();}

    private void jsonExecution() {

        try {

            FileReader file = new FileReader("C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src\\thirukkural\\thirukkural.json");

            Object object = parser.parse(file);

            JSONObject obj = (JSONObject) object;

            list = (JSONArray) obj.get("kural");

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void boundaryPrinter(int start, int end) {
        for (int i = start; i <= end; i++)
            searchByNumber(i);
    }

    public void searchByNumber(int num) {

        JSONObject obj = (JSONObject) list.get(num - 1);
        System.out.println("\n---------------");
        System.out.println("NUMBER        : " + num);
        System.out.println("குறள்         : " + obj.get("Line1"));
        System.out.println("                " + obj.get("Line2"));
        System.out.println("விளக்கம்      : " + obj.get("mk"));
        System.out.println("ENGLISH       : " + obj.get("Translation"));
        System.out.println("EXPLANATION   : " + obj.get("explanation"));
        System.out.println("---------------");
    }

    public void searchByWord(String word) {
        for (Object o : list) {
            JSONObject obj = (JSONObject) o;
            String line1 = (String) obj.get("Line1");
            String Line2 = (String) obj.get("Line2");
            if (line1.contains(word) || Line2.contains(word)) {
                System.out.println("----------");
                System.out.println("குறள்    : " + obj.get("Number"));
                System.out.println("           " + line1);
                System.out.println("           " + Line2);
                System.out.println("விளக்கம் : " + obj.get("mk"));
                System.out.println("----------");
            }
        }
    }

    public void everyKural() {
        for (Object o : list) {
            JSONObject obj = (JSONObject) o;
            String line1 = (String) obj.get("Line1");
            String line2 = (String) obj.get("Line2");
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(centerString(45, "குறள் : " + obj.get("Number")));
            System.out.println(centerString(50, line1));
            System.out.println(centerString(45, line2));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    private static String centerString(int w, String s) {
        return String.format("%-" + w + "s", String.format("%" + (s.length() + (w - s.length()) / 2) + "s", s));
    }

    public void kuralGame() {

        Scanner in = new Scanner(System.in);

        Random random = new Random();

        while (true) {

            int position = random.nextInt(1331);

            JSONObject obj = (JSONObject) list.get(position);

            String kural = (String) obj.get("Line1") + " " + obj.get("Line2");

            ArrayList<String> array = new ArrayList<>(List.of(kural.split("\\s")));

            String[] vacantarray = {"----1----", "----2----", "----3----", "----4----", "----5----", "----6----", "----7----"};

            for (int i = 0; i < 7; i++) {
                System.out.println("\n" + Arrays.toString(vacantarray));
                arrayShuffler(array);
                int j = 1;
                System.out.print("OPTION : ");
                for (String x : array) {
                    System.out.print((j) + " - " + x + ",");
                    j++;
                }
                System.out.println("\nSELECT PHRASE : ");
                try {
                    byte number = in.nextByte();
                    if (!(number < 1 || number > 7 - i)) {
                        vacantarray[i] = array.get(number - 1);
                        array.remove(number - 1);
                    } else i -= 1;
                } catch (InputMismatchException e) {
                    System.out.println("=-=-=-=- ENTER VALID INPUT -=-=-=-=");
                    in.next();
                    i -= 1;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String x : vacantarray) sb.append(x).append(" ");
            sb.deleteCharAt(sb.length() - 1);

            for (int i = 0; i < 7; i++) {
                if (!Objects.equals(kural, sb.toString())) {
                    System.out.println(" -=-=-= FAILED -=-=-=");
                    System.out.println("CORRECT ANSWER : '" + kural + "'");
                    break;
                } else System.out.println("-=-=-= YOU WON -=-=-=");
            }
            System.out.println("""
                    1. RETRY
                    2. EXIT
                    """);

            String input = in.next();
            if (input.equals("2")) break;

        }

    }

    private void arrayShuffler(List<String> array) {
        for (int i = array.size() - 1; i >= 0; i--) {
            int position = new Random().nextInt(array.size());
            if (position != i) {
                String temp = array.get(i);
                array.set(i, array.get(position));
                array.set(position, temp);
            }
        }
    }

}


