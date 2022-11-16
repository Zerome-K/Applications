package flightmanagementsystem.controller;

import flightmanagementsystem.model.Flight;
import flightmanagementsystem.repository.DataBase;
import flightmanagementsystem.view.FlightView;

import java.util.List;

public class FlightController {
    private final FlightView flightView;
    public FlightController(FlightView flightView) {
        this.flightView = flightView;
    }
    public void addFlight(Flight flight) {
        List<Flight> flights = DataBase.getInstance().getFlights();
        flights.add(flight);
        DataBase.getInstance().setFlights(flights);
        String message = "SCHEDULE ADDED";
        flightView.displayMessage(message);
    }
    public void listRoutes(int number) {
        for (Flight flight : DataBase.getInstance().getFlights()) {
            if (flight.getFlightNumber() == number) {
                flightView.displayRoutes(flight.getRoutes());
                break;
            }
        }
    }
}
