package edu.du.msa_project_pyl.controller;

import edu.du.msa_project_pyl.entity.Reservation;
import edu.du.msa_project_pyl.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservation() {
        List<Reservation> reservations = reservationService.selectAllReservation();
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.ok("예약이 취소되었습니다.");
    }
}
