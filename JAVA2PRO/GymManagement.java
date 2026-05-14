import java.sql.*;
import java.util.Scanner;

public class GymManagement {

    public static void main(String[] args) {

    Connection con = null;
    String url  = "jdbc:mariadb://localhost:3306/GymManagement";
    String user = "root";
    String pwd  = "";

    try{
        con = DriverManager.getConnection(url, user,pwd);
       }catch(SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
            return;
        }

        System.out.println("Successfully connected to database");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("\nTable MEMBER:");
            System.out.println("1) Insert new record");
            System.out.println("2) Display all the records");
            System.out.println("3) Exit");
            System.out.print("Choose an operation :) ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                String again = "Y";
                while (again.equalsIgnoreCase("Y")) {
                    System.out.println("\nMEMBER - INSERTION:");
                    System.out.print("member_id: ");
                    int member_id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("national_id: ");
                    String national_id = scanner.nextLine();

                    System.out.print("first_name: ");
                    String first_name = scanner.nextLine();

                    System.out.print("middle_name: ");
                    String middle_name = scanner.nextLine();

                    System.out.print("last_name: ");
                    String last_name = scanner.nextLine();

                    System.out.print("gender (Male/Female): ");
                    String gender = scanner.nextLine();

                    System.out.print("phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("email: ");
                    String email = scanner.nextLine();

                    System.out.print("date_of_birth (YYYY-MM-DD): ");
                    String date_of_birth = scanner.nextLine();

                    System.out.print("address: ");
                    String address = scanner.nextLine();

                    System.out.print("BrancID: ");
                    int BrancID = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Statement stmt = con.createStatement();

                        String sql = "INSERT INTO MEMBER VALUES ("
                                + member_id + ","
                                + "'" + national_id+ "',"
                                + "'" + first_name+ "',"
                                + "'" + middle_name+ "',"
                                + "'" + last_name+ "',"
                                + "'" + gender+ "',"
                                + "'" + phone+ "',"
                                + "'" + email+ "',"
                                + "'" + date_of_birth+ "',"
                                + "'" + address+ "',"
                                + BrancID
                                + ")";

                        stmt.executeUpdate(sql);
                        System.out.println("Record inserted successfully!");
                        stmt.close();

                    } catch(SQLIntegrityConstraintViolationException e){
                        System.out.println("Error: member_id or national_id already exists. " + e.getMessage());
                    }catch (SQLException e) {
                        System.out.println("Insertion failed: " + e.getMessage());
                    }

                    System.out.print("\nInsert a new record (Y/N)? ");
                    again = scanner.nextLine();
                    }

              }else if (choice == 2){

                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM MEMBER");
                    
                    System.out.println("\n--- MEMBER Records ---");
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.printf("%-10s %-15s %-12s %-12s %-12s %-8s %-12s%n",
                        "ID","National ID", "First","Middle","Last", "Gender","Phone");
                    System.out.println("-------------------------------------------------------------------------------------");

                    while (rs.next()) {
                        System.out.printf("%-10s %-15s %-12s %-12s %-12s %-8s %-12s%n",
                            rs.getInt("member_id"),
                            rs.getString("national_id"),
                            rs.getString("first_name"),
                            rs.getString("middle_name"),
                            rs.getString("last_name"),
                            rs.getString("gender"),
                            rs.getString("phone"));
                            }

                    System.out.println("-------------------------------------------------------------------------------------");
                    rs.close();
                    stmt.close();

                }catch (SQLException e){
                    System.out.println("Display failed: " +e.getMessage());
                }

            } else if(choice == 3){
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3");
            }
        }

        try {
            con.close();
        }catch(SQLException e){
         System.out.println("Error closing connection: " +e.getMessage());
        }
  }
}