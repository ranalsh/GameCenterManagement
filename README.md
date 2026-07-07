# CSC113 Game Center Management System

## Overview

A Java-based Game Center Management System. The project combines a console application with a Java Swing GUI, allowing customers to book gaming sessions and staff to manage the gaming center.

## Features

### Customer
- Create a customer profile
- Load and recharge account balance
- View customer information
- Book and end gaming sessions
- Generate session receipts
- View membership information

### Staff
- Add and remove devices through the GUI
- View available devices
- Access the console for additional features such as displaying center information, generating reports, and saving/retrieving data

## Technologies Used
- Java
- Java Swing
- Object-Oriented Programming (OOP)
- Exception Handling
- File Serialization

## Project Structure

```
main.java
InputFrame.java
ResultFrame.java

GameCenter.java
Customer.java
Session.java
Membership.java

Device.java
DeviceNode.java
PlayStation.java
PCStation.java
VRStation.java

Payable.java
NoBalanceException.java
```

## How to Run

1. Compile all Java files.
2. Run `main.java`.
3. The application will launch with the console and then the graphical menu.

## OOP Concepts Demonstrated
- Encapsulation
- Inheritance
- Polymorphism
- Exception Handling
- File I/O and Serialization

## Future Improvements
- Convert the remaining console menus to GUI
- Improve the interface layout
- Add a login system
- Integrate a database for persistent storage

## Notes
Developed as a course project for **CSC113 – Java 2**, purely for educational purposes.
