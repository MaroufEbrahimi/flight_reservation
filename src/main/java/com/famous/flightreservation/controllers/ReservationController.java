package com.famous.flightreservation.controllers;

import com.famous.flightreservation.dto.ReservationRequest;
import com.famous.flightreservation.entities.Flight;
import com.famous.flightreservation.entities.Reservation;
import com.famous.flightreservation.repos.FlightRepository;
import com.famous.flightreservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);


    @Autowired
    ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        LOGGER.info("showCompleteReservation() Invoked with the Flight Id: " + flightId);
        Flight flight = flightRepository.findById(flightId).orElse(null);
        modelMap.addAttribute("flight", flight);
        LOGGER.info("Flight is: " + flight);
        return "completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request, ModelMap modelMap) {
        // Create reservation and handle errors
        LOGGER.info("completeReservation(): " + request);
        try {
            Reservation reservation = reservationService.bookFlight(request);
            modelMap.addAttribute("msg", "Reservation Created Successfully and the id is: " + reservation.getId());
            return "reservationConfirmation";
        } catch (Exception e) {
            modelMap.addAttribute("error", "Failed to complete reservation: " + e.getMessage());
            return "error"; // Provide an appropriate error view
        }
    }
}
