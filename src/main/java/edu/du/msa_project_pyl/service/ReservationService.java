package edu.du.msa_project_pyl.service;

import edu.du.msa_project_pyl.entity.Reservation;
import edu.du.msa_project_pyl.repository.ReservationCheckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationCheckRepository reservationCheckRepository;

    public ReservationService(ReservationCheckRepository reservationCheckRepository) {
        this.reservationCheckRepository = reservationCheckRepository;
    }

    public List<Reservation> selectAllReservation () {
        return reservationCheckRepository.findAll();
    }

    public void deleteReservationById(Long id) {
        reservationCheckRepository.deleteById(id);
    }

}
