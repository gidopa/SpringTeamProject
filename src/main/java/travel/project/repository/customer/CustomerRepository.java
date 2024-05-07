package travel.project.repository.customer;

import travel.project.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {

	//회원가입 요청
    Customer save(Customer customer);

    //회원가입시 아이디 중복검사
    String verificationId(String customerId);
    //회원가입시 이메일 중복검사
    String verificationEmail(String email);

    //회원가입시 핸드폰번호 중복검사
    String verificationPhoneNumber(int phoneNumber);




    Optional<Customer> login(String customerId, String password);

    Optional<Customer> findById(String id);

    void updateCustomer(Customer customer);

}
