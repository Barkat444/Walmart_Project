package com.stackroute.wipro.backend.User;

import com.stackroute.wipro.backend.User.model.UserData;
import com.stackroute.wipro.backend.User.repository.UserRepository;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public class UserRepositoryLayerTest {

        @Autowired
        private UserRepository userRepository;

        private UserData userOne, userTwo;

        @BeforeEach
        public void setUp() {
            userOne = new UserData(1L, "Rohan", "something@gmail.com", "12345", "customer");
            userTwo = new UserData(2L, "anything", "anything@gmail.com", "123456", "customer");

        }

        @AfterEach
        public void tearDown() {
        userOne = null;
        userTwo = null;
        }

        @DisplayName("Create New User (Repository Layer Testing)")
        @Test
        public void CreateUserTest()
        {
             userRepository.save(userOne);
             UserData userData= userRepository.findByEmail("something@gmail.com");
            assertThat(userData.getEmail()).isEqualTo("something@gmail.com");
            assertThat(userData.getUsername()).isEqualTo("Rohan");
        }


        @DisplayName("Update Existing User (Repository Layer Testing)")
        @Test
        public void UpdateUserTest()
        {
            userRepository.save(userOne);
            UserData userData=userRepository.findByEmail("something@gmail.com");
            userData.setUsername("Mohan");
           UserData updatedUser=userRepository.save(userData);
            assertThat(updatedUser.getUsername()).isEqualTo("Mohan");
        }
    }

