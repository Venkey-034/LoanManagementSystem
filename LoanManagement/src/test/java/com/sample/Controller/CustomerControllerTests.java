package com.sample.Controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.Model.Customer;
import com.sample.Repository.CustomerRepository;
import com.sample.Service.Impl.CustomerServiceImpl;
import com.sample.Service.Impl.LoanServiceImpl;
import com.sample.Services.iCustomerService;
import com.sample.Services.iLoanService;

//@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CustomerControllerTests {
 
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired 
	private CustomerRepository customerRepo;
	@Test
	public void testAddCustomer() throws Exception {
	Customer customer=new Customer();
	customer.setFname("anil");
    customer.setLname("kumar");
    customer.setGender("male");
    customer.setPhone(9705703318L);
    customer.setEmail("venkey@gmail.com");
    customer.setPassword("12345");
    customer.setConfirmPassword("12345");
    customer.setSalary(1200000);
    customer.setAdhaar(123456789868L);
    customer.setPan("RTY1231Y");
    
    ResponseEntity<Customer> responce=restTemplate.postForEntity("/customer/addCustomer",customer,Customer.class);
    
    
    assertEquals(HttpStatus.OK,responce.getStatusCode());
    
    Customer savedCustomer=customerRepo.findById(responce.getBody().getId());//.orElse(null);
    assertNotNull(savedCustomer);
    assertEquals("anil",savedCustomer.getFname());
    assertEquals("kumar",savedCustomer.getLname());
    assertEquals("male",savedCustomer.getGender());
    assertEquals(9705703318L,savedCustomer.getPhone());
    assertEquals("venkey@gmail.com",savedCustomer.getEmail());
    assertEquals("12345",savedCustomer.getPassword());
    assertEquals("12345",savedCustomer.getConfirmPassword());
    assertEquals(1200000,savedCustomer.getSalary());
    assertEquals(123456789868L,savedCustomer.getAdhaar());
    assertEquals("RTY1231Y",savedCustomer.getPan());
	}
	
	//@Autowired
	//private MockMvc mockMvc;
	//@MockBean
	//private CustomerServiceImpl customerService;
	//@MockBean
	//private iCustomerService icustomerService;
	//@MockBean
	//private iLoanService iloanService;
	//@MockBean
	//private LoanServiceImpl loanService;
	//@Autowired
	//private ObjectMapper objectMapper;
	//@Autowired
    //private static ObjectMapper mapper = new ObjectMapper();
	/*@Test
	public void givenCustomerObject_whenCreateCustomer_thenReturnSaveCustomerObject()
	{
		Customer customer=Customer.builder()
				.fName()
				.lName()
				.gender()
				.phone()
				.email()
				.password()
				.confirmPassword()
				.salary()
				.adhaar()
				.pan()
				.build();
				
	}*/
   /* @Test
    public void testGetExample() throws Exception {
       // List<Customer> students = new ArrayList<>();
        Customer student = new Customer();
      //  student.setId(1);
        student.setFname("Arun");
        student.setLname("anil");
        student.setGender("male");
        student.setPhone(970570331);
        student.setEmail("venkey@gmail.com");
        student.setPassword("12345");
        student.setConfirmPassword("12345");
        student.setSalary(1200000);
        student.setAdhaar(1234567898);
        student.setPan("RTY1231Y");
      //  students.add(student);

        Mockito.when(icustomerService.addCustomer(ArgumentMatchers.any())).thenReturn(student);
        String json = mapper.writeValueAsString(student);
        mockMvc.perform(post("/customer/addCustomer").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
               // .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.fname", Matchers.equalTo("Arun")))
                .andExpect(jsonPath("$.lname", Matchers.equalTo("anil")))
                .andExpect(jsonPath("$.gender", Matchers.equalTo("male")))
                .andExpect(jsonPath("$.phone", Matchers.equalTo(970570331)))
                .andExpect(jsonPath("$.email", Matchers.equalTo("venkey@gmail.com")))
                .andExpect(jsonPath("$.password", Matchers.equalTo("12345")))
                .andExpect(jsonPath("$.confirmPassword", Matchers.equalTo("12345")))
                .andExpect(jsonPath("$.salary", Matchers.equalTo(1200000)))
                .andExpect(jsonPath("$.adhaar", Matchers.equalTo(1234567898)))
                .andExpect(jsonPath("$.pan", Matchers.equalTo("RTY1231Y")));
    }*/
    }


