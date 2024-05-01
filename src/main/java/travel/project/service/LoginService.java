package travel.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travel.project.repository.customer.CustomerRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final CustomerRepository customerRepository;
}
