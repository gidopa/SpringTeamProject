package travel.project.repository.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import travel.project.domain.Customer;
import travel.project.mapper.CustomerMapper;

import java.util.Optional;

// 뭐 시발
@Slf4j
@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository{

    private final CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {
        customerMapper.save(customer);
        return customer;
    }

    @Override
    public Optional<Customer> login(String customerId, String password) {
        Optional<Customer> a = Optional.ofNullable(customerMapper.findByLoginId(customerId, password));
        return a;
    }
}
