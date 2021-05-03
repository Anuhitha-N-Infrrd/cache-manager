package com.infrrd.cachemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infrrd.cachemanager.entity.Users;
import com.infrrd.cachemanager.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	@Cacheable(value = "users", key = "#id")
	public Users getUser(@PathVariable long id) {
		System.out.print(id);
		logger.debug(" >> UserController : /user/'{}' call : ",id);
		return userService.getUser(id);

	}

	@PostMapping("/user")
	public Users create(@RequestBody Users user) {
		System.out.print(user.toString());
		logger.debug(" >> UserController : /user : '{}'", user.toString());
		return userService.create(user);
	}

	@GetMapping("/users")
	public List<Users> getAll() {
		logger.debug(" >> UserController : /users : ");
		return userService.getAll();
	}

	@PutMapping("/update")
	@CachePut(value = "users", key = "#user.id")
	public Users updateUser(@RequestBody Users user) {
		logger.debug(" >> UserController : /update : ", user.toString());
		return userService.update(user);
	}

	@DeleteMapping("/delete/{id}")
	@CacheEvict(value = "users", allEntries = false, key = "#id")
	public void deleteUser(@PathVariable Long id) {
		logger.debug(" >> UserController : /delete : ", id);
		userService.delete(id);
		logger.debug(" << UserController : /delete : ", id);

	}

}
