package com.famous.flightreservation.services;

import com.famous.flightreservation.dto.ReservationRequest;
import com.famous.flightreservation.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request);
}
