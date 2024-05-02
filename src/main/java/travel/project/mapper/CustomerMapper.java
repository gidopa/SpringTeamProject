package travel.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import travel.project.domain.Customer;

@Mapper
public interface CustomerMapper {

    void save(Customer customer);


    // 로그인하는 Id로
    Customer findByLoginId(String customerId);

}
