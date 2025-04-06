package edu.du.msa_project_pyl.repository;

import edu.du.msa_project_pyl.entity.Reservation;
import edu.du.msa_project_pyl.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationCheckRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAll();

    void deleteById(Long id);
}
