package com.masai.SpringBootApp.Service.Impl;

import com.masai.SpringBootApp.Service.UserService;
import com.masai.SpringBootApp.exception.UserException;
import com.masai.SpringBootApp.model.User;
import com.masai.SpringBootApp.modelDto.userDto;
import com.masai.SpringBootApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getUserByEmailId(String emailId) throws UserException {
		return userRepository.findByEmail(emailId).orElseThrow(() ->
				new UserException("User not found"));

	}

	@Override
	public User addUser(userDto customer) throws UserException {
		if (customer == null)
			throw new UserException("customer Can not be Null");
		Optional<User> findByEmail = userRepository.findByEmail(customer.getEmail());
		if (findByEmail.isPresent()) {
			throw new UserException("Email already Register");
		}

		User newCustomer = new User();
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());

		newCustomer.setRegisterTime(LocalDateTime.now());
		return userRepository.save(newCustomer);
	}

	@Override
	@CacheEvict(cacheNames = "user", key = "#userId")
	public void deleteUser(Integer userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() ->
				new UserException("User not found with Id :" +userId));
		userRepository.deleteUser(userId);
		log.info("user deleted from database");
	}

	@Override
	@Cacheable(cacheNames = "user", key = "#userId")
	public User getUserDetails(Integer userId) throws UserException {
		User existingUser = userRepository.findById(userId).orElseThrow(() ->
				new UserException("User not found with Id :" +userId));
		log.info("get user details");
		return existingUser;

	}

	@Override
	@Cacheable(cacheNames = "user", key = "#page + '-' + #size", cacheManager = "caffeineCacheManager")
	public Page<User> getAllUserDetails(
			Integer page,
			Integer size
	) throws UserException {

		Pageable pageable = PageRequest.of(page - 1, size);
		Page<User> userList=userRepository.findAll(pageable);
		log.info("fetched All user details");

		if (userList.isEmpty()) {
			throw new UserException("User list is Empty");
		}
		return userList;
	}
}
