import java.io.Serializable;

public class DeviceNode implements Serializable {
    //The data payload of the node holding Device object
   private Device data;
    //reference to the next node in the sequence
   private DeviceNode next;

   public DeviceNode(Device obj) {
      data = obj;
      next = null;
   }

    //Updates the reference to the next node
   public void setNext(DeviceNode next){ 
      this.next = next; }
    
   public DeviceNode getNext(){ 
      return next; }
    
    //Updates the Device data stored in this node.
   public void setData(Device obj){ 
      data = obj;  }
    
   public Device getData(){
      return data; }
}