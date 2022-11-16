package flightmanagementsystem.controller;

import flightmanagementsystem.model.Passenger;
import flightmanagementsystem.repository.DataBase;
import flightmanagementsystem.view.PassengerView;

import java.util.List;

public class PassengerController {
    PassengerView passengerView;

    public PassengerController(PassengerView passengerView) {
        this.passengerView = passengerView;
    }

    public void doSearchPassenger(int id) {
        List<Passenger> passengers = DataBase.getInstance().getPassengers();
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerId() == id) {
                passengerView.printPassenger(passenger);
                break;
            }
        }
    }
}
