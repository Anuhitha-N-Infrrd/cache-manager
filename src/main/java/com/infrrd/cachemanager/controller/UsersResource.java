package com.infrrd.cachemanager.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infrrd.cachemanager.model.Users;
import com.infrrd.cachemanager.repo.UsersRepository;

@RestController
@RequestMapping("/user")
public class UsersResource {
	
	private UsersRepository userRepository;

    public UsersResource(UsersRepository userRepository) {
    	this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public Users add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name) {
        userRepository.save(new Users(id, name));
        return userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public Users update(@PathVariable("id") final String id,
                       @PathVariable("name") final String name) {
        userRepository.update(new Users(id, name));
        return userRepository.findById(id);
    }
    
    @GetMapping("/all")
    public Map<String, Users> all() {
        return userRepository.findAll();
    }
    
    @GetMapping("/find/{id}")
    public Users find(@PathVariable("id") final String id) {
        return userRepository.findById(id);
    }

    @GetMapping("/delete/{id}")
    public Map<String, Users> delete(@PathVariable("id") final String id) {
        userRepository.delete(id);
        return all();
    }

}
