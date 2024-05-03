package travel.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CsrfPreventionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import travel.project.domain.Customer;
import travel.project.service.LoginService;
import travel.project.service.LoginServiceImpl;
import travel.project.service.customer.CustomerService;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;
    private final CustomerService customerService;
    HttpServletRequest request;
    HttpSession session;
    String main = "main/main";


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("center", "../member/loginForm.jsp");
        return main;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Customer customer, Model model, HttpServletRequest request) {
        String id = null;
        // Optional로 받아 service 계층에서 filter 사용
        Optional<Customer> loginCustomer = loginService.login(customer.getCustomerId(), customer.getPassword());
        // Optional이 있으면
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

    @GetMapping("/login/naver")
    public String naverLogin(@RequestParam String code, Model model, HttpServletRequest request) {
        session = request.getSession();
        Customer customer = loginService.naverLogin(code, session);
        // 리턴받은 Customer가 != null ->  Model에 담아 넘길 DTO가 반환 
        if(customer != null){
            model.addAttribute("customer", customer);
            return "member/naverJoin";
        }else{
            return "redirect:/";
        }
        }

    @PostMapping("/login/naver/join")
    public String naverJoin(@ModelAttribute Customer customer, @RequestParam String address1, @RequestParam String address2,
                            @RequestParam String address3, @RequestParam String address4, @RequestParam String address5, HttpServletRequest request){
        // join 폼에서 입력받은 값 바인딩 및 DB저장
        String address = address1 + address2 + address3 + address4 + address5;
        customer.setAddress(address);
        loginService.naverJoin(customer);
        session = request.getSession();
        session.setAttribute("id",customer.getCustomerId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/editForm")
    public String editForm(Model model, HttpServletRequest request){
        session = request.getSession();
        String id = (String)session.getAttribute("id");
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            log.info("확인");
            model.addAttribute("customer", customer.get());
            model.addAttribute("center", "../member/editForm.jsp");
        }
        return main;
    }

    @PutMapping("/editForm")
    public String editCustomer(@ModelAttribute Customer customer){
        // 폼에서 입력받은 customer의 정보 수정
        customerService.update(customer);
        return "redirect:/";
    }


    }



