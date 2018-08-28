import java.sql.*;
import java.util.*;

public class Insert_Populate
{
   public static void main(String args[]) {
      try {
         String url = "jdbc:mysql://localhost/traindatabase";
         String user = "root";	
         String password = "root";
      
         Class.forName( "com.mysql.jdbc.Driver" ); 		
      
         Connection cx = DriverManager.getConnection( url, user, password );
      
         Statement st = cx.createStatement();
      
         String table = "Ticket";
      
         String[] values = { "1791501", "22","Jack ", "52" , "Adult", "STATION1", "STATION1", "10:05AM"};
      
         String sql_insert = "INSERT INTO " + table + " VALUES " + 
            "("  + "'" + values[0] + "'" + "," + "'" + values[1] + "'" + "," + "'" + values[2] + "'" + "," + "'" + values[3] + "'" + "," + "'" + values[4] + "'" + "," + "'" + values[5] + "'" + ","+"'" + values[6] + "'"+","+"'" + values[7] + "'"+ ")";
      
         System.out.println( "\n=> execute: " + sql_insert );
         st.executeUpdate( sql_insert );
      
      
      //-----------------DEPARTURES
         String table2 = "departures";
         String[][] departures = 
            { { "01", "A22", "10:05AM", "A1", "180", "0" },
              { "02", "B12", "11:00PM", "B2", "180", "2" },
              { "03", "G60", "11:55PM", "G3", "180", "12" },
            };
            
            //"scheduleID", "departureID", "Origin","Detination", "DepartureTime", "ArrivalTime"

      
         for (int i = 0; i < departures.length; ++i) {
            String sql_insert2 = "INSERT INTO " + table2 + " VALUES " + 
               "(" 
               + "'" + departures[i][0] + "'" + ","
               + "'" + departures[i][1] + "'" + ","
               + "'" + departures[i][2] + "'" + ","
               + "'" + departures[i][3] + "'" + ","
               + "'" + departures[i][4] + "'" + ","
               + "'" + departures[i][5] + "'"
               + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert2 );
            st.executeUpdate( sql_insert2 );
         } 
      
      //--------------------SCHEDULE
         String table3 = "schedule";
         String[][] schedule = 
              //"scheduleID", "departureID", "Origin","Detination", "DepartureTime", "ArrivalTime"
            { { "11", "01", "STATION1", "STATION2", "10:05AM", "10:55PM" },
              { "22", "02", "STATION2", "STATION3", "11:00PM", "11:50PM" },
              { "33", "03", "STATION3", "STATION4", "11:55PM", "12:50AM" }};
      
         for (int i = 0; i < schedule.length; ++i) {
            String sql_insert3 = "INSERT INTO " + table3 + " VALUES " + 
               "(" 
               + "'" + schedule[i][0] + "'" + ","
               + "'" + schedule[i][1] + "'" + ","
               + "'" + schedule[i][2] + "'" + ","
               + "'" + schedule[i][3] + "'" + ","
               + "'" + schedule[i][4] + "'" + ","
               + "'" + schedule[i][5] + "'" + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert3 );
            st.executeUpdate( sql_insert3 );
         } 
      
      
         //-----------------PAYMENT METHODS
         String table5 = "payment";
         String[][] payments = 
            { { "P01", "CREDIT" },
              { "P02", "BANK" }
            };
      
         for (int i = 0; i < payments.length; ++i) {
            String sql_insert5 = "INSERT INTO " + table5 + " VALUES " + 
               "(" 
               + "'" + payments[i][0] + "'" + ","
               + "'" + payments[i][1] + "'"
               + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert5 );
            st.executeUpdate( sql_insert5 );
         }
         
       //-----------------CASHIERS
         String table10 = "Cashiers";
         String[][] cashiers = 
            { { "6789", "9876" },
              { "2345", "5432" }
            };
      
         for (int i = 0; i < cashiers.length; ++i) {
            String sql_insert10 = "INSERT INTO " + table10 + " VALUES " + 
               "(" 
               + "'" + cashiers[i][0] + "'" + ","
               + "'" + cashiers[i][1] + "'"
               + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert10 );
            st.executeUpdate( sql_insert10 );
         }
         
         ///================CONDUCTORS
         String table11 = "CONDUCTORS";
         String[][] conductors = 
            { { "123456", "654321" },
              { "456789", "987654" }
            };
      
         for (int i = 0; i < conductors.length; ++i) {
            String sql_insert11 = "INSERT INTO " + table11 + " VALUES " + 
               "(" 
               + "'" + conductors[i][0] + "'" + ","
               + "'" + conductors[i][1] + "'"
               + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert11 );
            st.executeUpdate( sql_insert11 );
         }
         
         //------------------------Ticket_Seat

         String table6 = "Ticket_Seat";
         String[][] ticket_seats = 
            { { "1791501", "22" }};
      
         for (int i = 0; i < ticket_seats.length; ++i) {
            String sql_insert6 = "INSERT INTO " + table6 + " VALUES " + 
               "(" 
               + "'" + ticket_seats[i][0] + "'" + ","
               + "'" + ticket_seats[i][1] + "'"
               + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert6 );
            st.executeUpdate( sql_insert6 );
         }
         
         
//          //--------------------TRAINS
// 
//          String table7 = "Trains";
//          String[][] trains = 
//             { { "K8789", "TRAIN1", "A", "125" },
//               { "W5555", "TRAIN2", "A", "80" }
// 
//             };
//       
//          for (int i = 0; i < trains.length; ++i) {
//             String sql_insert7 = "INSERT INTO " + table7 + " VALUES " + 
//                "(" 
//                //trainID VARCHAR(15) UNIQUE NOT NULL, trainName VARCHAR(25), trainStatus VARCHAR(1), maxNumOfPassengers INT(3)
//                + "'" + trains[i][0] + "'" + ","
//                + "'" + trains[i][1] + "'" + ","
//                + "'" + trains[i][2] + "'" + ","
//                + "'" + trains[i][3] + "'"
//                + ")"
//                ;
//          
//             System.out.println( "\n=> execute: " + sql_insert7 );
//             st.executeUpdate( sql_insert7 );
//          }
         
                  //--------------------PASSENGERS

         String table8 = "PASSENGER_TICKET";
         String[][] passengers = 
            { { "1234", "1791501" }
            };
      
         for (int i = 0; i < passengers.length; ++i) {
            String sql_insert8 = "INSERT INTO " + table8 + " VALUES " + 
               "(" 
               + "'" + passengers[i][0] + "'" + ","
               + "'" + passengers[i][1] + "'"
               + ")"
               ;
         
            System.out.println( "\n=> execute: " + sql_insert8 );
            st.executeUpdate( sql_insert8 );
         }

      }
      catch( Exception x ) {
         System.err.println( x );
      }
   }
}