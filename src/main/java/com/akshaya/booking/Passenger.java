package com.akshaya.booking;

public class Passenger {
    private String name;
    private int age;
    private String gender;
    private String flightName;
    private String from;
    private String to;
    private String travelDate;
    private int numberOfPassengers;
    private String seatType;
    private int cost;

    public Passenger(String name, int age, String gender, String flightName,
                     String from, String to, String travelDate, int numberOfPassengers) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.flightName = flightName;
        this.from = from;
        this.to = to;
        this.travelDate = travelDate;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setSeatType(String seatType) { this.seatType = seatType; }
    public void setCost(int cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nFlight: " + flightName +
                "\nFrom: " + from +
                "\nTo: " + to +
                "\nTravel Date: " + travelDate +
                "\nPassengers: " + numberOfPassengers +
                "\nSeat Type: " + seatType +
                "\nCost: ₹" + cost;
    }
}
