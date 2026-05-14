//GameCenter
import java.io.*;
public class GameCenter{
   // Attributes
   private String centerName;
   public static Session[] sessions;
   public static int sCount;
   public static DeviceNode deviceHead; //LinkedList Instead of Array[]
   public static int dCount;
  
  //Paramiterize constructor
   public GameCenter(String cn, int sSize){
      centerName = cn;
      sessions = new Session[sSize];
      deviceHead = null;//DO NOT need size 
      sCount  = 0;
      dCount = 0;
   }
  
   // Method that adds a session to the session array
   public void addSession(Session s) throws NoBalanceException{
      if(sCount<0)
         sCount=0;
      //if the number of sessions is the same as the size of the array then there is no space left
      if (sCount == sessions.length)
         System.out.println("ERROR: All devices are booked");
      else{
         sessions[sCount++] = new Session(s);
      }
   }
  
  // Method that removes a session from the array
   public void removeSession(String id){
      if(sCount<0) {
         System.out.println("INVALID: No sessions available for removal.");
         sCount=0;
      }
      boolean found=false;
      // Loop that searches through the entire array for a session whose id matches
      for (int i = 0; i < sCount; i++){
          // If we find a matching id, then we move the last session in the array to that place and make the last place null, for better arrangement
         if (sessions[i].getSessionID().equalsIgnoreCase(id)){
            sessions[i].getDevice().setBooked(false);
            sessions[i] = sessions[sCount -1];
            sessions[sCount -1] = null;
            sCount--;
            found=true;            
         }
      }  
      if(found==false) System.out.println("ERROR: Cannot remove session. Session ID not found");
   }

     
   public Session searchSession(String id){
      // Loop that searches through the entire array and returns a session with a matching id
      for (int i = 0; i < sCount; i++)
         if (sessions[i].getSessionID().equalsIgnoreCase(id))
            return sessions[i];
      System.out.println("ERROR: Session not found.");
      return null;
   }
   
	// Recursion Method to find a specific session from the index
   public Session findSession(String id, int idx) {
   
   // base (index out of bounds)
      if (idx >= sCount)
         return null;
   // check current session
      if (sessions[idx].getSessionID().equalsIgnoreCase(id))
         return sessions[idx];
   // recursive call
      return findSession(id, idx + 1);
   }   
   
   //count the total number of nodes(devices)
   public int countDevices() {
      int count = 0;
      DeviceNode current = deviceHead;
      while (current != null) {
         count++;
         current = current.getNext();
      }
      return count;
   }
   
   //Adds new node to the beginning of the linkeList 
   public void addDevice(Device d) {
      DeviceNode n = new DeviceNode(d);
      n.setNext(deviceHead);
      deviceHead = n;
      dCount++;
   }

    //removes node from the linkedList
   public void removeDevice(String id) {
      if (deviceHead == null) {
         System.out.println("ERROR: No devices available to remove");
         return;
      }
        
        //If the first is required
      if (deviceHead.getData().getDeviceID().equalsIgnoreCase(id)) {
         if (deviceHead.getData().isBooked()) {
            System.out.println("ERROR: Device is currently booked and cannot be removed");
            return;
         }
         deviceHead = deviceHead.getNext();
         dCount--;
         System.out.println("Device removed successfully");
         return;
      }
        
        // prev/current nodes in the loop only
      DeviceNode prev= deviceHead;
      DeviceNode current = deviceHead.getNext();
      while (current != null) {
         if (current.getData().getDeviceID().equalsIgnoreCase(id)) {
            if (current.getData().isBooked()) {
               System.out.println("ERROR: Device is currently booked and cannot be removed");
               return;
            }
            prev.setNext(current.getNext());
            dCount--;
            System.out.println("Device removed successfully");
            return;
         }
         prev = prev.getNext();
         current = current.getNext();
      }
      System.out.println("ERROR: Device ID not found");
   }

