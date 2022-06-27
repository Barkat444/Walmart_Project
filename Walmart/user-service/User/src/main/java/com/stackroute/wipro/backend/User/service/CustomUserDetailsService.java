package com.stackroute.wipro.backend.User.service;
import com.stackroute.wipro.backend.User.model.CustomUserDetails;
import com.stackroute.wipro.backend.User.model.UserData;
import com.stackroute.wipro.backend.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if(username.equals("atif360thewhitewolf@gmail.com")) {
//			return new User("atif360thewhitewolf@gmail.com","pas1323",new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("User Not Found Exception!!");
//		}
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user=userRepository.findByUserName(username);

        if(user!=null) {
            return new CustomUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException("User Not Found Exception!!");
        }

    }
}