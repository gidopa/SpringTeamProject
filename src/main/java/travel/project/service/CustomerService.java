package travel.project.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import travel.project.domain.Customer;
import travel.project.repository.customer.CustomerRepositoryImpl;


@RequiredArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepositoryImpl customerRepositoryImpl;

	//회원가입시 아이디 중복검사
	public String verificationId(String customerId) {
		return customerRepositoryImpl.verificationId(customerId);
	}

	//회원가입시 이메일 중복검사
	public String verificationEmail(String email) {
		return  customerRepositoryImpl.verificationEmail(email);
	}

	//회원가입시 전화번호 중복검사
	public String verificationPhoneNumber(int phoneNumber) {
		return customerRepositoryImpl.verificationPhoneNumber(phoneNumber);
	}

	//회원가입 요청
	public void membershipJoin(Customer customer) {
		customerRepositoryImpl.save(customer);
	}






}
