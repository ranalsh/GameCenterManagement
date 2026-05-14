//Device
import java.io.*;
public abstract class Device implements Serializable{
   protected String deviceID;
   protected String deviceName;
   protected double pricePerHour; // Stores device's price per hour
   protected boolean isBooked; // Stores device's current status, if it's booked or not.
   protected static int counter = 1; // Utilised in couting IDs

   // Constructor //
   public Device(String devName, double pricePerHour) {
      this.deviceName = devName;
      this.pricePerHour = pricePerHour;
      this.isBooked = false;
   }
		
   // Checks if chosen device is busy or not //
   public boolean isBooked() {
      return isBooked;
   }
	    
	// Display Method //
   public String getDeviceInfo() {
      String str;
      str="=======*** DEVICE INFO ***=======\n"+"- Device Name: "+deviceName+"\n- Device ID: "+deviceID+"\n- Price (per hour): "+pricePerHour+"\n- Booking Status: ";
      if(isBooked)
         str+="BOOKED AT THE MOMENT\n";
      else
         str+="FREE TO BOOK\n";
      return str;
   }   
	    
	// Abstract Method //
   public abstract double calculateHourlyCost();
	   
	// Getters & Setters //
   public String getDeviceID() {
      return deviceID;
   }
	   
   public String getDeviceName() {
      return deviceName;
   }
	   
   public void setBooked(boolean isB) {
      isBooked=isB;
   }
	   
   public void setPricePerHour(double p) {
      pricePerHour=p;
   }

   public double getPrice() {
      return pricePerHour;
   }


}