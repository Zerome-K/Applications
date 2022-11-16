package cricket_score;

import java.util.Scanner;
public class Team {
    Scanner in = new Scanner(System.in);
    Player[] self = new Player[11];
    Player[] csk = {new Player("FAF", "BATSMAN"), new Player("JADEJA", "ALL ROUNDER"),
                    new Player("RAINA", "ALL ROUNDER"),new Player("D BRAVO", "BOWLER"),
                    new Player("MOEEN ALI", "BATSMAN"),new Player("RUTURAJ", "BATS MAN"),
                    new Player("RAYUDU", "BATSMAN"), new Player("DHONI", "CAP/WK"),
                    new Player("DUBE", "BATSMAN"),new Player("UTHAPPA", "VC"),new Player("VIJAY", "ALL ROUNDER")};

    Player[] mi = {new Player("SKY", "BATSMAN"), new Player("HARDIK", "BATSMAN"),
            new Player("I KISAN", "WK"),new Player("POLLARD", "ALL ROUNDER"),
            new Player("KRUNAL", "ALL ROUNDER"),new Player("CHAHAR", "BOWLER"),
            new Player("ROHIT", "CAP"), new Player("TIWARY", "BATSMAN"),
            new Player("BUMRAH", "BOWLER"),new Player("ARCHER", "BOWLER"),new Player("MITCHELL", "BOWLER")};

    Player[] rcb= {new Player("ABD", "BATSMAN"), new Player("PADIKKAL", "BATSMAN"),
            new Player("KOHLI", "BATSMAN"),new Player("GAYLE", "BATSMAN"),
            new Player("STOINIS", "ALL ROUNDER"),new Player("DUBE", "ALL ROUNDER"),
            new Player("PATEL", "WK"), new Player("SUNDAR", "ALL ROUNDER"),
            new Player("CHAHAL", "BOWLER"),new Player("UMESH", "BOWLER"),new Player("SIRAJ", "BOWLER")};
    String name;
     Team() {
         this.getplayer();
    }

    Team(String name){

         this.name = name;

         this.self = csk;
    }

    void getplayer(){

        System.out.print("ENTER TEAM NAME :");

        name = in.next();

        for (int i = 0; i < 11; i++) {

            System.out.print("\nENTER PLAYER NAME :");

            String name = in.next();

            System.out.print("\nENTER POSITION :");

            String pos = in.next();

            this.self[i] = new Player(name, pos);
        }
    }

    public void printPlayer(){

        for (Player x : this.self){

            System.out.println(x);

            System.out.println();
        }
    }

    @Override
    public String toString(){

         System.out.printf("Team : " + this.name);

         System.out.print(" Players : ");

         this.printPlayer();

         return "";
     }
    }
