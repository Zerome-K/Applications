package cricket_score;

public class Player {
    String name, position;
    int totalRuns, currentRuns;
    public Player(String name, String position){
        this.name = name;
        this.position = position;
    }
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    @Override
    public String toString(){
        System.out.println("-------------------------------------------------");
        System.out.printf("|" + centerString(15, "Name" ) +"|" + centerString(15, "Position" ) +"|" + centerString(15, "Total Runs" ) +"|");
        System.out.println("\n    - - - - -       - - - - -       - - - - -");
        System.out.printf("|" + centerString(15, this.name ) +"|" + centerString(15, this.position ) +"|" + centerString(15, String.valueOf(this.currentRuns )) +"|");
        System.out.println("\n-------------------------------------------------");
        return "";
    }
}
