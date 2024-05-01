package travel.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import travel.project.domain.Customer;
import travel.project.service.LoginServiceImpl;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final LoginServiceImpl loginService;
    HttpSession session;
    String main = "main/main";
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("center", "../member/loginForm.jsp");
        return main;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Customer customer, Model model, HttpServletRequest request){
        String id = null;
        // Optional로 받아 service 계층에서 filter 사용
        Optional<Customer> loginCustomer = loginService.login(customer.getCustomerId(), customer.getPassword());
        if (loginCustomer.isPresent()) {
            id = loginCustomer.get().getCustomerId();
            // id 사용
        } else {
            // Optional이 비어있을 경우의 처리
            loginCustomer.orElseThrow(() -> new RuntimeException("Customer not Found")).getCustomerId();
        }
        session = request.getSession();
        session.setAttribute("id", id);
        return "redirect:/";
    }
}
