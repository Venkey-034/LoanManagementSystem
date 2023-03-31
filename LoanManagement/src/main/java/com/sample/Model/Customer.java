package com.sample.Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fname;
	private String lname;
	private String gender;
	private long phone;
	private String email;
	private String password;
	private String confirmPassword;
	private float salary;
	private long adhaar;
	private String pan;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity=Loan.class)
	private List<Loan> loans = new ArrayList<Loan>();

	public Customer() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public long getAdhaar() {
		return adhaar;
	}

	public void setAdhaar(long adhaar) {
		this.adhaar = adhaar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

    @JsonManagedReference 
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", phone="
				+ phone + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", salary=" + salary + ", adhaar=" + adhaar + ", pan=" + pan + ", loans=" + loans + "]";
	}

	// the method below will add Loan to LoansList
	// also serves the purpose to avoid cyclic references.
	public void addLoan(Loan loan) {
		loan.setCustomer(this); // this will avoid nested cascade
		this.getLoans().add(loan);
	}
	public Customer(String jsonString) {
		ObjectMapper objectMapper=new ObjectMapper();
		try
		{
			Customer customer=objectMapper.readValue(jsonString,Customer.class);
			this.id=customer.getId();
			this.fname=customer.getFname();
			this.lname=customer.getLname();
			this.gender=customer.getGender();
			this.phone=customer.getPhone();
			this.email=customer.getEmail();
			this.password=customer.getPassword();
			this.confirmPassword=customer.getConfirmPassword();
			this.salary=customer.getSalary();
			this.adhaar=customer.getAdhaar();
			this.pan=customer.getPan();
			this.loans=customer.getLoans();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
