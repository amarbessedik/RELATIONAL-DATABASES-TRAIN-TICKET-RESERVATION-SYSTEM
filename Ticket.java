import java.sql.*;
import java.util.*;
import java.lang.String;

public class Ticket{
   static String url = "jdbc:mysql://localhost/traindatabase";
   static String user = "root";	
   static String password = "root";
   
   private String ticketID;
   private String seatNum;
   private String passengerName;
   private String passengerAge;
   private String passengerType;
   private String departureStation;
   private String destinationStation;
   private String departureTime;
  
   public Ticket(String ticketID, String seatNum, String passengerName,String passengerAge, String passengerType, String departureStation, String destinationStation, String departureTime){
      this.ticketID = ticketID;
      this.seatNum = seatNum;
      this.passengerName = passengerName;
      this.passengerAge = passengerAge;
      this.passengerType = passengerType;
      this.departureStation = departureStation;
      this.destinationStation = destinationStation;
      this.departureTime = departureTime;
   }
   
   public Ticket(String ticketID){
      this.ticketID = ticketID;
   }
   public Ticket(){
   
   }
   
   public Ticket(String ticketID, String seatNum){
      this.ticketID = ticketID;
      this.seatNum = seatNum;
   }
   
   public String getTicketID(){
      return ticketID;
   }
  
  //================================================
   public String getSeat(String ticketID){
      
      try{
         
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         
         String str = "select seatNum from ticket where ticketID = '"+ticketID+"';";
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next())
            seatNum = rs.getString(1);   
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   
      return seatNum;
   }
   
   //==============================================
   public String getDepartureTime(String ticketID){
      
      try{
         
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         
         String str = "select departureTime from ticket where ticketID = '"+ticketID+"';";
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next())
            departureTime = rs.getString(1);   
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   
      return departureTime;
   }
   
   ///=====================================================
   public String getOriginStation(String ticketID){
      
      try{
         
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         
         String str = "select departureStation from ticket where ticketID = '"+ticketID+"';";
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next())
            departureStation = rs.getString(1);   
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   
      return departureStation;
   }
//========================================================

   public String getDestinationStation(String ticketID){
      
      try{
         
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         
         String str = "select destinationStation from ticket where ticketID = '"+ticketID+"';";
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next())
            destinationStation = rs.getString(1);   
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   
      return destinationStation;
   }



//==========================================================
   
   public void setSeat(String seatNum){
      this.seatNum = seatNum;   
   }
   
   public void generateTicket(){
      System.out.println("\nTraveler's ticket details are being generated:\n");
      System.out.println("ticketID: "+ticketID+
                      "\nseatNum: "+seatNum+
                      "\npassengerName: "+ passengerName+
                      "\npassengerAge: "+passengerAge+
                      "\npassengerType: "+passengerType+
                      "\ndepartureStation: "+departureStation+
                      "\ndestinationStation: "+destinationStation+
                      "\ndepartureTime: "+departureTime+"\n\n\n");
   }
   //==================================================
   public void savePassenger(String ticketID){
      try{
         String url = "jdbc:mysql://localhost/traindatabase";
         String user = "root";	
         String password = "root";
      
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
      
         String passengerID = "";
         String sql_select = "SELECT passengerID from passenger_ticket order by passengerID DESC LIMIT 1;";
      
         ResultSet rs = st.executeQuery(sql_select);
         if(rs.next())passengerID = rs.getString(1);
         passengerID = String.valueOf(Integer.parseInt(passengerID)+1);
      
         String sql_update = "INSERT INTO passenger_ticket VALUES ('"+passengerID+"','"+ticketID+"')";
         st.executeUpdate( sql_update );
         System.out.println("\nPassenger Identification Number: "+passengerID+". Please keep for record!\n");
      
      }
      catch( Exception x ) {
         System.err.println( x );
      }
   
   }
   
   
   //==================================================
   public void saveTicket(){
      try{
         String url = "jdbc:mysql://localhost/traindatabase";
         String user = "root";	
         String password = "root";
      
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
      
         String sql_select = "SELECT ticketID from ticket order by ticketID DESC LIMIT 1;";
      
         ResultSet rs = st.executeQuery(sql_select);
         if(rs.next())ticketID = rs.getString(1);
         seatNum = this.getSeat(ticketID);
         seatNum = String.valueOf(Integer.parseInt(seatNum)+1);
         ticketID = String.valueOf(Integer.parseInt(ticketID)+1);
      
            
         String[] values = {ticketID, seatNum, passengerName, passengerAge , passengerType, departureStation, destinationStation, departureTime};
         String sql_insert1 = "INSERT INTO Ticket VALUES " + 
            "("  + "'" + values[0] + "'" + "," + "'" + values[1] + "'" + "," + "'" + values[2] + "'" + "," + "'" + values[3] + "'" + "," + "'" + values[4] + "'" + "," + "'" + values[5] + "'" + ","+"'" + values[6] + "'"+","+"'" + values[7] + "'"+ ")";
         String sql_insert2 = "INSERT INTO ticket_seat VALUES " + "("  + "'" + values[0] + "'" + "," + "'" + values[1] + "')";
         String sql_update1 = "UPDATE departures SET numOfRemainingSeats = numOfRemainingSeats - 1 where departureTime = '"+values[7]+"';";
      
         st.executeUpdate( sql_insert1 );
         st.executeUpdate( sql_insert2 );
         st.executeUpdate( sql_update1 );
         this.savePassenger(ticketID);
      
      }
      catch( Exception x ) {
         System.err.println( x );
      }
   
   }
   //=====================Update ticket
   
