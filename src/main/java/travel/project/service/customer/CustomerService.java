package travel.project.service.customer;

import travel.project.domain.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(String id);

    void update(Customer customer);
}
