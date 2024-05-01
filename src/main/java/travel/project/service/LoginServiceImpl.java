package travel.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travel.project.domain.Customer;
import travel.project.repository.customer.CustomerRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService{

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> login(String customerId, String password) {
        Optional<Customer> customer = customerRepository.login(customerId, password)
                .filter(m -> m.getPassword().equals(password));
        return customer;


    }
}

