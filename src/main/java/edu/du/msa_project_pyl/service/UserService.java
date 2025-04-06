package edu.du.msa_project_pyl.service;

import edu.du.msa_project_pyl.entity.Users;
import edu.du.msa_project_pyl.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void updateUser(Users user) {
        userRepository.updateUser(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
