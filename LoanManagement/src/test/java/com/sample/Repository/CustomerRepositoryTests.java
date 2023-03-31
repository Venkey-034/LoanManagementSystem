package com.sample.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sample.Model.Customer;

@DataJpaTest
public class CustomerRepositoryTests {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Test
	public void givenCustomerObject_whenSave_thenReturnCustomerObject()
	{
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
    
    Customer savedEmployee=customerRepo.save(customer);
    
   assertThat(savedEmployee).isNotNull();
    assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
}
