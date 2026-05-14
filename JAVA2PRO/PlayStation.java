public class PlayStation extends Device{
	
	// Constructor //
   public PlayStation(String name, double price) {
      super(name, price);
      deviceID="PS_"+(counter++);
   }
	
   // Display Method //
   public String getDeviceInfo() {
      return super.getDeviceInfo();
   }

	// Abstract Method implementation //
   public double calculateHourlyCost() {
      return ( pricePerHour * 1.1 ); // Due to the PS plus / "online player" fee //
   }
	
}
