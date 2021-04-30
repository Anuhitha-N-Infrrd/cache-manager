package com.infrrd.cachemanager.repo;

import java.util.Map;

import com.infrrd.cachemanager.model.Users;

public interface UsersRepository {
	
	void save(Users user);
	Map<String, Users> findAll();
	Users findById(String id);
	void update(Users user);
	void delete(String id);

}
