package customer.controller;
import customer.dto.CustomerDto;
import customer.dto.CustomerVerificationDto;
import customer.service.CustomerService;
import customer.service.CustomerVerificationService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerVerificationService customerVerificationService;

    @PostMapping(value = "/customer/verification")
    public ResponseEntity<CustomerVerificationDto> customerData(@RequestBody CustomerVerificationDto customerVerificationDto){
        CustomerVerificationDto customerVerificationDto1=customerVerificationService.saveData(customerVerificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerVerificationDto1);
    }

    @GetMapping(value = "/getDetailsById/{customerId}")
    public ResponseEntity<Integer> getByVerificationIdId(@PathVariable Integer customerId){
        CustomerVerificationDto customerEntity= customerVerificationService.getDetailsById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerEntity.getCustomerVerificationId());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto customerEntity= customerService.createCustomer(customerDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(customerEntity);
    }

    @GetMapping(value = "/token")
    public Object getToken(HttpServletRequest httpServletRequest){
        return httpServletRequest.getAttribute("_csrf");
    }

    @GetMapping(value = "/sessionId")
    public String getSession(HttpServletRequest httpServletRequest){
        return httpServletRequest.getSession().getId();
    }

    @GetMapping(value = "/getById/{customerId}")
    public ResponseEntity<String> getById(@PathVariable Integer customerId){
        CustomerDto customerEntity= customerService.getById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body("Retrieved");
    }

    @PatchMapping(value = "/updateById/{customerId}")
    public ResponseEntity<CustomerDto> updateById(@PathVariable Integer customerId){
        CustomerDto customerEntity=customerService.updateCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerEntity);
    }

    @DeleteMapping(value = "/delete/{customerId}")
    public ResponseEntity<String> deleteById(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @GetMapping(value = "/hi")
    public String helloWorld(HttpServletRequest req){
        HttpSession session=req.getSession(false);
        if(session==null){
            return "Please authenticate";
        }else{
            return "Hello world";
        }
    }


}
