## CAR PARKING MANAGEMENT APPLICATION
- Car parking application is to maintains car parking.
- This application covers the parking management features like checking free slots, assigning slots, release car from parking , service charges, customer details etc...
- The core concepts is when a user looks for a slot admin check for availability, if any availability then proceed for next steps.
- After check for availability the system noted the current time as in time of the user parked, by this the user status also updated.
- If any user leaves from parking leaving time is noted by this in time and out time charges are collect.
- It also provides a view of parking slot.
- Maintains a record of freeslots, revenue, parkers details are maintained.

## WORK FLOW
- ParkerDetail maintains the parker details about name, vehicle number etc all fields are validated by regex.
- Main class handles the overall workflow of the parking management application.
- Admin class is for admin to work with the car parking managemnet.
- The Floor class is to maintains the parking area for further processing.

Technology used

- JAVA
