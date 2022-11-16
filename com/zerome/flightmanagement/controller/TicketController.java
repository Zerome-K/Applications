package flightmanagementsystem.controller;

import flightmanagementsystem.model.City;
import flightmanagementsystem.model.Flight;
import flightmanagementsystem.model.Passenger;
import flightmanagementsystem.model.Ticket;
import flightmanagementsystem.repository.DataBase;
import flightmanagementsystem.view.TicketView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TicketController {
    private final TicketView ticketView;

    public TicketController(TicketView ticketView) {
        this.ticketView = ticketView;
    }

    public void checkFlights(String fromStation, String toStation) {
        List<Flight> flights = DataBase.getInstance().getFlights();
        HashMap<Flight, Integer> availableFlight = new HashMap<>();
        for (Flight flight : flights) {
            List<City> via = flight.getRoutes();
            boolean flag = false;
            int citiCount = 0;
            for (City city : via) {
                if (Objects.equals(city.getCityName(), fromStation)) {
                    flag = true;
                    citiCount++;
                } else if (flag) {
                    citiCount++;
                    if (Objects.equals(city.getCityName(), toStation)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) availableFlight.put(flight, citiCount);
        }
        ticketView.availableFlights(availableFlight);
    }

    public void bookFlight(Flight flight, List<Passenger> passengers, int travelRatio, boolean availability) {
        Ticket ticket = new Ticket();
        int pnr = new Random().nextInt(10000, 50000);
        ticketView.displayMessage("YOUR PNR NUMBER : " + pnr);
        ticket.setPnrNumber(pnr);
        ticket.setPassengerList(passengers);
        ticket.setFlight(flight);
        ticket.setStatus("CNF");
        ticket.setFlightNumber(flight.getFlightNumber());
        int fare = flight.getFare() / flight.getRoutes().size() * travelRatio;
        ticket.setFare(fare);
        if (!availability) ticket.setStatus("WL");
        addTicket(ticket);
    }

    private void addTicket(Ticket ticket) {
        List<Ticket> tickets = DataBase.getInstance().getTickets();
        tickets.add(ticket);
        DataBase.getInstance().setTickets(tickets);
        String message = " BOOKED SUCCESSFULLY";
        ticketView.displayMessage(message);
    }

    public void getStatus(int pnr) {
        List<Ticket> tickets = DataBase.getInstance().getTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getPnrNumber() == pnr) ticketView.printTicket(ticket);
            return;
        }
        String message = "PNR NUMBER NOT AVAILABLE";
        ticketView.displayMessage(message);
    }

    public void doCancelTicket(int pnr) {
        List<Ticket> tickets = DataBase.getInstance().getTickets();
        int fare = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getPnrNumber() == pnr) {
                ticket.setStatus("CANCEL");
                fare = ticket.getFare();
                ticket.setFare(0);
                break;
            }
        }
        DataBase.getInstance().setTickets(tickets);
        String message = "Ticket Cancelled Successfully.Your refund" + fare + "will be processed soon";
        ticketView.displayMessage(message);
    }

    public void doUpdateTicketStatus(int pnr, String status) {
        List<Ticket> tickets = DataBase.getInstance().getTickets();
        Ticket ticket1 = null;
        for (Ticket ticket : tickets) {
            if (ticket.getPnrNumber() == pnr) {
                ticket1 = ticket;
                ticket.setStatus(status);
                break;
            }
        }
        DataBase.getInstance().setTickets(tickets);
        String message = "Ticket status updated as " + status;
        ticketView.displayMessage(message);
        if (ticket1 != null)
            ticketView.printTicket(ticket1);
    }

    public void doBookedTickets() {
        List<Ticket> tickets = DataBase.getInstance().getTickets();
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getStatus(), "BOOKED")) ticketView.printTicket(ticket);
        }
    }
}
