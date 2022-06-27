package com.stackroute.wipro.backend.User;


import com.stackroute.wipro.backend.User.exception.UserAlreadyExistException;
import com.stackroute.wipro.backend.User.exception.UserNotFoundException;
import com.stackroute.wipro.backend.User.model.UserData;
import com.stackroute.wipro.backend.User.repository.UserRepository;

import com.stackroute.wipro.backend.User.service.CustomUserDetailsService;
import com.stackroute.wipro.backend.User.service.UserServiceImpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    UserData dummyUser;
    UserData updateUser;
    @BeforeEach
    void setup(){
         dummyUser=new UserData(12L,"Mohan","mohan123@gmail.com","12345678","USER");
         updateUser=new UserData();
         updateUser.setUsername("Rohan");
         updateUser.setPassword("244455");
         updateUser.setEmail("mohan123@gmail.com");
    }


    @DisplayName("SAVE and Return UserData for the given email (CREATE/SAVE SUCCESS CASE)")
    @Test
    public void givenUser_whenSaveUser_thenReturnUserObject_success_case()throws UserAlreadyExistException {

        Mockito.when(userRepository.findByEmail("mohan123@gmail.com")).thenReturn(null);
        Mockito.when(userRepository.save(dummyUser)).thenReturn(dummyUser);

        assertEquals(dummyUser.getEmail(),userService.createNewUser(dummyUser).getEmail());
    }

    @DisplayName("User Already Exist for the given email (CREATE/SAVE FAILURE CASE)")
    @Test
    public void givenUser_whenSaveUser_thenThrowUserAlreadyExistException_failure_case(){
        Mockito.when(userRepository.findByEmail("mohan123@gmail.com")).thenReturn(dummyUser);
        assertThrows(UserAlreadyExistException.class,
                () -> userService.createNewUser(dummyUser),
                "User already present Failure Case");
    }

    /* Update Case */

    @DisplayName("Return User for the given email (UPDATE SUCCESS CASE)")
    @Test
    public void givenUser_whenUpdateUser_thenReturnUserObject_success_case()throws UserNotFoundException {

        Mockito.when(userRepository.findByEmail("mohan123@gmail.com")).thenReturn(dummyUser);
        Mockito.when(userRepository.save(dummyUser)).thenReturn(updateUser);

        assertEquals("Rohan",userService.UpdateUser(updateUser,"mohan123@gmail.com").getUsername());
    }




    @DisplayName("User Not Found for the given email (UPDATE FAILURE CASE)")
    @Test
    public void givenUser_UpdateUserData_thenThrowUserNotFoundExceptionException_failure_case(){
        Mockito.when(userRepository.findByEmail("mohan123@gmail.com")).thenReturn(null);

        assertThrows(UserNotFoundException.class,
                () -> userService.UpdateUser(updateUser,dummyUser.getEmail()),
                "User Not Found");
    }

    @InjectMocks
    private CustomUserDetailsService customerUserDetailsService;


    @Test
    public void test() {
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(dummyUser);

        UserDetails user=customerUserDetailsService.loadUserByUsername(Mockito.anyString());
        assertThat(user).isNotNull();
    }

    @Test
    public void test2() {
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> customerUserDetailsService.loadUserByUsername(Mockito.anyString()),
                "User Not Found");
    }


}
