package travel.project;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import travel.project.domain.Customer;
import travel.project.repository.customer.CustomerRepository;

import static org.assertj.core.api.Assertions.*;



//@SpringBootTest
@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class TestHi {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void save(){
        Customer customer = new Customer("jdm2131", "qwer1234", "길동", "test@test.com",
                "01012341234","부산시");
        Customer savedOne = customerRepository.save(customer);
        assertThat(customer.getCustomerId()).isEqualTo(savedOne.getCustomerId());
    }
}
