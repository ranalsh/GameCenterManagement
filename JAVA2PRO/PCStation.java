public class PCStation extends Device{
   protected int ramGB; // Stores the Personal Computer's RAM GBytes
		
	// Constructor //
   public PCStation(String devName, double pricePerHour, int ramGB) {
      super(devName, pricePerHour);
      this.ramGB=ramGB;
      deviceID="PC_"+(counter++);
   }
		
	// Display Method //
   public String getDeviceInfo() {
      String str;
      str=super.getDeviceInfo();
      str+="- RAM GB: "+ramGB+"\n";
      return str;
   }
		
	// Abstract Method implementation
   public double calculateHourlyCost() {
      return ( pricePerHour * 1.25 ); // Stronger hardware fee, more expensive than PS
   }
	
}
