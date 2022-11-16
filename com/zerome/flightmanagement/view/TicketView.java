package flightmanagementsystem.view;

import flightmanagementsystem.controller.TicketController;
import flightmanagementsystem.model.Flight;
import flightmanagementsystem.model.Passenger;
import flightmanagementsystem.model.Ticket;
import inventory.module.Product;

import java.util.*;

public class TicketView {

    private final Scanner scanner = new Scanner(System.in);
    private final TicketController ticketController;

    TicketView() {
        ticketController = new TicketController(this);
    }

    public void searchTrain() {
        System.out.print("FROM STATION : ");
        String fromStation = scanner.nextLine();
        System.out.print("TO STATION : ");
        String toStation = scanner.nextLine();
        ticketController.checkFlights(fromStation, toStation);
    }

    public void availableFlights(HashMap<Flight, Integer> availableFlight) {
        System.out.println("+------------------------------------------------------------------------------------------------------+");
        System.out.println("|" + centerString(10, "NUMBER") + "|" + centerString(15, "NAME") + "|" +
                centerString(15, "SOURCE") + "|" + centerString(15, "DESTINATION") + "|" +
                centerString(10, "DEPT TIME") + "|" + centerString(10, "ARR TIME") + "|" +
                centerString(8, "SEATS") + "|" + centerString(6,"FARE")+"|");
        System.out.println("+------------------------------------------------------------------------------------------------------+");

        for (Map.Entry<Flight, Integer> flight : availableFlight.entrySet()) {
            System.out.println("|" + centerString(10, flight.getKey().getFlightNumber() + "|" + centerString(15, flight.getKey().getFlightName())
                    + "|" + centerString(15, flight.getKey().getSource()) + "|" + centerString(15, flight.getKey().getDestination())
                    + "|" + centerString(10, flight.getKey().getDepartureTime())) + "|" + centerString(10, flight.getKey().getArrivalTime())
                    + "|" + centerString(8, String.valueOf(flight.getKey().getTotalSeats())) + "|" + centerString(6,String.valueOf(flight.getKey().getFare()))+"|");
        }
        System.out.println("+------------------------------------------------------------------------------------------------------+");
        System.out.print("SELECT TRAIN NUMBER :");
        int number = scanner.nextInt();
        for (Map.Entry<Flight, Integer> flight : availableFlight.entrySet()) {
            if (flight.getKey().getFlightNumber() == number) {
                System.out.print("ENTER NUMBER OF TICKETS : ");
                bookTicket(flight.getKey(), flight.getValue());
            }
        }
    }

    private String centerString(int width, String string) {
        return String.format("%-" + width + "s", String.format("%" + (string.length() + (width - string.length()) / 2) + "s", string));
    }

    private void bookTicket(Flight flight, int travelRatio) {
        System.out.print("ENTER NUMBER OF TICKETS : ");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();
        List<Passenger> passengersCount = new ArrayList<>();
        while (ticketCount-- > 0) {
            Passenger passenger = new PassengerView().addPassenger();
            passengersCount.add(passenger);
        }
        if (ticketCount < flight.getTotalSeats()) confirmation(flight, passengersCount, travelRatio);
        else ticketController.bookFlight(flight, passengersCount, travelRatio, false);
    }

    private void confirmation(Flight flight, List<Passenger> passengersCount, int travelRatio) {
        System.out.println("1. PAY" +
                "2. CANCEL" +
                "3. RESCHEDULE");
        byte option = scanner.nextByte();
        if (option == 1) {
            flight.setTotalSeats(flight.getTotalSeats() - passengersCount.size());
            ticketController.bookFlight(flight, passengersCount, travelRatio, true);
        } else if (option == 2) System.out.println("CANCELLED SUCCESSFULLY");
        else searchTrain();
    }


    public void pnrStatus() {
        System.out.print("ENTER PNR NUMBER : ");
        int pnr = scanner.nextInt();
        ticketController.getStatus(pnr);
    }

    public void updateTicketStatus() {
        System.out.print("ENTER PNR NUMBER : ");
        int pnr = scanner.nextInt();
        System.out.print("ENTER TICKET STATUS" +
                "1. CNF" +
                "2. CANCEL");
        byte option = scanner.nextByte();
        if (option == 1) ticketController.doUpdateTicketStatus(pnr, "CNF");
        else ticketController.doUpdateTicketStatus(pnr, "CANCEL");
    }

    public void cancelledTicket() {
        System.out.println("ENTER PNR NUMBER : ");
        int pnr = scanner.nextInt();
        ticketController.doCancelTicket(pnr);
    }

    public void bookedTickets() {
        ticketController.doBookedTickets();
    }

    public void printTicket(Ticket ticket) {
        System.out.println("-------------------------------------------");
        System.out.println("PNR          : " + ticket.getPnrNumber());
        System.out.println("NUMBER       : " + ticket.getFlightNumber());
        System.out.println("STATUS       : " + ticket.getStatus());
        System.out.println("FARE         : " + ticket.getFare()+"\n     PASSENGERS");
        for (Passenger passenger : ticket.getPassengerList()) {
            System.out.println("PASSENGER ID : " + passenger.getPassengerId());
            System.out.println("NAME         : " + passenger.getName());
            System.out.println("AGE          : " + passenger.getAge());
            System.out.println("GENDER       : " + passenger.getGender());
        }
        System.out.println("-------------------------------------------");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}