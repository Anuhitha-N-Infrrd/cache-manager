package com.infrrd.cachemanager.repo;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.infrrd.cachemanager.model.Users;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

	private RedisTemplate<String, Users> redisTemplate;
	
	private HashOperations hashOperations;
	
	public UsersRepositoryImpl(RedisTemplate<String, Users> redisTemplate) {
		this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public void save(Users user) {
		hashOperations.put("USERS", user.getId(), user);
		
	}

	@Override
	public Map<String, Users> findAll() {
		return hashOperations.entries("USERS");
	}

	@Override
	public Users findById(String id) {
		return (Users) hashOperations.get("USERS", id);
	}

	@Override
	public void update(Users user) {
		save(user);
		
	}

	@Override
	public void delete(String id) {
		hashOperations.delete("USERS", id);
		
	}
	
}
