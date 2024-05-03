package travel.project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import travel.project.domain.Customer;
import travel.project.repository.customer.CustomerRepository;


//@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
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

    @Test
    void verificationId() {
    	String id = "id1";
    	String result = customerRepository.verificationId(id);
    	assertThat(result).isEqualTo("true");

    }

}
