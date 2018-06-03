package com.transportation.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.transportation.app.data.AppUserData;

public interface AppUserDataRepository extends CrudRepository<AppUserData, Long>{
	
	AppUserData findByUsername(String username);
	AppUserData findByEmail(String email);

}
