import java.sql.*;
import java.lang.String;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class Conductor{
   private String conductorID = "";
   private Ticket ticket;
   static String url = "jdbc:mysql://localhost/traindatabase";
   static String user = "root";	
   static String password = "root";

   public Conductor(String conductorID){
      this.conductorID = conductorID;
   }
   
   public Conductor(){
      conductorID = "1";
   }   
   
   public boolean checkConductorDetails(String conductorIdentifier, String loginPass){
      boolean flag = false;
      try {
         String str = "select conductorID from conductors where loginPass = '"+loginPass+"' and conductorID ='"+conductorIdentifier+"';";
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
   }//end checkCashierDetails()
    
   public boolean checkTicket(String ticketID){
      //ticket = new Ticket(ticketID);
      // String ticketIDChecker = ticket.getTicketID();
      boolean flag = false;
       
      try{
         String url = "jdbc:mysql://localhost/traindatabase";
         String user = "root";	
         String password = "root";
      
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
      
         String sql_select = "SELECT ticketID from ticket where ticketID = '"+ticketID+"'";
               
         ResultSet rs = st.executeQuery(sql_select);
            
         if (rs.next())
            flag = true;
         else
            System.out.println("Sorry, Ticket is invalid!");
         
      }
      catch( Exception x ) {
         System.err.println( x );
      }
      return flag;
   }//endCheckTicket
    
   public boolean checkSeat(String ticketID, String seatNum){
      boolean flag = false;
      ticket = new Ticket(ticketID, seatNum);
      
      
      try{
         String url = "jdbc:mysql://localhost/traindatabase";
         String user = "root";	
         String password = "root";
      
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
      
         String sql_select = "SELECT ticketID from ticket_seat where ticketID = '"+ticketID+"' and seatNum = '"+seatNum+"'";       
      
         ResultSet rs = st.executeQuery(sql_select);
            
         if (rs.next()){
            if(!((rs.getString(1)).equals("Empty")))
               flag = true;
         }
      }
      catch( Exception x ) {
         System.err.println( x );
      }
       
      if(flag){
         System.out.println("--------------------------------------------");
         System.out.println("TICKET AND SEAT CHECHING IN PROGRESS ...");
         System.out.println("....");
         waiter(1);
         System.out.println("........");
         waiter(1);
         System.out.println("................");
         waiter(1);
         System.out.println(".............................");
         waiter(1);
         System.out.println("BOTH SEAT AND TICKET ARE VALID");
         System.out.println("---------------------------------------------\n\n");
      
      }
      else{
         String correctSeat = "";
         try{
            String url = "jdbc:mysql://localhost/traindatabase";
            String user = "root";	
            String password = "root";
         
            Class.forName( "com.mysql.jdbc.Driver" ); 		
            Connection cx = DriverManager.getConnection( url, user, password );
            Statement st = cx.createStatement();
         
            String sql_select = "SELECT seatNum from ticket where ticketID = '"+ticketID+"'";
         
            ResultSet rs = st.executeQuery(sql_select);
               
            if (rs.next())
               correctSeat = rs.getString(1);
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("INVALID SEAT NUMBER!\n\n\n                     WITH TICKET#: "+ticketID+", PASSENGER MUST TAKE SEAT# "+correctSeat);
            System.out.println("-----------------------------------------------------------------------");
         }
         catch( Exception x ) {
            System.err.println( x );
         }
      }
      return flag;
   }//endCheckSeat()
   
   
   
   public boolean checkTicketValidity(String ticketID, String seatNum){
      boolean flag = false;
     
     
      ticket = new Ticket(ticketID, seatNum);
      Conductor c = new Conductor();
      if(c.checkTicket(ticketID) && c.checkSeat(ticketID, seatNum)){
         //System.out.println("Ticket is valid!");
         flag = true;
      }
     
      return flag;
   }



   public String waiter(int i){
      try{
         Thread.sleep(i*1000);
      }
      catch(Exception e)
      {
         System.out.println("Exception caught");
      }
      return "";
   }//end waiter()

   public static void main(String[] args){
   
      Conductor c = new Conductor();
      String ticketID = "1791501";
      String seatNum = "21";
   
      boolean checkTicket = c.checkTicket(ticketID);
   
      System.out.println("Ticket Checking: "+checkTicket);
      
      
      boolean valid = c.checkTicketValidity(ticketID, seatNum);
      
      System.out.println("Boarding Validity: "+valid); 
      
   
   }//end main
}