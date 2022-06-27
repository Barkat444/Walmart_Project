package com.stackroute.wipro.backend.User.Controller;
import com.stackroute.wipro.backend.User.JwtUtil.JwtUtil;
import com.stackroute.wipro.backend.User.exception.UserAlreadyExistException;
import com.stackroute.wipro.backend.User.exception.UserNotFoundException;
import com.stackroute.wipro.backend.User.model.CustomUserDetails;
import com.stackroute.wipro.backend.User.model.JwtRequest;
import com.stackroute.wipro.backend.User.model.JwtResponse;
import com.stackroute.wipro.backend.User.model.UserData;
import com.stackroute.wipro.backend.User.service.CustomUserDetailsService;
import com.stackroute.wipro.backend.User.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;


@RestController
//@CrossOrigin
@RequestMapping("/user")
public class UserController {



	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CustomUserDetailsService userDetailsService;
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private UserService userService;


	//Login
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(jwtRequest.getUsername(),jwtRequest.getPassword()));
		}
		catch (BadCredentialsException e){
			throw new UserNotFoundException("incorrect username and password!!");
		}
		final CustomUserDetails userDetails=(CustomUserDetails) userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);

		JwtResponse jwtResponse=new JwtResponse(jwt);
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setEmail(userDetails.getEmail());

		return ResponseEntity.ok(jwtResponse);
	}


	//Registering a new User
	@RequestMapping(value = "/registerUser",method = RequestMethod.POST)
	public ResponseEntity<UserData>  RegisterUser(@Valid @RequestBody UserData user)throws UserAlreadyExistException {
		String tempEmail=user.getEmail();
		UserData userObj=null;

		if(tempEmail!=null && !"".equals(tempEmail)){
            userObj=userService.createNewUser(user);
        }

		//creating empty wishlist for new user
		String new_userEmail=userObj.getEmail();
		String url="http://wishlist-service/wishlist/create/"+new_userEmail;
		this.restTemplate.getForObject(url,String.class);
		return ResponseEntity.ok(userObj);
	}


	//Updating an existing user
	@PutMapping("/update/{id}")

	public ResponseEntity<UserData> updateUser(@RequestBody UserData  user,@PathVariable("id") String email)throws UserNotFoundException{
		UserData updatedUserData=this.userService.UpdateUser(user,email);
		return ResponseEntity.ok(updatedUserData);
	}


}