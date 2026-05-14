public class VRStation extends PCStation {
	   
	// Constructor //
   public VRStation(String devName, double pricePerHour, int ramGB) {
      super(devName, pricePerHour, ramGB);
      deviceID="VR_"+(counter++);
   }
	
	// Display Method //
   public String getDeviceInfo() {
      return super.getDeviceInfo();
   }
	
	// Calculates hourly cost with an extra fee due to the expensive hardware //
   public double calculateHourlyCost() {
      return (pricePerHour * 1.5);
   }
}
