import java.util.*;

public class BuyerRole{
   private String status = "Active";
   private CashierRole c;
   private String paymentMethod = "";
   private Ticket tUpdate;
   private Ticket t;
   
   Scanner kb = new Scanner(System.in);
   
   public void makeReservation(){
      c = new CashierRole();
      c.proceedToBooking();
   
   }//en purchase()    
   
   public void cancelReservation(String ticketID){
      System.out.println("Are you sure you want to cancel reservation: ");
      System.out.print("(y/n)?: ");
      String status = "";
      Scanner kb = new Scanner(System.in);
      status = kb.nextLine();
      t = new Ticket(ticketID);
   
      switch(status){
         case "n":
            System.out.println("No changes have been made to your reservation.");
            break;
      
         case "y":
            if(t.cancelTicket(ticketID))
               System.out.println("Your reservation has been cancelled successfully.");
            break;
      
         default:
            break;
      }
   
   }
   
   
   public void updateReservation(String ticketId){
      String status = "";
      System.out.println("Your are about to make changes to you reservation: ");
      System.out.println("Press y (YES) to proceed or n (NO) to cancel operation: ");
      Scanner kb = new Scanner(System.in);
      status = kb.nextLine();
      
      switch(status){
         case "n":
            System.out.println("No canges have been made to you reservation.");
            break;
         case "y":
            c = new CashierRole(); 
            tUpdate = new Ticket(ticketId);
            String ticketID = "";
            String seatNum = "";
            boolean scheduleFound = false;
            boolean seatFound = false;
         
            c.showSchedule();
         
               //Scanner kb = new Scanner(System.in);
            System.out.println("Input your departing station: ");
            String os = kb.nextLine();
            System.out.println("Input your destination station: ");
            String ds = kb.nextLine();
            System.out.println("Input your departure time: ");
            String dt = kb.nextLine();
            scheduleFound = c.checkAvailability("scheduleID","schedule", "where origin like '%"+os+"' and destination like '%"+ds+"' and departureTime like '%"+dt+"'");
            
            
            if(scheduleFound){
               seatFound = c.checkSeatAvailability(dt);
               
               if(seatFound){
                  System.out.println("\nA seat is available for you, please enter passenger's information: \n");
                  
                  System.out.println("==========================================================================");
                  ticketID = ticketId;
                  seatNum = tUpdate.getSeat(ticketID);
                  t = new Ticket(ticketID, seatNum, tUpdate.getPassengerName(ticketId), tUpdate.getPassengerAge(ticketId), tUpdate.getPassengerType(ticketId), os, ds, dt);
                  t.updateTicket(ticketID, seatNum, os, ds, dt);
                  t.generateTicket();
               }
               else{
                  System.out.println("Sorry, there are no seats left, please refere to the schedule and attempt another booking!");
                  c.showSchedule();     
               }
            }
            else{   
               while(!scheduleFound){
                  System.out.println("Sorry, there are no departures found, please refere to the schedule!");
                  c.showSchedule();
                  System.out.println("Input your departing station: ");
                  os = kb.nextLine();
                  System.out.println("Input your destination station: ");
                  ds = kb.nextLine();
                  System.out.println("Input your departure time: ");
                  dt = kb.nextLine();
                  scheduleFound = c.checkAvailability("scheduleID","schedule", "where origin like '%"+os+"' and destination like '%"+ds+"' and departureTime like '%"+dt+"'"); 
               }
               if(seatFound){
                  System.out.println("\nA seat is available for you, please enter passenger's information: \n");
                           
                  System.out.println("==========================================================================");
                  
                  t= new Ticket(ticketID, seatNum, tUpdate.getPassengerName(ticketId), tUpdate.getPassengerAge(ticketId), tUpdate.getPassengerType(ticketId), os, ds, dt);
                  t.updateTicket(ticketID, seatNum, os, ds, dt);
                  System.out.println("\nCongratulation, your reservation is complete :)\n\n");
                  t.generateTicket();
               }
               else{
                  System.out.println("Sorry, there are no seats left, please refere to the schedule and attempt another booking!");
                  c.showSchedule();
               }
            }  
         
            break;
            
         default:
            System.out.println("No canges have been made to you reservation.");
            break;
      }
   }//end updateReservation()
   
      
 
//    public static void main(String[] args){
//     
//       BuyerRole br = new BuyerRole();
//    
//      br.makeReservation();
//      // br.updateReservation("1791501");
//       //br.cancelReservation("1791501");
//    }

}