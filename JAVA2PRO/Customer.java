//Customer
import java.io.*;
public class Customer implements Payable, Serializable{
   private String customerId;
   private String name;
   private String phone;
   private double balance; // the current amount of money the customer has loaded

   public Customer(String customerId,String name,String phone,double balance){
      this.customerId= customerId;
      this.name= name;
      this.phone=phone;
      this.balance=balance;
   }

	// return the current balance of the customer
   public double calculateCost(){
      return balance;
   }

	// return a summary of the customer account
   public String getCustomerInfo(){
      return "Customer Info - ID: "+customerId+", Name: "+name+ ", Phone: "+phone +", Balance: "+balance;
   }

   public String getCustomerId(){
      return customerId;
   }

   public String getName(){
      return name;
   }

   public String getPhone(){
      return phone;
   }

   public double getBalance(){
      return balance;
   }

   public void setBalance(double balance){
      this.balance= balance;
   }

   public void recharge(double amount){
      balance =balance +amount;
   }



}