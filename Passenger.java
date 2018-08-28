import java.sql.*;
import java.util.*;
import java.lang.String;

public class Passenger{
   private String passengerID;
   private Conductor c;
   private String ticketID;
   private String setaNum;
   private String name;
   private int age;
   private String passengerType;
   static String url = "jdbc:mysql://localhost/traindatabase";
   static String user = "root";	
   static String password = "root";
   
    
   public Passenger(String passengerID){
      this.passengerID = passengerID;
   }
    
    
   public boolean checkPassengerDetails(String loginPass, String ticketID){
      boolean flag = false;
      try {
         String str = "select ticketID from passenger_ticket where passengerID = '"+loginPass+"' and ticketID ='"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next()){
            System.out.println("Login Successful!..."); 
            flag = true;    
         }
         else{
            System.out.println("Login Denied!...");
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      return flag;
   }
   //================================================
   public void boardTrain(String ticketID, String seatNum){
      c = new Conductor();
      if(c.checkTicketValidity(ticketID, seatNum)) System.out.println("BOARDING SIMULATOR [x!x]\n\nBoth ticket and seat are valid... SUCCESSFUL BOARDING!\n\n\n");
      else{
         if(!c.checkTicket(ticketID))
            System.out.println("Sorry, TicketID#"+ticketID+" is invalid! BOARDING DENIED!\n\n\n");
          
      }  
   }//end boardTrain()
   
   public void printPassengerTicket(String ticketID){
      Ticket t = new Ticket();
      String seatNum = t.getSeat(ticketID);
      String passengerName = t.getPassengerName(ticketID);
      String passengerAge = t.getPassengerAge(ticketID);
      String passengerType = t.getPassengerType(ticketID); 
      String origin = t.getOriginStation(ticketID);
      String destination = t.getDestinationStation(ticketID);
      String departureTime = t.getDepartureTime(ticketID);
      t = new Ticket(ticketID, seatNum, passengerName, passengerAge, passengerType, origin, destination, departureTime);
   
      t.generateTicket();
   }
}
