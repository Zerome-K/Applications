package flightmanagementsystem.view;

import flightmanagementsystem.controller.FlightController;
import flightmanagementsystem.model.City;
import flightmanagementsystem.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightView {
    private final FlightController flightController;
    private final Scanner scanner = new Scanner(System.in);

    public FlightView() {
        flightController = new FlightController(this);
    }

    public void createFlight() {
        System.out.print("FLIGHT NAME : ");
        String name = scanner.nextLine();
        System.out.print("FLIGHT NUMBER : ");
        String number = scanner.next();
        System.out.print("SOURCE : ");
        String source = scanner.next();
        System.out.print("DESTINATION : ");
        String destination = scanner.next();
        System.out.print("DEPARTURE TIME : ");
        String departureTime = scanner.next();
        System.out.print("ARRIVAL TIME : ");
        String arrivalTime = scanner.next();
        System.out.print("TOTAL SEATS : ");
        String seats = scanner.next();
        System.out.print("FARE PER TICKET : ");
        String fare = scanner.next();
        List<City> routes = this.via(source, destination);
        Flight flight = new Flight();
        flight.setFlightName(name);
        flight.setSource(source);
        flight.setDestination(destination);
        flight.setFlightNumber(Integer.parseInt(number));
        flight.setArrivalTime(arrivalTime);
        flight.setDepartureTime(departureTime);
        flight.setTotalSeats(Integer.parseInt(seats));
        flight.setFare(Integer.parseInt(fare));
        flight.setRoutes(routes);
        flightController.addFlight(flight);
    }

    private List<City> via(String source, String destination) {
        System.out.print("NO.OF VIAs : ");
        int count = scanner.nextInt();
        scanner.nextLine();
        City city = new City();
        city.setCityName(source);
        List<City> via = new ArrayList<>();
        via.add(city);
        while (count-- > 0) {
            System.out.print("ENTER CITY NAME : ");
            String cityName = scanner.nextLine();
            city = new City();
            city.setCityName(cityName);
            via.add(city);
        }
        city = new City();
        city.setCityName(destination);
        via.add(city);
        return via;
    }

    public void routes() {
        System.out.print("ENTER FLIGHT NUMBER : ");
        int number = scanner.nextInt();
        flightController.listRoutes(number);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayRoutes(List<City> routes) {
        System.out.println("[");
        for (City city : routes) {
            System.out.print(city.getCityName() + ',');
        }
        System.out.println("]");
    }
}
