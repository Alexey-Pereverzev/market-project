package org.example.november_market_2.auth.tests;

import org.example.november_market_2.auth.entities.Role;
import org.example.november_market_2.auth.entities.User;
import org.example.november_market_2.auth.repositories.UserRepository;
import org.example.november_market_2.auth.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        User user = new User();
        user.setUsername("bob");
        user.setEmail("bob_johnson@gmail.com");
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(List.of(role));
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername("bob");

        Optional<User> user1 = userService.findByUsername("bob");
        Assertions.assertEquals("bob", user1.get().getUsername());
        Assertions.assertEquals("bob_johnson@gmail.com", user1.get().getEmail());
        Assertions.assertEquals("ROLE_USER",
                user1.get().getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()).get(0));
    }

}
