package com.infrrd.cachemanager.service;

import java.util.List;
import java.util.Map;

import com.infrrd.cachemanager.entity.Users;

public interface UserService {
	
	public Users getUser(long id);
    public List<Users> getAll();
    public void delete(long id);
    public Users update(Users user);
    public Users create(Users user);
}
