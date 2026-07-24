package customer.service;

import customer.dto.CustomerDto;
import customer.entity.Customer;
import customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerDto customerDto){
        Customer customer =new Customer(1,"Jasna",26);
        customerRepository.save(customer);
        return new CustomerDto(customer.getCustomerId(), customer.getCustomerName(),customerDto.getCustomerAge());
    }

    public CustomerDto getById(Integer customerId){
        Customer customer =customerRepository.findById(customerId).get();
        return new CustomerDto(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAge());
    }

    public CustomerDto updateCustomer(Integer customerId){
        Customer customer =customerRepository.findById(customerId).get();
        customer.setCustomerAge(28);
        return new CustomerDto(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAge());
    }

    public void deleteCustomer(Integer customerId){
        customerRepository.deleteById(customerId);
    }
}
