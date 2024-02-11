package com.famous.flightreservation.services;

import com.famous.flightreservation.controllers.ReservationController;
import com.famous.flightreservation.dto.ReservationRequest;
import com.famous.flightreservation.entities.Flight;
import com.famous.flightreservation.entities.Passenger;
import com.famous.flightreservation.entities.Reservation;
import com.famous.flightreservation.repos.FlightRepository;
import com.famous.flightreservation.repos.PassengerRepository;
import com.famous.flightreservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    FlightRepository flightRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);


    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        LOGGER.info("Inside bookFlight()");
        // make Payment
        Long flightId = request.getFlightId();
        LOGGER.info("Fetching flight for flight ID: " + flightId);
        Flight flight = flightRepository.findById(flightId).orElse(null);

        // Passenger
        var passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());
        LOGGER.info("Saving the passenger: " + passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);

        // Reservation
        var reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        LOGGER.info("Saving the reservation: " + reservation);
        Reservation savedReservation = reservationRepository.save(reservation);

        return savedReservation;
    }
}
