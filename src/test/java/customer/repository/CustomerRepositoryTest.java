package customer.repository;
import customer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void createCustomer(){
        Customer customer = new Customer(1, "Jasna", 26);
        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getCustomerId());
        assertEquals("Jasna", savedCustomer.getCustomerName());
        assertEquals(26, savedCustomer.getCustomerAge());
    }

    @Test
    void getById(){
        Customer customer = new Customer(1, "Jasna", 26);
        customerRepository.save(customer);
        Customer savedCustomer = customerRepository.findById(customer.getCustomerId()).get();

        assertNotNull(savedCustomer.getCustomerId());
        assertEquals("Jasna", savedCustomer.getCustomerName());
        assertEquals(26, savedCustomer.getCustomerAge());
    }

    @Test
    void updateById(){
        Customer customer = new Customer(1, "Jasna", 26);
        customerRepository.save(customer);
        Customer savedCustomer = customerRepository.findById(customer.getCustomerId()).get();
        savedCustomer.setCustomerAge(28);
        customerRepository.save(savedCustomer);

        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).get();
        assertEquals(28, updatedCustomer.getCustomerAge());
    }

    @Test
    void deleteById(){
        Customer customer = new Customer(1, "Jasna", 26);
        customerRepository.save(customer);
        customerRepository.deleteById(customer.getCustomerId());
        assertTrue(customerRepository.findById(1).isEmpty());
    }
}
