package com.transportation.app.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCrypt;


@Entity
@Table(name="User_Data")
public class AppUserData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERDATA_SEQ")
    @SequenceGenerator(sequenceName = "user_data_seq", allocationSize = 1, name = "USERDATA_SEQ")
	Long id;
	
	String username;
	String password;
	String email;
	Boolean active;
	Boolean loggedIn;
	
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
		this.password = hashPassword(password);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	private static String hashPassword(String plainText) {
		return BCrypt.hashpw(plainText, BCrypt.gensalt(14));
	}
	

}
