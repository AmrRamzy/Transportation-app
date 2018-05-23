package com.transportation.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.transportation.app.data.UserData;

public interface UserDataRepository extends CrudRepository<UserData, Long>{
	
	UserData findByUsername(String username);
	UserData findByEmail(String email);

}
