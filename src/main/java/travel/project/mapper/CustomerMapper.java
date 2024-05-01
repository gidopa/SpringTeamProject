package travel.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import travel.project.domain.Customer;

@Mapper
public interface CustomerMapper {

    void save(Customer customer);


    Customer findByLoginId(String customerId, String password);

}
