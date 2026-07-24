package customer.service;

import customer.dto.CustomerDto;
import customer.entity.Customer;
import customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    void createCustomer(){
        Customer customerEntity=new Customer(1,"Jasna",26);
        CustomerDto customerDto=new CustomerDto(1,"Jasna",26);

        when(customerRepository.save(any(Customer.class))).thenReturn(customerEntity);

        CustomerDto customerDto1=customerService.createCustomer(customerDto);
        assertEquals(1,customerDto1.getCustomerId());
        assertEquals("Jasna",customerDto1.getCustomerName());
        assertEquals(26,customerDto1.getCustomerAge());
//        assertEquals(customerDto,customerDto1);
    }

    @Test
    void getById(){
        Customer customerEntity=new Customer(1,"Jasna",26);
        when(customerRepository.findById(customerEntity.getCustomerId())).thenReturn(Optional.of(customerEntity));
        CustomerDto customerDto=customerService.getById(customerEntity.getCustomerId());

        assertEquals(1,customerDto.getCustomerId());
        assertEquals("Jasna",customerDto.getCustomerName());
        assertEquals(26,customerDto.getCustomerAge());
    }

    @Test
    void updateCustomer(){
        Customer customerEntity=new Customer(1,"Jasna",26);
        when(customerRepository.findById(customerEntity.getCustomerId())).thenReturn(Optional.of(customerEntity));
        CustomerDto customerDto=customerService.updateCustomer(customerEntity.getCustomerId());

        assertEquals(1,customerDto.getCustomerId());
        assertEquals("Jasna",customerDto.getCustomerName());
        assertEquals(28,customerDto.getCustomerAge());
    }

    @Test
    void deleteCustomer(){
        Customer customerEntity=new Customer(1,"Jasna",26);
        doNothing().when(customerRepository).deleteById(customerEntity.getCustomerId());
        customerService.deleteCustomer(customerEntity.getCustomerId());
        verify(customerRepository).deleteById(customerEntity.getCustomerId());
    }
}
