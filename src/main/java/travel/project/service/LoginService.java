package travel.project.service;


import travel.project.domain.Customer;

import java.util.Optional;

public interface LoginService {

    Optional<Customer> login(String customerId, String password);
}
