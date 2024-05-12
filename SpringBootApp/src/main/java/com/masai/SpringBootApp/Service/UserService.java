package com.masai.SpringBootApp.Service;

import com.masai.SpringBootApp.exception.UserException;
import com.masai.SpringBootApp.model.User;
import com.masai.SpringBootApp.modelDto.userDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



@Service
public interface UserService {

	User getUserByEmailId(String emailId);

	User addUser(userDto customer)  ;

	void deleteUser(Integer userId);
	

	User getUserDetails(Integer userId);

	Page<User> getAllUserDetails(
            Integer page,
            Integer size
    ) throws UserException;

}
