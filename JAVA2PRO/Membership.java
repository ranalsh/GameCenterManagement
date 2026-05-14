public class Membership implements Payable{
   private String membershipType;//Bronze, Silver, Gold
   private double discountRate;


   public Membership(String membershipType) {
      this.membershipType = membershipType;
   
      if (membershipType.equalsIgnoreCase("Gold"))
         discountRate = 0.20;
      else if (membershipType.equalsIgnoreCase("Silver"))
         discountRate = 0.10;
      else
         discountRate = 0.05; // Bronze
   }
	//return the subscription cost based on mempership type
   public double calculateCost(){
      if(membershipType.equalsIgnoreCase("Gold"))
         return 500;
      else if(membershipType.equalsIgnoreCase("Silver"))
         return 300;
      else
         return 100;// Bronze
   }

	// display membership info and discount rate
   public String generateReceipt(){
      return "Membership Type:" +membershipType +", Cost: " + calculateCost()+ ", Discount:" + (discountRate * 100)+ "%";
   }


   public String getMembershipType(){
      return membershipType;
   }

   public double getDiscountRate(){
      return discountRate;
   }

   public String toString() {
      return "Membership Type: " + membershipType + ", Discount: " + (discountRate * 100) + "%";
   }

   public static void displayMembershipInfo() {
      Membership bronze= new Membership("Bronze");
      Membership silver= new Membership("Silver");
      Membership gold= new Membership("Gold");
   
      System.out.println(bronze.toString());
      System.out.println(silver.toString());
      System.out.println(gold.toString());
   }
}