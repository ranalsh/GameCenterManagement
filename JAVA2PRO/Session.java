//Session
import java.io.*;
public class Session implements Serializable{
    // Attributes
   private String sessionID;
   private String time;
   private int durationHours;
   private Customer customer;
   private Device device;
   private double startTime;
   private double endTime;
   private static int count=1;
   /////// added this
   private boolean isTimeSet=false;
     
    // Parameterized constructor
   public Session(String t, int dh, Customer c, Device d) throws NoBalanceException {
      time = t;
      customer = c;
      device = d;
      durationHours = dh;
      
      if(customer.getBalance() < calculateCost()){
         throw new NoBalanceException("You don't have enough balance!");
      }
   
      timeHandler();
      if (!isTimeSet) 
         return;
   
      sessionID = "" + count++;
   }
   
    // Copy constructor
   public Session(Session s){
      this.time = s.time;
      this.customer = s.customer;
      this.device = s.device;
      this.durationHours = s.durationHours;
      this.sessionID=s.sessionID;
   }    
   
   public void timeHandler() {
      double startHour = 0;
      double startMin = 0;
   
    // basic format check
      if (time.length() < 6 || time.charAt(2) != ':') {
         return;
      }
   
    // check digits
      for (int i = 0; i < 5; i++) {
         if (i == 2) 
            continue;
         if (!Character.isDigit(time.charAt(i))) {
            System.out.println("TIP: Use digits only in time!");
            return;
         }
      }
   
    // fix missing space (e.g. 03:30pm)
      if (time.indexOf(' ') == -1) {
         if (time.toLowerCase().charAt(5)=='p')
            time = time.substring(0, 5) + " pm";
         else if (time.toLowerCase().charAt(5)=='a')
            time = time.substring(0, 5) + " am";
         else {
            System.out.println("TIP: Must include am/pm.");
            return;
         }
      }
   
    // get hour
      int hour = Integer.parseInt(time.substring(0, 2));
      String period = time.substring(time.indexOf(' ') + 1).toLowerCase();
   
    // FIX AM/PM conversion
      if (period.equals("pm") && hour != 12)
         hour += 12;
      else if (period.equals("am") && hour == 12)
         hour = 0;
   
      startHour = hour;
   
    // minutes
      int minutes = Integer.parseInt(time.substring(3, 5));
      if (minutes == 30)
         startMin = 0.5;
      else if (minutes == 0)
         startMin = 0;
      else {
         System.out.println("TIP: Minutes must be either 00 or 30.");
         return;
      }
   
      startTime = startHour + startMin;
      endTime = startTime + durationHours;
   
      isTimeSet = true;
   }    
   
   // Method that calculates the cost of one session
   // where it's the duration hours multiplied by 100, so if the session is 2 hours then the cost will be 200 riyals
   public double calculateCost(){
      double cost = 0;
      cost = (durationHours * 100) + device.calculateHourlyCost();
      return cost;
   }
    
   // Method that displays session information in an organised form
   public String generateSessionReceipt(){
      String receipt = "Session ID: #" + sessionID + "\n" + "Session started at: " + time + "\n" + "Session's Duration: " + durationHours + "\n" + "Session's Device: " + getDeviceName()+" / "+device.getDeviceID();
      return receipt;
   }
   public String getSessionInfo() {
      return generateSessionReceipt();
   }
   // Getters & Setters
   public String getSessionID() {
      return sessionID;
   }
    
   public Customer getCustomer() {
      return customer;
   }
    
   public Device getDevice() {
      return device;
   }
   public String getDeviceName(){
      return device.getDeviceName();
   }
   public int getDuration(){
      return durationHours;
   }
   public String getTime(){
      return time;
   }
   public boolean getIsTimeSet(){
      return isTimeSet;
   }
}