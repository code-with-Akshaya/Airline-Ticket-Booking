package com.akshaya.booking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {

    private final List<Passenger> passengers = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Airline Ticket Booking!");
        return "index";
    }

    @GetMapping("/book")
    public String showBookingForm() {
        return "book";
    }

    @PostMapping("/book")
    public String processBooking(@RequestParam String name,
                                 @RequestParam int age,
                                 @RequestParam String gender,
                                 @RequestParam String flightName,
                                 @RequestParam String from,
                                 @RequestParam String to,
                                 @RequestParam String travelDate,
                                 @RequestParam int numberOfPassengers,
                                 @RequestParam String seatType,
                                 Model model) {

        Passenger p = new Passenger(name, age, gender, flightName, from, to, travelDate, numberOfPassengers);

        int price = switch (seatType) {
            case "Business" -> 500;
            case "Economic" -> 300;
            default -> 150;
        };

        p.setSeatType(seatType);
        p.setCost(numberOfPassengers * price);
        passengers.add(p);

        model.addAttribute("confirmation", p.toString());
        return "confirmation";
    }
}
