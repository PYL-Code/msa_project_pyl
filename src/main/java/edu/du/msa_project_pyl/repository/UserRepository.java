package edu.du.msa_project_pyl.repository;

import edu.du.msa_project_pyl.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);

    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.userId = :#{#user.userId}, " +
            "u.name = :#{#user.name}, " +
            "u.birthday = :#{#user.birthday}, " +
            "u.phone = :#{#user.phone}, " +
            "u.email = :#{#user.email} " +
            "WHERE u.id = :#{#user.id}")
    void updateUser(Users user);
}
