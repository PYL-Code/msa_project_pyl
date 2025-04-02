package edu.du.msa_project_pyl.controller;

import edu.du.msa_project_pyl.entity.Users;
import edu.du.msa_project_pyl.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete")
    public void deleteUserById(@RequestParam Long id) {
        userService.deleteUserById(id);
    }
}
