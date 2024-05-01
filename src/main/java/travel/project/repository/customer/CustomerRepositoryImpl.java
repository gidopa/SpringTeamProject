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

    // 회원 한명 insert 하고 다시 리턴
    @Override
    public Customer save(Customer customer) {
        customerMapper.save(customer);
        return customer;
    }
    // 로그인하는 id, pwd 받고 Optional로 리턴
    @Override
    public Optional<Customer> login(String customerId, String password) {
        return Optional.ofNullable(customerMapper.findByLoginId(customerId));
    }
}
