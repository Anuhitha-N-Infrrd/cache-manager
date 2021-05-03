package com.infrrd.cachemanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infrrd.cachemanager.entity.Users;
import com.infrrd.cachemanager.repo.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UsersRepository userRepository;

	@Override
	public Users getUser(long id) {
		System.out.print("Inside get User");
		logger.debug(" >> UserService : Entering getUser");
		Optional<Users> userOp = Optional.of(userRepository.findOne(id));
		if (userOp != null) {
			logger.debug(" << UserService : Exiting getUser");
			return userOp.get();
		}

		else {
			logger.debug(" << UserService : No Such User Exists : Exiting getUser");

			return null;
		}
	}

	@Override
	public List<Users> getAll() {
		logger.debug(" >> UserService : Entering getAll");
		logger.debug(" << UserService : Exiting getAll");
		return userRepository.findAll();
	}

	@Override
	public void delete(long id) {
		userRepository.delete(id);

	}

	@Override
	public Users update(Users user) {
		logger.debug(">> UserService : Entering update");
		Long id = user.getId();
		Users userInDb = getUser(id);
		if (userInDb != null) {
			logger.debug(">> UserService : User updated : Exiting update");
			return create(user);
		} else {
			logger.debug(">> UserService : User with this id does not exist : Exiting update");
			return null;
		}
	}

	@Override
	public Users create(Users user) {
		logger.debug(" >> UserService : Entering create");

		Users userToRet = userRepository.save(user);
		logger.debug(" << UserService : Exiting create");
		return userToRet;
	}

}
