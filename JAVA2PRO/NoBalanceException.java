public class NoBalanceException extends Exception{

   public NoBalanceException(){
      super();
   }

   public NoBalanceException(String message){
      super("Invalid: "+message);
   }
}