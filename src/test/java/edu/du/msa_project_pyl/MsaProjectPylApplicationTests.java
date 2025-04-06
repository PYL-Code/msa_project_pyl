package edu.du.msa_project_pyl;

import edu.du.msa_project_pyl.entity.Users;
import edu.du.msa_project_pyl.repository.ReservationCheckRepository;
import edu.du.msa_project_pyl.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MsaProjectPylApplicationTests {

    @Autowired
    ReservationCheckRepository reservationCheckRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void reservationFindAll() {
        System.out.println(reservationCheckRepository.findAll());
    }

    @Test
    void userFindById() {
        userRepository.findById(1L);
    }

    @Test
    void userUpdate() {
        Users user = new Users();
        user.setId(1L);
        user.setUserId("user1");
        user.setPassword("password");
        user.setName("user1");
        user.setEmail("test@email.com");
        user.setPhone("123456789");
        user.setAddress("address");
        user.setPostcode("postcode");
        userRepository.updateUser(user);
    }
}
