import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputFrame extends JFrame implements ActionListener {
   private JTextField nameField, phoneField, balanceField;
   private JButton submitButton;
   private GameCenter gc;
   private Customer customer = null; // The object we will return to main

   public InputFrame(GameCenter gameCenter) {
      this.gc = gameCenter;
   
      setTitle("Customer Registration");
      setSize(300, 250);
      setLocationRelativeTo(null); // Center on screen
      setLayout(new GridLayout(4, 2, 10, 10));
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
   
      add(new JLabel(" Name:"));
      nameField = new JTextField();
      add(nameField);
   
      add(new JLabel(" Phone:"));
      phoneField = new JTextField();
      add(phoneField);
   
      add(new JLabel(" Initial Balance:"));
      balanceField = new JTextField();
      add(balanceField);
   
      submitButton = new JButton("Register");
      submitButton.addActionListener(this);
      add(new JLabel("")); // Empty cell
      add(submitButton);
   }

   public void actionPerformed(ActionEvent e) {
      try {
         String name = nameField.getText().trim();
         String phone = phoneField.getText().trim();
         String balStr = balanceField.getText().trim();
      
         if (name.isEmpty() || phone.isEmpty() || balStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
         }
      
         // Validate numeric inputs
         Long.parseLong(phone);
         double balance = Double.parseDouble(balStr);
      
         // Create the persistent customer object
         this.customer = new Customer("ID-" + (int)(Math.random()*1000), name, phone, balance);
         
         JOptionPane.showMessageDialog(this, "Welcome, " + name + "!");
         
         // Launch the ResultFrame here
         ResultFrame res = new ResultFrame(customer, gc);
         res.setVisible(true);
      
         this.dispose(); // Close registration and unblock main thread
      
      } catch (NumberFormatException ex) {
         JOptionPane.showMessageDialog(this, "Invalid Phone or Balance. Use digits only.", "Error", JOptionPane.ERROR_MESSAGE);
      }
   }

   public Customer getCustomer() {
      return customer;
   }
}