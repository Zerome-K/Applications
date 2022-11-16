## FLIGHT MANAGEMENT SYSTEM

  -> System to manage the flight services in Booking, Scheduling flights ,maintain passengers details.
  -> Flight, Passengers & Booking procedurals are handled in separate classes and modules to achieve real world OOPS entity and
     control the work flow for better optimization.
  -> Application is well organized with MODEL-CONTROl-VIEW pattern to manage modules in an effective manner.
  -> Create and Stored the datas in Local Database to fetch and update Datas.

## USE CASES COVERED

  -> Maintains Flight Information
  -> Booking Tickets
  -> Searching Trains Between Airports 

## CLASSES & PACKAGES
  
  -> POJO's              : Flight, Passengers, City, Ticket
  -> Controller Classes  : Flight Controller, Ticket Controller
  -> View Classes        : FlightView,TicketView
  
  #POJOS -> 
      1. Handles the flight datas, Passenger datas, City datas, Ticket info
      2. flight class having basic info about flight name-number-source-departure-... by this we can access the flight details
      3. Passenger class having the information about passenger name, id, contact
      4. City class can be used to store the flight travelling cities we can add extra info in every city airports in future.
  #CONTROLLER ->
      1. Flight Controller handles the all the flight accessing methods like addFlight(), flightvia(), showFlight().
      2. Ticket controller is for booking activites some functions are bookflight(), checkAvailableflight().
  #VIEWs
      1. FlightView interact with users for flight side activities.
      2. TicketView interact with users for booking related activities.
      
## Note : Build in processing... 
