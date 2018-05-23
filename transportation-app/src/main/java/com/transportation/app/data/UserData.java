package com.transportation.app.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERDATA_SEQ")
    @SequenceGenerator(sequenceName = "user_data_seq", allocationSize = 1, name = "USERDATA_SEQ")
	Long id;
	
	String username;
	String password;
	String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
