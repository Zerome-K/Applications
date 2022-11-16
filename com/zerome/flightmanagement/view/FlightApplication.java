package flightmanagementsystem.view;

import flightmanagementsystem.model.Options;
import flightmanagementsystem.repository.DataBase;

import java.util.Scanner;

public class FlightApplication {

    private final Scanner scanner = new Scanner(System.in);

    private final FlightView flightView;
    private final PassengerView passengerView;
    private final TicketView ticketView;

    FlightApplication() {
        this.flightView = new FlightView();
        passengerView = new PassengerView();
        ticketView = new TicketView();
    }

    public void schedule() {
        System.out.print("ENTER NUMBER OF SCHEDULES : ");
        int schedules = scanner.nextInt();
        while (schedules-- > 0) {
            flightView.createFlight();
        }
        System.out.println("SCHEDULED SUCCESSFULLY");
        startUp();
    }

    public void startUp() {
        System.out.print("""
                1 - BOOKING
                2 - GET PNR STATUS
                3 - BOOKED TICKETS
                4 - CANCEL TICKETS
                5 - SEARCH PASSENGER
                6 - CHANGE TICKET STATUS OF A PASSENGER
                7 - LIST FLIGHT ROUTES
                8 - ADD FLIGHT ROUTES
                9 - EXIT
                CHOOSE OPTION : """);
        Options[] options = Options.values();
        while (true) {
            byte option = scanner.nextByte();
            if (option > 0 && option < 9) {
                Options choice = options[option - 1];
                executeMenu(choice);
                break;
            } else if (option == 9) {
                return;
            } else System.out.print("ENTER VALID OPTION : ");
        }
        startUp();
    }

    private void executeMenu(Options menu) {
        switch (menu) {
            case BOOKING -> ticketView.searchTrain();
            case PNR_STATUS -> ticketView.pnrStatus();
            case SEARCH_PASSENGER -> passengerView.searchPassenger();
            case UPDATE_STATUS -> ticketView.updateTicketStatus();
            case BOOKED_TICKETS -> ticketView.bookedTickets();
            case CANCEL_TICKETS -> ticketView.cancelledTicket();
            case LIST_ROUTES -> flightView.routes();
            case ADD_ROUTES -> System.out.println("kkk");
        }
    }

    public static void main(String[] args) {
        FlightApplication app = new FlightApplication();
        app.schedule();
    }
}
