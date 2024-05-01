package travel.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import travel.project.service.LoginService;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final LoginService loginService;

    String main = "main/main";
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("center", "../member/loginForm.jsp");
        return main;
    }
}
