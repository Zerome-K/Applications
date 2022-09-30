package carParkingManagementSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Floor {

    char[][][] arr;

    Floor() {
        arr = new char[3][2][3];
    }

    public String getSlot(){

        Scanner in = new Scanner(System.in);

        int i = 1;

        while(i++ <= 3) {

            System.out.print("ENTER FLOOR 1/2/3 : ");

            try {
                int floor = in.nextInt();
                String slot = checkFloor(floor);
                if (!slot.equals("-1")) return slot;
            }catch (InputMismatchException e){
                i--;
                continue;
            }
            System.out.println("- SLOT NOT AVAILABLE CHOOSE ANOTHER FLOOR -");
        }

        return "=-=-=-=- ALL SLOTS ARE FULL -=-=-=-=";
    }

    private String checkFloor(int floor) {

        for(int i = 0; i < this.arr[floor-1].length; i++){

            for(int j = 0; j < this.arr[floor-1][i].length; j++){

                if(this.arr[floor-1][i][j] == '\u0000') {

                    this.arr[floor-1][i][j] = 'P';

                    return  floor+""+i+""+j;
                }
            }
        }
        return "-1";
    }

    public void set(String slot){

        arr[Character.getNumericValue(slot.charAt(0))-1][Character.getNumericValue(slot.charAt(1))][Character.getNumericValue(slot.charAt(2))] = '\u0000';

    }

    public void display() {

        System.out.print("\n-------------------");
        for (int i = 0; i < this.arr.length; i++) {

            System.out.printf("\n\t  Floor %d ", this.arr.length-i);

            System.out.println("\n-------------------");

            for (int j = 0; j < this.arr[i].length; j++) {

                System.out.print((j%2==0 ? "\n/" : "\n\\"));

                for (int k = 0; k < this.arr[i][j].length; k++)

                    if(j % 2 != 0) System.out.print("  "+ this.arr[i][j][k] + "  \\");

                    else System.out.print("  " + this.arr[i][j][k] + "  /");

                System.out.println("\n");
            }
            System.out.print("-------------------");
        }
    }
}