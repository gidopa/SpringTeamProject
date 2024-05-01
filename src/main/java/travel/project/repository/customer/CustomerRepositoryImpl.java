package travel.project.repository.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import travel.project.domain.Customer;
import travel.project.mapper.CustomerMapper;
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
}
