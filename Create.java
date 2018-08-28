import java.sql.*;
import java.util.*;

public class Create
{
   public static void main(String args[]) {
      try {
         String url = "jdbc:mysql://localhost/traindatabase";
      
         String user = "root";
      
         String password = "root";
      
         Class.forName( "com.mysql.jdbc.Driver" ); 
      
      
         Connection cx = DriverManager.getConnection( url, user, password );
      
         Statement st = cx.createStatement();
         String table1 = "Ticket";
         String sql_drop1 = "DROP TABLE IF EXISTS " + table1;
         System.out.println( "\n=> execute: " + sql_drop1 );
         st.executeUpdate( sql_drop1 );
      
         String table1_def =
            "ticketID VARCHAR(25) UNIQUE NOT NULL, seatNum VARCHAR(3) UNIQUE NOT NULL, passengerName VARCHAR(80), passengerAge VARCHAR(3), passengerType VARCHAR(20), departureStation VARCHAR(20), destinationStation VARCHAR(20), departureTime VARCHAR(8)";
         String sql_create1 = "CREATE TABLE " + table1 + "(" + table1_def + ")";
         System.out.println( "\n=> execute: " + sql_create1 );
         st.executeUpdate( sql_create1 );
         
         String table2 = "Departures";
         String sql_drop2 = "DROP TABLE IF EXISTS " + table2;
         System.out.println( "\n=> execute: " + sql_drop2 );
         st.executeUpdate( sql_drop2 );
      
         String table2_def =
            "departureID VARCHAR(25) UNIQUE NOT NULL, stationID VARCHAR(15), departureTime VARCHAR(15), trainID VARCHAR(80), trainCapacity INT(3), numOfRemainingSeats INT(3)";
         String sql_create2 = "CREATE TABLE " + table2 + "(" + table2_def + ")";
         System.out.println( "\n=> execute: " + sql_create2 );
         st.executeUpdate( sql_create2 );
         
         String table3 = "Ticket_Seat";
         String sql_drop3 = "DROP TABLE IF EXISTS " + table3;
         System.out.println( "\n=> execute: " + sql_drop3 );
         st.executeUpdate( sql_drop3 );
         String table3_def = "ticketID VARCHAR(20) UNIQUE NOT NULL, seatNum VARCHAR(20) UNIQUE NOT NULL";
         String sql_create3 = "CREATE TABLE " + table3 + "(" + table3_def + ")";
         System.out.println( "\n=> execute: " + sql_create3 );
         st.executeUpdate( sql_create3 );
         
      
         String table6 = "Schedule";
         String sql_drop6 = "DROP TABLE IF EXISTS " + table6;
         System.out.println( "\n=> execute: " + sql_drop6 );
         st.executeUpdate( sql_drop6 );
         String table6_def =
            "scheduleID VARCHAR(25) UNIQUE NOT NULL, departureID VARCHAR(25), Origin VARCHAR(25), Destination VARCHAR(25), departureTime VARCHAR(25), arrivalTime VARCHAR(25)"
            ;
         String sql_create6 = "CREATE TABLE " + table6 + "(" + table6_def + ")";
         System.out.println( "\n=> execute: " + sql_create6 );
         st.executeUpdate( sql_create6 );
      
         
         String table8 = "Payment";
         String sql_drop8 = "DROP TABLE IF EXISTS " + table8;
         System.out.println( "\n=> execute: " + sql_drop8 );
         st.executeUpdate( sql_drop8 );
         String table8_def =
            "paymentID VARCHAR(25) UNIQUE NOT NULL, paymentMethod VARCHAR(25) UNIQUE NOT NULL";
         String sql_create8 = "CREATE TABLE " + table8 + "(" + table8_def + ")";
         System.out.println( "\n=> execute: " + sql_create8 );
         st.executeUpdate( sql_create8 );
         
         String table9 = "PASSENGER_TICKET";
         String sql_drop9 = "DROP TABLE IF EXISTS " + table9;
         System.out.println( "\n=> execute: " + sql_drop9 );
         st.executeUpdate( sql_drop9 );
         String table9_def =
            "passengerID VARCHAR(25) PRIMARY KEY UNIQUE NOT NULL, ticketID VARCHAR(25) UNIQUE NOT NULL";
         String sql_create9 = "CREATE TABLE " + table9 + "(" + table9_def + ")";
         System.out.println( "\n=> execute: " + sql_create9 );
         st.executeUpdate( sql_create9 );
         
         String table10 = "Cashiers";
         String sql_drop10 = "DROP TABLE IF EXISTS " + table10;
         System.out.println( "\n=> execute: " + sql_drop10 );
         st.executeUpdate( sql_drop10 );
         String table10_def =
            "cashierID VARCHAR(25) PRIMARY KEY UNIQUE NOT NULL, loginPass VARCHAR(25) UNIQUE NOT NULL";
         String sql_create10 = "CREATE TABLE " + table10 + "(" + table10_def + ")";
         System.out.println( "\n=> execute: " + sql_create10 );
         st.executeUpdate( sql_create10 );
         
         String table11 = "Conductors";
         String sql_drop11 = "DROP TABLE IF EXISTS " + table11;
         System.out.println( "\n=> execute: " + sql_drop11 );
         st.executeUpdate( sql_drop11 );
         String table11_def =
            "conductorID VARCHAR(25) PRIMARY KEY UNIQUE NOT NULL, loginPass VARCHAR(25) UNIQUE NOT NULL";
         String sql_create11 = "CREATE TABLE " + table11 + "(" + table11_def + ")";
         System.out.println( "\n=> execute: " + sql_create11 );
         st.executeUpdate( sql_create11 );
      
      
             
      }
      catch( Exception x ) {
         System.err.println( x );
      }
   }
}