package travel.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import travel.project.domain.Customer;

@Mapper
public interface CustomerMapper {

    void save(Customer customer);


	String verificationId(String customerId);

	String verificationEmail(String email);

	String verificationPhoneNumber(int phoneNumber);
    // 로그인하는 Id로
  Customer findById(String customerId);
  void update(Customer customer);


}
