package flightmanagementsystem.model;

import java.util.List;

public class Ticket {
    private String status;
    private int pnrNumber;
    private int fare;

    private Flight flight;
    private int flightNumber;
    private List<Passenger> passengerList;

    public int getFare() {
        return fare;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(int pnrNumber) {
        this.pnrNumber = pnrNumber;
    }
}
