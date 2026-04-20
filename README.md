# ✈️ Airline Ticket Booking System (Java Swing)

## 📌 Overview
This project is a **desktop-based Airline Ticket Booking System** built using **Java Swing**.  
It provides a simple, interactive GUI for passengers to book tickets, select seat types, and confirm their journey.  
The application also includes an **Admin view** to display all passenger bookings.

---

## 🎯 Features
- **Multi-page workflow** using `CardLayout`:
  - Welcome page with Admin/Passenger options
  - Passenger booking form
  - Seat selection page
  - Booking confirmation page
  - Farewell page
- **Passenger Form**:
  - Name, Age, Gender (dropdown: Female, Male, Other)
  - Date of Birth (calendar picker with age validation)
  - Flight Name
  - From/To (dropdowns restricted to Indian cities)
  - Travel Date (calendar picker)
  - Number of Passengers
- **Seat Selection**:
  - Business Class → ₹500 per passenger
  - Economic Class → ₹300 per passenger
  - General Class → ₹150 per passenger
- **Validation**:
  - Age must match Date of Birth
  - Passenger details must be complete before proceeding
- **Admin Panel**:
  - Displays all passenger bookings with details
- **UI Enhancements**:
  - Clean layout with `GridLayout` and `BorderLayout`
  - Scrollable booking details

---
 🛠️ Technologies Used
- **Java** (JDK 8+)
- **Swing** for GUI components
- **CardLayout** for multi-page navigation
- **JSpinner** for calendar date selection
- **JComboBox** for dropdown menus


## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/airline-ticket-booking.git
