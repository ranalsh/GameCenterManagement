import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultFrame extends JFrame implements ActionListener {
   private JTextArea displayArea;
   private JButton infoButton, rechargeButton, backButton;
   private Customer customer;

   public ResultFrame(Customer c, GameCenter gc) {
      this.customer = c;
   
      setTitle("Customer Profile: " + c.getName());
      setSize(400, 350);
      setLayout(new BorderLayout());
   
      displayArea = new JTextArea();
      displayArea.setEditable(false);
      add(new JScrollPane(displayArea), BorderLayout.CENTER);
   
      JPanel btnPanel = new JPanel();
      infoButton = new JButton("View Info");
      rechargeButton = new JButton("Recharge 50");
      backButton = new JButton("Go to Console");
   
      infoButton.addActionListener(this);
      rechargeButton.addActionListener(this);
      backButton.addActionListener(this);
   
      btnPanel.add(infoButton);
      btnPanel.add(rechargeButton);
      btnPanel.add(backButton);
      add(btnPanel, BorderLayout.SOUTH);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == infoButton) {
         displayArea.setText(customer.getCustomerInfo());
      } else if (e.getSource() == rechargeButton) {
         customer.recharge(50);
         displayArea.append("\nRecharged! New Balance: " + customer.getBalance());
      } else if (e.getSource() == backButton) {
         this.dispose();
      }
   }
}