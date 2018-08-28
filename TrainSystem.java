import java.lang.String;
import java.util.*;

public class TrainSystem{
   protected int admin;

   public TrainSystem(int admin){
      this.admin = admin;
   }
   

   public static void main(String[] args){
      while(true){
         TrainSystem ts = new TrainSystem(1);
         Scanner kb = new Scanner(System.in);
         System.out.println("\nMAIN MENU:\n\nENTER 1 FOR BUYER: \nENTER 2 FOR CASHIER: \nENTER 3 FOR TRAVELER: \nENTER 4 FOR CONDUCTOR: ");
         String status = kb.nextLine();
      //          while(!status.equals("1") || !status.equals("2") || !status.equals("3") || !status.equals("4")){
      //             System.out.println("Only Options (1, 2, 3, 4)are allowed! \nMAIN MENU:\n\nEnter 1 for buyer: \nEnter 2 for Cashier: \nEnter 3 for Passenger: \nEnter 4 for Conductor: ");
      //             status = kb.nextLine();
      //          }
         BuyerRole br = new BuyerRole();
         String ticketID = "";
         switch(status){
            case "1"://UC1
               System.out.println("\n\n   TO MAKE A RESERVATION, PLEASE ENTER 1:\n   TO MAKE CHANGES TO AN EXIXTING RESERVATION, PLEASE ENTER 2:\n   TO CANCEL AN EXISTING RESERVATION, PLEASE ENTER 3:\n");
               String buyerEvent = kb.nextLine();
               switch(buyerEvent){
                  case "1":
                     br.makeReservation();
                     System.out.println("WOULD YOU LIKE TO MAKE ANOTHER RESERVATION?(y/n)");
                     String yesNo = "";
                     yesNo = kb.nextLine();
                     while(yesNo.equalsIgnoreCase("y")){
                        br.makeReservation();//goto USE CASE UC1 main Senario
                        System.out.println("WOULD YOU LIKE TO MAKE ANOTHER RESERVATION?(y/n)");
                        yesNo = kb.nextLine();
                     }
                     System.out.println("-----------GOOD BYE---------!");
                     break; 
               
                  case "2": //USE CASE UC1.1 
                  
                     System.out.println("ENTER PASSENGER'S IDENTIFICATION NUMBER (Ex: 7234): ");
                     String password = kb.nextLine();
                     System.out.println("ENTER PASSENGER'S TICKET NUMBER (Ex: 1791501): ");
                     ticketID = kb.nextLine();
                     Passenger pr = new Passenger("1");
                     if(pr.checkPassengerDetails(password, ticketID)){
                     
                        Ticket t = new Ticket(ticketID);
                        if(t.checkTicketValidity(ticketID)){
                           br.updateReservation(ticketID);
                        }
                     }
                  
                     break;
                  case "3"://USE CASE UC1.2
                     System.out.println("ENTERE PASSENGER'S IDENTIFICATION NUMBER (Ex: 1234): ");
                     password = kb.nextLine();
                     System.out.println("ENTER PASSENGER'S TICKET NUMBER (Ex: 123456): ");
                     ticketID = kb.nextLine();
                     pr = new Passenger("1");
                     if(pr.checkPassengerDetails(password, ticketID)){
                       
                        Ticket t = new Ticket(ticketID);
                        if(t.checkTicketValidity(ticketID))
                           br.cancelReservation(ticketID);
                     }
                     break;
               }
               break;
            case "2"://USE CASE UC2
               System.out.println("ENTER CASHIER'S IDENTIFICATION NUMBER (Ex: 1234): ");
               String cashierIdentifier = kb.nextLine();
               System.out.println("ENTER PASSWORD: ");
               String password = kb.nextLine();
               CashierRole cr = new CashierRole("1");
               if(cr.checkCashierDetails(cashierIdentifier, password)){
                  System.out.println("\n--------------CASHIER CAN SEE IF TICKETS ARE ALL SOLD OUT--------------------\n\n");
                  cr.showAllTickets();
                  System.out.println("----------------------------------\n\n");
                  cr = new CashierRole(password);
                  cr.seeAllTickets();
                  System.out.println("----------------------------------\n\n");
               }
               else{
                  System.out.println("LOGIN FORBIDEN! INVALID PASSWORD!");//GOTO UC1 MAIN SCENARIO
               }
            
               break;
            
            case "3"://USE CASE UC3
               System.out.println("\n\n   TO PRINT OUT TICKET, PLEASE ENTER 1:\n   TO BOARD THE TRAIN, PLEASE ENTER 2:\n");
               String passengerEvent = "";
               passengerEvent = kb.nextLine();
               
               switch(passengerEvent){
                  case "1":
                     System.out.println("ENTERE PASSENGER'S IDENTIFICATION NUMBER (Ex: 1234): ");
                     password = kb.nextLine();
                     System.out.println("ENTER PASSENGER'S TICKET NUMBER (Ex: 123456): ");
                     ticketID = kb.nextLine();
                  
                     Passenger pr = new Passenger("1");
                     if(pr.checkPassengerDetails(password, ticketID)){
                        pr.printPassengerTicket(ticketID);
                     }
                     break;
               
                  case "2":
                     System.out.println("ENTERE PASSENGER'S IDENTIFICATION NUMBER (Ex: 1234): ");
                     password = kb.nextLine();
                     System.out.println("ENTER PASSENGER'S TICKET NUMBER (Ex: 123456): ");
                     ticketID = kb.nextLine();
                     pr = new Passenger("1");
                     if(pr.checkPassengerDetails(password, ticketID)){
                        System.out.println("ENTER YOUR SEAT NUMBER (Ex: 99): ");
                        String seatNum = kb.nextLine();
                        pr.boardTrain(ticketID, seatNum);
                     }
                     break;
               }
            
            
               break;
            
            case "4"://UC4
               System.out.println("ENTER CONDUCTOR'S IDENTIFICATION NUMBER: ");
               String conductorID = kb.nextLine();
               System.out.println("ENTER YOUR PASSWORD: ");
               String conductorPassword = kb.nextLine();
               
               Conductor c = new Conductor(conductorID);
               if(c.checkConductorDetails(conductorID, conductorPassword)){
                  
                  System.out.println("ENTER TICKET NUMBER HERE: ");
                  ticketID = kb.nextLine();
                  System.out.println("ENTER SEAT NUMBER HERE: ");
                  String seatNum = kb.nextLine();
                  if(!c.checkTicket(ticketID))
                     continue;
                  c.checkSeat(ticketID, seatNum);  
               }       
               break;
         }
      }//end while
   }//end main
}//end TrainSystem