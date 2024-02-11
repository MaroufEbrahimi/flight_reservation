package com.famous.flightreservation.controllers;

import com.famous.flightreservation.dto.ReservationUpdateRequest;
import com.famous.flightreservation.entities.Reservation;
import com.famous.flightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @RequestMapping("/reservations")
    public ResponseEntity<Reservation> updateReservationRequest(@RequestBody ReservationUpdateRequest request) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(request.getId());
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setNumberOfBags(request.getNumberOfBags());
            reservation.setCheckedIn(request.getCheckedIn());
            Reservation updatedReservation = reservationRepository.save(reservation);
            return ResponseEntity.ok(updatedReservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
