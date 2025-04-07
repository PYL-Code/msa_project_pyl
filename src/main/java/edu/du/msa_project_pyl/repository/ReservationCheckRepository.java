package edu.du.msa_project_pyl.repository;

import edu.du.msa_project_pyl.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationCheckRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserNo(Long userNo);

    void deleteById(Long id);
}
