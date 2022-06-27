package com.stackroute.wipro.backend.User.service;
import com.stackroute.wipro.backend.User.exception.UserAlreadyExistException;
import com.stackroute.wipro.backend.User.exception.UserNotFoundException;
import com.stackroute.wipro.backend.User.model.UserData;
import com.stackroute.wipro.backend.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserData createNewUser(UserData user)throws UserAlreadyExistException {
        UserData userData = userRepository.findByEmail(user.getEmail());
        if (userData == null) {
            return userRepository.save(user);
        } else
            throw new UserAlreadyExistException("User already exist");
    }


    @Override
    public UserData UpdateUser(UserData user,String email)throws UserNotFoundException {
        UserData getUser=this.userRepository.findByEmail(email);
        System.out.println(user);
        if (getUser==null)
            throw new UserNotFoundException("User do not exist!!");
            else {
            getUser.setUsername(user.getUsername());
            getUser.setPassword(user.getPassword());
            return this.userRepository.save(getUser);
        }
    }


    @Override
    public void deleteUser(UserData user) {

    }


	

}
