package customer.service;
import customer.dto.CustomerVerificationDto;
import customer.entity.CustomerVerificationEntity;
import customer.repository.CustomerVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerVerificationService {

    @Autowired
    CustomerVerificationRepository customerVerificationRepository;

    public CustomerVerificationDto saveData(CustomerVerificationDto customerVerificationDto){
        CustomerVerificationEntity c1=new CustomerVerificationEntity(1,"customer","customer123","admin");
        customerVerificationRepository.save(c1);
        return new CustomerVerificationDto(c1.getCustomerVerificationId(),c1.getCustomerUserName(),c1.getCustomerPassword(),c1.getCustomerRole());
    }

    public CustomerVerificationDto getDetailsById(Integer customerVerificationId){
        CustomerVerificationEntity customer =customerVerificationRepository.findById(customerVerificationId).get();
        return new CustomerVerificationDto(customer.getCustomerVerificationId(), customer.getCustomerUserName(), customer.getCustomerPassword(),customer.getCustomerRole());
    }
}
