package edu.du.msa_project_pyl.controller;

import edu.du.msa_project_pyl.entity.Users;
import edu.du.msa_project_pyl.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageViewController {

    private final UserService userService;

    public MypageViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String mypage() {
        return "/mypage/mypage";
    }

    @GetMapping("/myinfo")
    public String myinfo(HttpSession session, Model model) {
        session.setAttribute("id", 1);
        Long id = (Long) session.getAttribute("id");
        System.out.println(id);
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/mypage/myinfo";
    }
}
