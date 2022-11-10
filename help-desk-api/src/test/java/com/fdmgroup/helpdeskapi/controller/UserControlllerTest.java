package com.fdmgroup.helpdeskapi.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

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
import com.fdmgroup.helpdeskapi.model.request.AdminRequest;
import com.fdmgroup.helpdeskapi.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControlllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	UserController userController;

	Admin admin1, admin2;
	AdminRequest adminRequest1, adminRequest2;
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

		adminRequest1 = new AdminRequest();
		adminRequest1.setFullName("George Smith");
		adminRequest1.setEmail("george@msn");
		adminRequest1.setUsername("GSmith");
		adminRequest1.setPassword("password");

		admin2 = new Admin();
		admin2.setFullName("Georgia Smith");
		admin2.setEmail("georgia@msn");
		admin2.setUsername("GaSmith");
		admin2.setPassword("password");

		adminRequest2 = new AdminRequest();
		adminRequest2.setFullName("Georgia Smith");
		adminRequest2.setEmail("georgia@msn");
		adminRequest2.setUsername("GaSmith");
		adminRequest2.setPassword("password");

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
	void testDeleteUserById() {
		given(userService.findUserById(1)).willReturn(admin1);
		userController.deleteUserById(1);
		verify(userService).findUserById(1);
		verify(userService).deleteUserById(1);
	}

	@Test
	void testFindAllUsers() {
		userController.findAllUsers();
		verify(userService).findAllUsers();
	}

	@Test
	void testFindUserById() {
		userController.findUserById(1);
		verify(userService).findUserById(1);
	}

	@Test
	void testSaveUser() {
		userController.saveAdmin(adminRequest1);
		verify(userService).saveUser(admin1);
	}

	@Test
	void testUpdateTicket() {
		userController.saveAdmin(adminRequest1);

		admin1.setFullName("G Smith");
		adminRequest1.setFullName("G Smith");

		userController.saveAdmin(adminRequest1);
		verify(userService, times(1)).saveUser(admin1);

	}
}
