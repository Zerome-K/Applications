package flightmanagementsystem.repository;

import flightmanagementsystem.model.Flight;
import flightmanagementsystem.model.Passenger;
import flightmanagementsystem.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase dataBase = null;
    private List<Flight> flights = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private DataBase() {}

    public static DataBase getInstance(){
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Flight> getFlights() {
        return this.flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
