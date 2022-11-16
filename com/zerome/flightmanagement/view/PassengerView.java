package flightmanagementsystem.view;

import flightmanagementsystem.controller.PassengerController;
import flightmanagementsystem.model.Passenger;
import flightmanagementsystem.repository.DataBase;

import java.util.List;
import java.util.Scanner;

public class PassengerView {

    private final PassengerController passengerController ;

    public PassengerView(){
        passengerController = new PassengerController(this);
    }
    private final Scanner scanner = new Scanner(System.in);

    public Passenger addPassenger(){
        Passenger passenger = new Passenger();
        System.out.print("ENTER NAME : ");
        String name = scanner.nextLine();
        passenger.setName(name);
        System.out.print("ENTER ID :");
        int id = scanner.nextInt();
        passenger.setPassengerId(id);
        scanner.nextLine();
        System.out.print("ENTER AGE :");
        int age = scanner.nextInt();
        passenger.setAge(age);
        scanner.nextLine();
        System.out.print("ENTER GENDER : ");
        String gender = scanner.next();
        passenger.setGender(gender);
        List<Passenger> passengerList = DataBase.getInstance().getPassengers();
        passengerList.add(passenger);
        DataBase.getInstance().setPassengers(passengerList);
        return passenger;
    }

    public void searchPassenger(){
        System.out.print("ENTER PASSENGER ID : ");
        int id = scanner.nextInt();
        passengerController.doSearchPassenger(id);
    }

    public void printPassenger(Passenger passenger) {
        System.out.println("NAME    : " + passenger.getName());
        System.out.println("ID      : " + passenger.getPassengerId());
        System.out.println("AGE     : "+ passenger.getAge());
        System.out.println("GENDER  : " + passenger.getGender());
    }
}
