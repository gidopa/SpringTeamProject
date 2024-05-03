package travel.project.repository.customer;

import travel.project.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);


    Optional<Customer> login(String customerId, String password);

    Optional<Customer> findById(String id);

    void updateCustomer(Customer customer);
}