   public Device searchDevice(String id) {
      DeviceNode current = deviceHead;
      while (current != null) {
         Device d = current.getData();
         if (d.getDeviceID().substring(0, 2).equalsIgnoreCase(id) && !d.isBooked())
            return d;
         current = current.getNext();
      }
      System.out.println("ERROR: Device not found or is booked");
      return null;
   }
    
   
   public Device findDevice(String id, DeviceNode current) {
      if (current == null)
         return null;
      if (current.getData().getDeviceID().equalsIgnoreCase(id))
         return current.getData();
      return findDevice(id, current.getNext());
   }
    
       
  // Display Method, displays all information in an arranged form
   public void displayAll() {
      System.out.println("Game Center Name: " + centerName);
      System.out.println("Number of Active Sessions: " + sCount);
      if (sCount>0 && sessions[0] !=null) {
         System.out.println("+------List of Active Sessions------+");
            
         for (int i = 0; i < sCount; i++) {
            System.out.println("Session " + sessions[i].getSessionID() + " :");
            System.out.println("- Start time: "+ sessions[i].getTime());
            System.out.println("- Duration: "+ sessions[i].getDuration() + "hr.");
            System.out.println("- For Customer: "+ sessions[i].generateSessionReceipt());
            System.out.println("- Device: "+ sessions[i].getDevice().getDeviceName());
            System.out.println("+-----------------------------------+");
         }
      }
      System.out.println("Number of Devices: " + dCount);
      System.out.println("+----------List of Devices----------+");
      DeviceNode current = deviceHead;// 
      while (current != null) {
         System.out.println("Device "+ current.getData().getDeviceID() + " :");
         System.out.println("- Device name: "+ current.getData().getDeviceName());
         if (current.getData().isBooked())
            System.out.println("- Booking State: booked");
         else
            System.out.println("- Booking State: not booked");
         System.out.println("+-----------------------------------+");
         current = current.getNext();
      }
   }
  
  
   // Display devices only
   public void displayDevices() {
      DeviceNode current = deviceHead;
      while (current != null) {
         System.out.println(current.getData().getDeviceInfo());
         current = current.getNext();
      }
   }

   //Makes a file that saves current data
   public void writeObjectFile(Customer c) {
      try {
         File fileOfObjects1 =new File("Objects_File.dat");
         ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(fileOfObjects1));
         oos.writeObject(c);
         oos.writeObject(deviceHead); 
         oos.writeInt(sCount);
         for (int i = 0; i < sCount; i++)
            oos.writeObject(sessions[i]);
         oos.close();
         System.out.println("Data Saved successfully");
      } catch(NullPointerException e1){
         System.out.println("Error no customer or session created");
      }catch (IOException e) {
         System.out.println("Error cannot write in file");
      }
   }
    
    
//Reads the file that has the last saved data in
   public void readObjectFile(Customer c) {
      try {
         try {
            File fileOfObjects2 =new File("Objects_File.dat");
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(fileOfObjects2));
            c= (Customer)ois.readObject();
            deviceHead =(DeviceNode)ois.readObject(); 
            sCount= ois.readInt();
            for (int i =0; i < sCount; i++)
               sessions[i] =(Session) ois.readObject();
            ois.close();
            System.out.println("Data loaded successfully");
         } catch (ClassNotFoundException e) {}
      }catch (NullPointerException e1){
         System.out.println("Error customer or session(s) not created"); 
      }catch (IOException e) {
         System.out.println("Error cannot read file");
      }
   }
    
//Generates a text file named Report that shows current Center status
   public void generateReport(Customer c) {
      try{
         
         File report = new File("Report.txt");
         PrintWriter pw = new PrintWriter(new FileOutputStream(report));
         pw.println("+-----------------------------------+");                                                
         pw.println(c.getCustomerInfo());
         pw.println("+-----------------------------------+");
         DeviceNode current = deviceHead;
         while (current != null) {
            pw.println(current.getData().getDeviceInfo());
            pw.println("+-----------------------------------+");
            current = current.getNext();
         }
         for (int i = 0; i < sCount; i++) {
            pw.println(sessions[i].getSessionInfo());
            pw.println("+-----------------------------------+");
         }
         pw.close();
         System.out.println("Report generated successfully");
      } catch (NullPointerException e1){
         System.out.println("Error no customer or session(s) created");
      }catch (IOException e) {
         System.out.println("Error cannot print report");
      }  
   }
}


