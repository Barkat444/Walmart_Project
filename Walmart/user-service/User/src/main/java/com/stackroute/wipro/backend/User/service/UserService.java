package com.stackroute.wipro.backend.User.service;

import com.stackroute.wipro.backend.User.exception.UserAlreadyExistException;
import com.stackroute.wipro.backend.User.exception.UserNotFoundException;
import com.stackroute.wipro.backend.User.model.UserData;

public interface UserService {
	
	public UserData createNewUser(UserData user)throws UserAlreadyExistException;

    UserData UpdateUser(UserData user, String email)throws UserNotFoundException;

    public void deleteUser(UserData user);
	

}
