package com.fdmgroup.helpdeskapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.helpdeskapi.model.Admin;
import com.fdmgroup.helpdeskapi.model.Client;
import com.fdmgroup.helpdeskapi.model.Engineer;
import com.fdmgroup.helpdeskapi.model.User;
import com.fdmgroup.helpdeskapi.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    Admin admin1, admin2;
    Client client1, client2;
    Engineer engineer1, engineer2;

    List<User> users;

    @BeforeEach
    void setUp() throws Exception {
        admin1 = new Admin();
        admin1.setFullName("George Smith");
        admin1.setEmail("george@msn");
        admin1.setUsername("GSmith");
        admin1.setPassword("password");

        admin2 = new Admin();
        admin2.setFullName("Georgia Smith");
        admin2.setEmail("georgia@msn");
        admin2.setUsername("GaSmith");
        admin2.setPassword("password");

        client1 = new Client();
        client1.setFullName("Joe Bloggs");
        client1.setEmail("joe@msn");
        client1.setUsername("JBloggs");
        client1.setPassword("password");

        client2 = new Client();
        client2.setFullName("Joanna Bloggs");
        client2.setEmail("joanna@msn");
        client2.setUsername("JaBloggs");
        client2.setPassword("password");

        engineer1 = new Engineer();
        engineer1.setFullName("Tim Knight");
        engineer1.setEmail("tim@msn");
        engineer1.setUsername("TKnight");
        engineer1.setPassword("password");
        engineer1.setSpecialism("Teleocoms");

        engineer2 = new Engineer();
        engineer2.setFullName("Tarra Knight");
        engineer2.setEmail("tarra@msn");
        engineer2.setUsername("TaKnight");
        engineer2.setPassword("password");
        engineer2.setSpecialism("Software");

        users = List.of(admin1, admin2, engineer1, engineer2, client1, client2);
    }

    @Test
    void testSaveUser() {

        given(userRepository.save(admin1)).willReturn(admin1);

        Admin actual = (Admin) userService.saveUser(admin1);

        assertThat(actual).isNotNull();
        verify(userRepository, times(1)).save(admin1);
    }

    @Test
    public void testFindAllUsers() {

        given(userRepository.findAll()).willReturn(users);

        Iterable<User> actual = userService.findAllUsers();

        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(6);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllEngineers() {

        given(userRepository.findAll()).willReturn(users);

        Iterable<User> actual = userService.findAllEngineers();

        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindUserById() {
        // given
        Long id = 1L;
        Optional<User> optionalUser = Optional.of(client1);
        given(userRepository.findById(id)).willReturn(optionalUser);
        // when
        assertThat(userService.findUserById(id)).isEqualTo(optionalUser.get());
        // then
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteTicketById() {
        // given
        Long id = 1L;
        // when
        userService.deleteUserById(id);
        // then
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindUserByUsername() {
        // given
        String username = "testUser";
        // when
        userService.findUserByUsername(username);
        // then
        verify(userRepository, times(1)).findByUsername(username);
    }

}
