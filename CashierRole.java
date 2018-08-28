import java.sql.*;
import java.lang.String;
import java.util.*;

public class CashierRole{
   private Ticket t;
   private String cashierID = "";
   static String url = "jdbc:mysql://localhost/traindatabase";
   static String user = "root";	
   static String password = "root";
   static int count = 1;
   static boolean valid = true;
   public CashierRole(){
   
   }
   public CashierRole(String cashierID){
      this.cashierID = cashierID;
   }
   
   public boolean checkCashierDetails(String cashierIdentifier, String loginPass){
      boolean flag = false;
      try {
         String str = "select cashierID from cashiers where loginPass = '"+loginPass+"' and cashierID ='"+cashierIdentifier+"';";
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


   public void showSchedule(){
   //go to database and show schedule
      String str = "select * from schedule;";
      String [] results = {""};
      try{
         Class.forName( "com.mysql.jdbc.Driver" ); 		
      
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         ResultSetMetaData metaData = rs.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
            
         results = new String [numberOfColumns];
         for(int i = 0; i<numberOfColumns; i++){
            results[i]="";
         }
         System.out.println("TRAIN DEPARTURES:\n");
         System.out.printf("%s%s%s%s%s%s", "schedule# ", " Itenerary# ", "  Origin ","     Destination ", " DepartureTime ", "ArrivalTime\n");
         
         while (rs.next()){
            for(int i=1; i<= 2; ++i)
               System.out.print(" "+rs.getObject(i)+"         ");
               
            for(int i=3; i<= 4; ++i)
               System.out.print(" "+rs.getObject(i)+"   ");
               
            for(int i=5; i<= results.length; ++i)
               System.out.print(" "+rs.getObject(i)+"      ");
         
            System.out.println("");
         }
         System.out.println("\n\n");
      }
      catch(Exception x){
         System.out.println("System intrrupted by "+x);
      }
   }//end ShowSchedule()
   
   public boolean checkAvailability(String column, String table, String condition){
      boolean flag = false;
      try {
         String str = "select "+column+" from "+table+" "+condition+";";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
      
         Connection cx = DriverManager.getConnection( url, user, password );
      
         Statement st = cx.createStatement();
      
         ResultSet rs = st.executeQuery(str);
      
         ResultSetMetaData metaData = rs.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
            
         if (rs.next()){
            if(!(((rs.getObject(1)+"").substring(0,1)).equals("Empty")))
               flag = true;
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      return flag;
   
   }//end checkAvailability()
   
   //Check seat availability
   public boolean checkSeatAvailability(String departureTime){
      boolean flag = false;
      try {
      
         String str = "select departureID from departures where departureTime like '%"+departureTime+"' and numOfRemainingSeats > 0;";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
      
         Connection cx = DriverManager.getConnection( url, user, password );
      
         Statement st = cx.createStatement();
      
         ResultSet rs = st.executeQuery(str);
      
         ResultSetMetaData metaData = rs.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
            
         if (rs.next()){
            if(!(((rs.getObject(1)+"").substring(0,1)).equals("Empty")))
               flag = true;
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      return flag;
   }
   
  //check payment validity
   public boolean checkPaymentValidity(String paymentMethod){
      if(paymentMethod.equalsIgnoreCase("bank") || paymentMethod.equalsIgnoreCase("credit"))
         return true;
      return false;
   }
   
   //Authorize booking
   public void proceedToBooking(){
      String ticketID = "";
      String seatNum = "";
      boolean scheduleFound = false;
      boolean seatFound = false;
         
      showSchedule();
   
      Scanner kb = new Scanner(System.in);
      System.out.println("DEPARTING STATION: ");
      String os = kb.nextLine();
      System.out.println("DESTINATION STATION: ");
      String ds = kb.nextLine();
      System.out.println("DEPARTING TIME: ");
      String dt = kb.nextLine();
      scheduleFound = checkAvailability("scheduleID","schedule", "where origin like '%"+os+"' and destination like '%"+ds+"' and departureTime like '%"+dt+"'");
       
      
      if(scheduleFound){
         seatFound = checkSeatAvailability(dt);
            
         if(seatFound){
            System.out.println("\nA seat is available for you, please enter passenger's information: \n");
            kb = new Scanner(System.in);
            System.out.println("ENTER PASSENGER'S NAME: ");
            String name = kb.nextLine();
            System.out.print("ENTER PASSENGER'S AGE: ");
            String age = kb.nextLine();
            System.out.print("ENTER PASSENGER'S TYPE: (Adult, Old, Child)");
            String type = kb.nextLine();
            System.out.println("ENTER PAYMENT METHOD: (Bank, Credit)");
            String payment = kb.nextLine();
            
            while(!checkPaymentValidity(payment)){
               
               System.out.println("Payment Method is invalid, please");
               System.out.print("enter a valid payment Method: (Bank, Credit)");
               payment = kb.nextLine();
               count++;
               if(count > 5) { valid = false;
                  break;}
            }
         
            System.out.println("==========================================================================");
            try{
               String url = "jdbc:mysql://localhost/traindatabase";
               String user = "root";	
               String password = "root";
            
               Class.forName( "com.mysql.jdbc.Driver" ); 		
               Connection cx = DriverManager.getConnection( url, user, password );
               Statement st = cx.createStatement();
            
               String sql_select = "SELECT ticketID, seatNum from ticket order by ticketID DESC LIMIT 1;";
            
               ResultSet rs = st.executeQuery(sql_select);
               if(rs.next()){
                  ticketID = rs.getString(1);
                  seatNum = rs.getString(1);
                  seatNum = String.valueOf(Integer.parseInt(seatNum)+1);
                  ticketID = String.valueOf(Integer.parseInt(ticketID)+1);
               }
               t = new Ticket(ticketID, seatNum, name, age, type, os, ds, dt);
               t.saveTicket();
               t.generateTicket();
            }
            catch(Exception x){
               System.out.println("ProceedToBooking is intrupted by: "+x);
            }
         }
         else{
            System.out.println("\nSorry, there are no seats left, please refere to the schedule and attempt another booking!\n");
            showSchedule();     
         }
      }
      else{   
         while(!scheduleFound){
            System.out.println("Sorry, there are no departures found, please refere to the schedule!");
            showSchedule();
            System.out.println("Input your departing station: ");
            os = kb.nextLine();
            System.out.println("Input your destination station: ");
            ds = kb.nextLine();
            System.out.println("Input your departure time: ");
            dt = kb.nextLine();
            scheduleFound = checkAvailability("scheduleID","schedule", "where origin like '%"+os+"' and destination like '%"+ds+"' and departureTime like '%"+dt+"'"); 
         }
         if(seatFound){
            System.out.println("\nA seat is available for you, please enter passenger's information: \n");
            kb = new Scanner(System.in);
            System.out.println("Input passenger's name: ");
            String name = kb.nextLine();
            System.out.print("Input passenger's age: ");
            String age = kb.nextLine();
            System.out.print("Input passenger's type: (Adult, Old, Child)");
            String type = kb.nextLine();
            System.out.println("Enter payment Method: (Bank, Credit)");
            String payment = kb.nextLine();
            
            while(!checkPaymentValidity(payment)){
               
               System.out.println("Payment Method is invalid, please");
               System.out.print("enter a valid payment Method: (Bank, Credit)");
               payment = kb.nextLine();
               count++;
               if(count > 5) { valid = false;
                  break;}
            }
            
            
            System.out.println("==========================================================================");
            
            t = new Ticket(ticketID, seatNum, name, age, type, os, ds, dt);
            t.saveTicket();
            t.generateTicket();
         }
         else{
            System.out.println("\nSorry, there are no seats left, please refere to the schedule and attempt another booking!\n");
            showSchedule();
         }
      }  
   }
   
   
         //==================================================
   public void seeAllTickets(){
      String [] results = {""};
      try {
         String str = "select departureID, numOfRemainingSeats from departures;";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
         ResultSetMetaData metaData = rs.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
            
      
         System.out.printf("%s%s", " DEPARTURE ", "   REMAINING\n"); 
         System.out.printf("%s%s"," SPAIN     ", "   TICKETS\n");   
         results = new String [numberOfColumns];
         for(int i = 0; i<numberOfColumns; i++){
            results[i]="";
         }
         int i = 1;
         while (rs.next()){
            for(i=1; i<= results.length; i++)
               System.out.print(" "+rs.getString(i)+"          ");
            if(i%2 == 1) System.out.println();
         }   
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      
   }//end seeAllTickets
   
   //=======================================================
   public void showAllTickets(){
   //go to database and show schedule
      String str = "select * from ticket;";
      String [] results = {""};
      try{
         Class.forName( "com.mysql.jdbc.Driver" ); 		
      
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         ResultSetMetaData metaData = rs.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
            
         results = new String [numberOfColumns];
         for(int i = 0; i<numberOfColumns; i++){
            results[i]="";
         }
         System.out.println("ALL TICKETS:\n");
         System.out.printf("%s%s%s%s%s%s%s%s", "ticketID#", "     seatNum#", "    Name","           Age", "      Type", "      Origin", "     Destination","   Time\n");
         
         while (rs.next()){
         
               System.out.print(rs.getObject(1));
            for(int i=2; i<= results.length; ++i)
               System.out.print(rs.getString(i)+""+printSpace(12-rs.getString(i).length()));
         
            System.out.println("");
         }
         System.out.println("\n\n");
      }
      catch(Exception x){
         System.out.println("System intrrupted by "+x);
      }
   }//end ShowSchedule()
   
   public String printSpace(int s){
      for(int i = 1; i <= s; i++)
      System.out.print(" ");
      return "";
   }

   public static void main(String[] args){
      
      CashierRole cr = new CashierRole();
      cr.proceedToBooking();
   }
}