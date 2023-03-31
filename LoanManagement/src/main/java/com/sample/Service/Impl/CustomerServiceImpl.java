package com.sample.Service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sample.Exception.CustomerAlreadyRegisteredException;
import com.sample.Exception.CustomerNotFoundException;
import com.sample.Model.Customer;
import com.sample.Repository.CustomerRepository;
import com.sample.Services.iCustomerService;


@Service
@Primary
public class CustomerServiceImpl implements iCustomerService {

	@Autowired
	private CustomerRepository customerDao;

	//private Logger logger = Logger.getLogger(getClass());

	@Override
	public Customer addCustomer(Customer c) {
		Customer customer = customerDao.checkCustomer(c.getEmail(), c.getAdhaar(), c.getPan(), c.getPhone());
		if (customer != null) {
			throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getId());
		}
		return this.customerDao.save(c);
	}

	@Override
	public Integer doLogin(String email, String password) {
		Integer customerId = null;
		try {
			customerId = customerDao.findCustomerByEmailAndPassword(email, password);
			//logger.info("Customer: " + customerId + " Logged In Successfully");
			return customerId;
		} catch (Exception e) {
			throw new CustomerNotFoundException("Customer Not Found: " + customerId);
		}
	}
   @Override
	public Customer updateCustomer(Customer c) {
		Customer customer = customerDao.findById(c.getId());
			//	.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
		BeanUtils.copyProperties(c, customer);
		return customerDao.save(customer);
	}

	@Override
	public List<Customer> getCustomers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return customerDao.findAll(pageable).toList();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = customerDao.findById(customerId);
			//	.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
		//logger.info("Customer Found: " + customerId);
		return customer;
	}

}
