import java.util.*;

public class main{
   
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      //variables
      int choice;
      int userType = 0;
      double newBalance=0;
      boolean identity=true;
      Customer customer=null;
   
       // Set up the Game Center and its devices
      GameCenter gameCenter = new GameCenter("PHASE2", 5);
      Device playStation  = new PlayStation("PlayStation 4", 60);
      Device personalComp = new PCStation("Personal Computer - MaciOS", 100, 32);
      Device vReality     = new VRStation("VR Set", 150, 32);
      gameCenter.addDevice(playStation);
      gameCenter.addDevice(personalComp);
      gameCenter.addDevice(vReality);
   
      System.out.println("=== Welcome to CSC 113 Game Center ===");
   
      // OUTER LOOP: identity question first to find out who entered the system
      do {
         while (true) {
            try {
               System.out.println("* * * *\nAre you:\n1. Customer\n2. Staff\n3. Exit");
               userType = input.nextInt();
               break;
            } catch (InputMismatchException e) {
               System.out.println("INVALID: Please enter an integer ONLY between (1-3).");
               input.next();
            }
         }
      
            // Customer logic: customer frame pops up
         if (userType == 1) {
                // Check if we already have a customer from a previous visit
            if (customer == null) {
               InputFrame gui = new InputFrame(gameCenter);
               gui.setVisible(true);
            
               // Wait until GUI is closed
               while (gui.isVisible()) {
                  try {
                     Thread.sleep(500);
                  } catch (Exception e) {}
               }
                    
               // Retrieve the customer created by the GUI
               customer = gui.getCustomer();
            }
         
            // If customer is still null (they closed GUI without submitting)
            if (customer == null) {
               System.out.println("No customer profile created. Returning to main menu...");
            } else {
               System.out.println("Logged in as: " + customer.getName());            
               do {
                  while(true){
                     try{
                        System.out.println("   =========  CUSTOMER MENU  =========");
                        System.out.println("1. Book a session.\n2. End session.\n3. Recharge balance.");
                        System.out.println("4. View memberships.\n5. View my information.");
                        System.out.println("6. Back to main menu.");
                        System.out.print("Enter choice: ");
                        choice = input.nextInt();
                        break;
                     } catch (InputMismatchException e){
                        System.out.println("INVALID: please enter an integer between (1-6).");
                        input.nextLine();
                     }
                  }
               
                  switch (choice) {
                  
                     // Book a session
                     case 1:
                        input.nextLine();
                        System.out.print("Please enter your desired start time in 00:00 pm/am format: ");
                        String time = input.nextLine();
                        int duration=0;
                        while(true){
                           try{
                              System.out.print("Enter duration in hours: ");
                              duration = input.nextInt();
                              break;
                           } catch(InputMismatchException e){
                              System.out.println("INVALID: Enter a valid duration (integer).");
                              input.nextLine();
                           }
                        }
                     
                        int deviceChoice=1;
                        Device d = null;
                        do {
                           while(true){
                              try{
                                 System.out.println("1. PC\n2. PlayStation\n3. VR");
                                 System.out.print("Enter choice: ");
                                 deviceChoice = input.nextInt();
                                 break;
                              } catch (InputMismatchException e){
                                 System.out.println("INVALID: Enter a valid choice (integer).");
                                 input.nextLine();
                              }
                           }
                           //customer will choose a device
                           switch(deviceChoice) {
                              case 1:
                                 d=gameCenter.searchDevice("PC"); 
                                 break;
                              case 2:
                                 d=gameCenter.searchDevice("PS"); 
                                 break;
                              case 3:
                                 d=gameCenter.searchDevice("VR"); 
                                 break;
                              default: System.out.println("INVALID: Enter a number between (1-3).");
                           }
                        } while (deviceChoice != 1 && deviceChoice != 2 && deviceChoice != 3);
                     
                     // In case chosen device type is either booked or does not exist
                        if (d == null || d.isBooked()) {
                           break;
                        }
                     
                     // Create session after collecting all needed information
                        try {
                           Session session = new Session(time, duration, customer, d);
                        
                        // In case time format is wrong
                           if(!session.getIsTimeSet()){
                              System.out.println("INVALID: Incorrect time format. Use 00:00 pm/am.\nSession not made.");
                              break;
                           }
                        
                        // Update user balance
                           double cost=session.calculateCost();
                           System.out.println("The price of the session will be "+ cost+" SAR.");
                           customer.setBalance(customer.getBalance() - cost);
                        
                           gameCenter.addSession(session);
                           d.setBooked(true);
                           System.out.println("Session added successfully!");
                        
                        } catch (NoBalanceException e) {
                           System.out.println(e.getMessage()+"\nSession not made.");
                        
                        } catch (IllegalArgumentException e) {
                           System.out.println("INVALID: Incorrect time format. \nSession not made.");
                        }   
                        break;
                     
                  // End session
                     case 2:
                        int choiceID=1;
                        boolean sessionFound=false;
                     
                     // Check if there are any active sessions to begin with
                        if(gameCenter.sCount==0) {
                           System.out.println("There are no reserved sessions to be removed.");
                           break;
                        }
                        else {
                        // List all current active sessions
                           System.out.println("===* SESSION INFORMATION *===");
                           for(int j=0;j<GameCenter.sCount;j++)
                              System.out.println((j+1)+"- Device Used: "+gameCenter.sessions[j].getDeviceName()+" | Start Time: "+gameCenter.sessions[j].getTime() );
                        
                           while(true){
                              try{
                                 System.out.println("Enter the number of the session you'd like to end: ");
                                 choiceID=input.nextInt();
                                 if(choiceID>GameCenter.sCount || choiceID<=0){
                                    System.out.println("INVALID: Enter a number from the list above."); 
                                    continue;
                                 }
                                 break;
                              } catch (InputMismatchException e){
                                 input.nextLine();
                                 System.out.println("INVALID: Enter a number from the list above.");
                              }
                           }
                        
                           
                        // End the session and remove it & print receipt
                           String str=gameCenter.sessions[(choiceID-1)].generateSessionReceipt();
                           System.out.println("RECEIPT:\n"+str);
                           gameCenter.removeSession(gameCenter.sessions[(choiceID-1)].getSessionID());
                           System.out.println("Session ended. Thank you for using our service.");
                           sessionFound=true;
                        }
                        if(sessionFound==false)
                           System.out.println("INVALID: No session found.");
                                      
                        break;
                     
                  // Recharge balance
                     case 3:
                        System.out.println("Your current balance: "+ customer.getBalance());
                        while(true){
                           try{
                              System.out.print("Enter how much more balance you would like to load: ");
                              double amount=input.nextDouble();
                              if(amount<0)
                                 amount=(-1*amount);
                              customer.recharge(amount);
                              break;
                           } catch (InputMismatchException e){
                              System.out.println("INVALID: Enter a number please (integer).");
                              input.nextLine();
                           }
                        }
                        break;
                     
                  // View all membership information //
                     case 4:
                        Membership.displayMembershipInfo();
                        break;
                     
                  // View my (customer) information //
                     case 5:                                          
                        System.out.println(customer.getCustomerInfo());       
                        break;
                  
                  // Return to the main menu //
                     case 6:
                        System.out.println("Returning to main menu...");
                        break;
                     
                  // If user enters an invalid choice //
                     default:
                        System.out.println("INVALID: Enter a choice ONLY between (1-6).");
                  }
               
               } while (choice != 6); 
            }
            
                // Staff program starts here //
         } else if (userType==2) {
            do {
               while(true){
                  try{
                     System.out.println("   =========  STAFF MENU  =========");
                     System.out.println("1. Add device.\n2. Remove Device.");
                     System.out.println("3. Display center information.\n4. View devices.");
                     System.out.println("5. Generate Report\n6. Save info");
                     System.out.println("7. Retrieve past info \n8. Back to the main menu.");
                     System.out.print("Please enter a choice: ");
                     choice= input.nextInt();
                     break;
                  } catch (InputMismatchException e){
                     input.nextLine();
                     System.out.println("INVALID: Enter a valid choice (integer).");
                  }
               }
               switch (choice) {
               
                  // Add device to the array
                  case 1:
                     System.out.print("Enter device type (PS, PC, or VR): "); 
                     String dName=input.next();
                     if(dName.equalsIgnoreCase("PS")) {
                        System.out.println("Please enter the name of the PlayStation's model: ");
                        input.nextLine();
                        String psName=input.nextLine();
                        Device ps=new PlayStation("PlayStation "+psName,50);
                        gameCenter.addDevice(ps); 
                        System.out.println("Device added successfully.");
                     } else if (dName.equalsIgnoreCase("PC")) {
                        System.out.println("Please enter the name of the Personal Computer's Operating System: ");
                        input.nextLine();
                        String pcName=input.nextLine();
                        Device pc=new PCStation("Personal Computer - "+pcName,80,16);
                        gameCenter.addDevice(pc);
                        System.out.println("Device added successfully.");
                     } else if (dName.equalsIgnoreCase("VR")){
                        Device vr=new VRStation("Virtual Reality",100,16);
                        gameCenter.addDevice(vr);
                        System.out.println("Device added successfully.");
                     // The code above is executed based on which device (PS, PC, VR) the user wants //
                     } else 
                        System.out.println("INVALID ENTRY: Only enter \"PC\", \"PS\", or\"VR\".");
                     break;
                     
                  // Remove device from the array
                  case 2:
                     gameCenter.displayDevices();
                     System.out.print("Enter device ID to remove it: ");
                     input.nextLine();
                     String dId = input.nextLine();
                     gameCenter.removeDevice(dId);
                     break;
                     
                  // Display all center information //
                  case 3:
                     gameCenter.displayAll();
                     break;
                     
                  // View all devices currently in the center's information //
                  case 4:
                     gameCenter.displayDevices();
                     break;
                     
                  case 5:
                     gameCenter.generateReport(customer);
                     break;
                	  
                  case 6:
                     gameCenter.writeObjectFile(customer);
                     break;
                     
                  case 7:
                     gameCenter.readObjectFile(customer);
                     break;
                  // Return to the main menu
                  case 8:
                     System.out.println("Returning to main menu...");
                     break;
                     
                  // In case the user enters an invalid choice //
                  default:
                     System.out.println("INVALID: Enter a choice ONLY between (1-5).");
               }
            
            } while (choice != 8);
         
         } else if (userType != 3) {
            System.out.println("INVALID: Enter a choice ONLY between (1-3).");
         }
           
      } while (userType != 3); // Exit
      
      System.out.println("Thank you for using our system!");
   }

}