   public void updateTicket(String ticketID, String seatNum, String departureStation, String destinationStation, String departureTime){
      //Scanner kb = new Scanner(System.in);
      try{
         String sql_update = "UPDATE ticket SET departureStation ='"+departureStation+"', destinationStation = '"+destinationStation+"', departureTime = '"+departureTime+"' where ticketID = '"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         
         String str = "select passengerAge from ticket where ticketID = '"+ticketID+"';";
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next())
            st.executeUpdate( sql_update );
         else
            System.out.println("WARNING: Reservation number "+ticketID+" does not exist!");   
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   }//end updateTicket()
   
   //=======================Cancel Ticket
   public boolean cancelTicket(String ticketID){
      boolean flag = false;
      Scanner kb = new Scanner(System.in);
      try{
         String sql_update1 = "DELETE from ticket where ticketID = '"+ticketID+"';";
         String sql_update2 = "DELETE from ticket_seat where ticketID = '"+ticketID+"';";
         String sql_update3 = "DELETE from passenger_ticket where ticketID = '"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         
         String str = "select passengerAge from ticket where ticketID = '"+ticketID+"';";
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next()){
            System.out.println("Are you sure you want to permanantly cancel ticket? (y/n): ");
            String yesNo = kb.nextLine();
            if(yesNo.equalsIgnoreCase("y")){
               st.executeUpdate( sql_update1 );
               st.executeUpdate( sql_update2 );
               st.executeUpdate( sql_update3 );
               flag = true;
            }
            else System.out.println("Ticket not cancelled!");
         }
         else
            System.out.println("The reservation number "+ticketID+" does not exist!");
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   
      return flag;
   }

   
   //====================================
   public String getPassengerName(String ticketID){
      try {
         String str = "select passengerName from ticket where ticketID = '"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next()){
            passengerName = rs.getString(1);     
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
   
      return passengerName;
   }
   
   public String getPassengerAge(String ticketID){
      try {
         String str = "select passengerAge from ticket where ticketID = '"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next()){
            passengerAge = rs.getString(1);     
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      return passengerAge;
   }
   
   public String getPassengerType(String ticketID){
      try {
         String str = "select passengerType from ticket where ticketID = '"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next()){
            passengerType = rs.getString(1);     
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      return passengerType;
   }

      
       //=======================
   public boolean checkTicketValidity(String ticketID){
      boolean flag = false;
      try {
         String str = "select ticketID from ticket where ticketID = '"+ticketID+"';";
         Class.forName( "com.mysql.jdbc.Driver" ); 		
         Connection cx = DriverManager.getConnection( url, user, password );
         Statement st = cx.createStatement();
         ResultSet rs = st.executeQuery(str);
            
         if (rs.next()){
            flag = true;     
         }
         else{
            System.out.println("\nThis ticket Number: ***"+ticketID+"*** does not exit!\n");
         }
      }
      catch( Exception x ) {
         System.out.println("Found is interrupted by "+x);
      }
      return flag;
   }

   
 
}//end class