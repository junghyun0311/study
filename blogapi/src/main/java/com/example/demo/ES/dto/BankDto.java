package com.example.demo.ES.dto;

import lombok.Builder; 
import lombok.Getter; 
import lombok.NoArgsConstructor; 
import lombok.ToString; 
import java.io.Serializable; 

@Getter 
@ToString 
@NoArgsConstructor 
public class BankDto implements Serializable { //document 단위가 정보를 보관할 dto
	private int account_number; 
	private int balance;
	private String firstname;
	private String lastname;
	private int age;
	private String gender;
	private String address;
	private String employer;
	private String email;
	private String city;
	private String state;
	
	
	@Builder 
	public BankDto(int account_number, int balance, String firstname, String lastname, int age, 
			String gender, String address, String employer, String email, String city, String state) { 
		this.account_number = account_number;
		this.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.employer = employer;
		this.email = email;
		this.city = city;
		this.state = state;	
	} 
}
