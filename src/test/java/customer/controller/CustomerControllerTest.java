package customer.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import customer.dto.CustomerDto;
import customer.service.CustomerService;
import customer.service.CustomerVerificationService;
import customer.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CustomerService customerService;

    @MockitoBean
    CustomerVerificationService customerVerificationService;

    @MockitoBean
    JwtService jwtService;

    @Test
    void createCustomerTest() throws Exception {
        CustomerDto customerDto=new CustomerDto(1,"Jasna",26);
        when(customerService.createCustomer(any(CustomerDto.class)))
                .thenReturn(customerDto);

        mockMvc.perform(post("/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.customerName").value("Jasna"))
                .andExpect(jsonPath("$.customerAge").value(26));
    }

    @Test
    void getByIdTest() throws Exception {
        CustomerDto customerDto=new CustomerDto(1,"Jasna",26);

        when(customerService.getById(1))
                .thenReturn(customerDto);

        mockMvc.perform(get("/api/v1/getById/{customerId}",customerDto.getCustomerId()  )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.customerName").value("Jasna"))
                .andExpect(jsonPath("$.customerAge").value(26));
    }

    @Test
    void updateByIdTest() throws Exception {
        CustomerDto customerDto=new CustomerDto(1,"Jasna",26);

        when(customerService.updateCustomer(1))
                .thenReturn(customerDto);

        mockMvc.perform(patch("/api/v1/updateById/{customerId}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.customerName").value("Jasna"))
                .andExpect(jsonPath("$.customerAge").value(26));

    }

    @Test
    void deleteByIdTest() throws Exception {
        doNothing().when(customerService).deleteCustomer(1);

        mockMvc.perform(delete("/api/v1/delete/{customerId}",1))
                .andExpect(status().isOk());
    }
}
