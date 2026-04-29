package com.akshaya.booking;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AirlineTicketBooking extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private List<Passenger> passengers;
    private Passenger currentPassenger;

    // Form fields
    private JTextField nameField, ageField, flightNameField, numberOfPassengersField;
    private JComboBox<String> genderComboBox, fromFieldCombo, toFieldCombo;
    private JSpinner dobSpinner, travelDateSpinner;

    public AirlineTicketBooking() {
        setTitle("Airline Ticket Booking");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        passengers = new ArrayList<>();

        addPage1();
        addPage2();
        addPage3();
        addPage4();
        addPage5();

        add(cardPanel);
        setVisible(true);
    }

    private void addPage1() {
        JPanel page1 = new JPanel();
        JLabel companyNameLabel = new JLabel("FlyMe Company", SwingConstants.CENTER);
        companyNameLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JButton adminButton = new JButton("Admin");
        JButton passengerButton = new JButton("Passenger");

        adminButton.addActionListener(e -> showAdminDetails());
        passengerButton.addActionListener(e -> {
            clearForm();
            cardLayout.show(cardPanel, "Page2");
        });

        page1.setLayout(new GridLayout(4, 1));
        page1.add(companyNameLabel);
        page1.add(new JLabel());
        page1.add(adminButton);
        page1.add(passengerButton);

        cardPanel.add(page1, "Page1");
    }

    private void addPage2() {
        JPanel page2 = new JPanel();

        nameField = new JTextField(25);
        ageField = new JTextField(10);

        // Date of Birth calendar
        dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dobEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dobEditor);

        // Gender dropdown
        genderComboBox = new JComboBox<>(new String[]{"Female", "Male", "Other"});

        flightNameField = new JTextField(20);

        // Indian cities dropdowns
        String[] indianCities = {
            "Delhi", "Mumbai", "Bengaluru", "Hyderabad", "Chennai",
            "Kolkata", "Pune", "Ahmedabad", "Jaipur", "Goa",
            "Lucknow", "Coimbatore", "Visakhapatnam", "Nagpur", "Patna"
        };
        fromFieldCombo = new JComboBox<>(indianCities);
        toFieldCombo = new JComboBox<>(indianCities);

        // Travel Date calendar
        travelDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor travelDateEditor = new JSpinner.DateEditor(travelDateSpinner, "yyyy-MM-dd");
        travelDateSpinner.setEditor(travelDateEditor);

        numberOfPassengersField = new JTextField(10);

        JButton nextButton = new JButton("Next");
        JButton cancelButton = new JButton("Cancel");

        nextButton.addActionListener(e -> {
            try {
                int enteredAge = Integer.parseInt(ageField.getText());
                Date dobDate = (Date) dobSpinner.getValue();
                int calculatedAge = calculateAge(dobDate);

                if (enteredAge != calculatedAge) {
                    JOptionPane.showMessageDialog(this,
                            "Age does not match Date of Birth. Please correct it.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int numberOfPassengers = Integer.parseInt(numberOfPassengersField.getText());
                currentPassenger = new Passenger(
                        nameField.getText(),
                        String.valueOf(enteredAge),
                        dobEditor.getFormat().format(dobDate),
                        (String) genderComboBox.getSelectedItem(),
                        flightNameField.getText(),
                        (String) fromFieldCombo.getSelectedItem(),
                        (String) toFieldCombo.getSelectedItem(),
                        travelDateEditor.getFormat().format((Date) travelDateSpinner.getValue()),
                        numberOfPassengers
                );
                cardLayout.show(cardPanel, "Page3");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid numbers for Age and Passengers.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            currentPassenger = null;
            clearForm();
            cardLayout.show(cardPanel, "Page1");
        });

        page2.setLayout(new GridLayout(11, 2));
        page2.add(new JLabel("Name:")); page2.add(nameField);
        page2.add(new JLabel("Age:")); page2.add(ageField);
        page2.add(new JLabel("Date of Birth:")); page2.add(dobSpinner);
        page2.add(new JLabel("Gender:")); page2.add(genderComboBox);
        page2.add(new JLabel("Flight Name:")); page2.add(flightNameField);
        page2.add(new JLabel("From:")); page2.add(fromFieldCombo);
        page2.add(new JLabel("To:")); page2.add(toFieldCombo);
        page2.add(new JLabel("Travel Date:")); page2.add(travelDateSpinner);
        page2.add(new JLabel("Number of Passengers:")); page2.add(numberOfPassengersField);
        page2.add(nextButton); page2.add(cancelButton);

        cardPanel.add(page2, "Page2");
    }

    private void addPage3() {
        JPanel page3 = new JPanel();
        ButtonGroup seatGroup = new ButtonGroup();
        JRadioButton businessClass = new JRadioButton("Business Class -> ₹500");
        JRadioButton economicClass = new JRadioButton("Economic Class -> ₹300");
        JRadioButton generalClass = new JRadioButton("General Class -> ₹150");

        JButton continueButton = new JButton("Continue");
        JButton cancelButton = new JButton("Cancel");

        seatGroup.add(businessClass);
        seatGroup.add(economicClass);
        seatGroup.add(generalClass);

        continueButton.addActionListener(e -> {
            if (businessClass.isSelected()) {
                currentPassenger.setSeatType("Business Class");
                currentPassenger.setCost(currentPassenger.getNumberOfPassengers() * 500);
            } else if (economicClass.isSelected()) {
                currentPassenger.setSeatType("Economic Class");
                currentPassenger.setCost(currentPassenger.getNumberOfPassengers() * 300);
            } else if (generalClass.isSelected()) {
                currentPassenger.setSeatType("General Class");
                currentPassenger.setCost(currentPassenger.getNumberOfPassengers() * 150);
            }
            cardLayout.show(cardPanel, "Page4");
        });

        cancelButton.addActionListener(e -> {
            currentPassenger = null;
            clearForm();
            cardLayout.show(cardPanel, "Page1");
        });

        page3.setLayout(new GridLayout(5, 1));
        page3.add(businessClass);
        page3.add(economicClass);
        page3.add(generalClass);
        page3.add(continueButton);
        page3.add(cancelButton);

        cardPanel.add(page3, "Page3");
    }

    private void addPage4() {
        JPanel page4 = new JPanel(new BorderLayout());
        JLabel confirmLabel = new JLabel("Please confirm your booking details:", SwingConstants.CENTER);

        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);

        JButton confirmButton = new JButton("Confirm Booking");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(e -> {
            passengers.add(currentPassenger);
            cardLayout.show(cardPanel, "Page5");
        });

        cancelButton.addActionListener(e -> {
            currentPassenger = null;
            clearForm();
            cardLayout.show(cardPanel, "Page1");
        });

        page4.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                if (currentPassenger != null) {
                    detailsTextArea.setText(currentPassenger.toString());
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        page4.add(confirmLabel, BorderLayout.NORTH);
        page4.add(scrollPane, BorderLayout.CENTER);
        page4.add(buttonPanel, BorderLayout.SOUTH);

        cardPanel.add(page4, "Page4");
    }

    private void addPage5() {
        JPanel page5 = new JPanel(new BorderLayout());

        JLabel farewellLabel = new JLabel("Happy Journey! Visit again.", SwingConstants.CENTER);
        JButton logoutButton = new JButton("Log Out");

        logoutButton.addActionListener(e -> {
            currentPassenger = null;
            clearForm();
            cardLayout.show(cardPanel, "Page1");
        });

        page5.add(farewellLabel, BorderLayout.CENTER);
        page5.add(logoutButton, BorderLayout.SOUTH);

        cardPanel.add(page5, "Page5");
    }

        private void showAdminDetails() {
        StringBuilder adminDetails = new StringBuilder("Passenger details:\n");
        if (passengers.isEmpty()) {
            adminDetails.append("No bookings yet.");
        } else {
            for (Passenger passenger : passengers) {
                adminDetails.append(passenger.toString()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(this, adminDetails.toString(),
                "Admin Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        if (nameField != null) nameField.setText("");
        if (ageField != null) ageField.setText("");
        if (dobSpinner != null) dobSpinner.setValue(new Date());
        if (genderComboBox != null) genderComboBox.setSelectedIndex(0);
        if (flightNameField != null) flightNameField.setText("");
        if (fromFieldCombo != null) fromFieldCombo.setSelectedIndex(0);
        if (toFieldCombo != null) toFieldCombo.setSelectedIndex(0);
        if (travelDateSpinner != null) travelDateSpinner.setValue(new Date());
        if (numberOfPassengersField != null) numberOfPassengersField.setText("");
    }

    private int calculateAge(Date dob) {
        java.util.Calendar dobCal = java.util.Calendar.getInstance();
        dobCal.setTime(dob);

        java.util.Calendar today = java.util.Calendar.getInstance();

        int age = today.get(java.util.Calendar.YEAR) - dobCal.get(java.util.Calendar.YEAR);

        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dobCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    private class Passenger {
        private String name, age, dob, gender, flightName, from, to, date, seatType;
        private int numberOfPassengers, cost;

        public Passenger(String name, String age, String dob, String gender,
                         String flightName, String from, String to, String date, int numberOfPassengers) {
            this.name = name;
            this.age = age;
            this.dob = dob;
            this.gender = gender;
            this.flightName = flightName;
            this.from = from;
            this.to = to;
            this.date = date;
            this.numberOfPassengers = numberOfPassengers;
        }

        public void setSeatType(String seatType) { this.seatType = seatType; }
        public void setCost(int cost) { this.cost = cost; }
        public int getNumberOfPassengers() { return numberOfPassengers; }

        @Override
        public String toString() {
            return "Name: " + name +
                    "\nAge: " + age +
                    "\nDOB: " + dob +
                    "\nGender: " + gender +
                    "\nFlight Name: " + flightName +
                    "\nFrom: " + from +
                    "\nTo: " + to +
                    "\nTravel Date: " + date +
                    "\nPassengers: " + numberOfPassengers +
                    "\nSeat Type: " + seatType +
                    "\nCost: ₹" + cost;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirlineTicketBooking::new);
    }
}
