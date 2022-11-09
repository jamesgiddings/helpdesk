package com.fdmgroup.feignclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.feignclient.request.UserRequestService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gateway/users")
@RequiredArgsConstructor
public class UserRequestController {

	Logger logger = LoggerFactory.getLogger(UserRequestController.class);

	@NonNull
	private UserRequestService userRequestService;

	@PostMapping("/admin")
	ResponseEntity<?> saveAdmin(@RequestBody Object admin) {
		logger.info("Feign Client: Saving admin: {}", admin);
		return new ResponseEntity<>(userRequestService.saveAdmin(admin), HttpStatus.CREATED);
	}

	@PostMapping("/client")
	ResponseEntity<?> saveClient(@RequestBody Object client) {
		logger.info("Feign Client: Saving client: {}", client);
		return new ResponseEntity<>(userRequestService.saveClient(client), HttpStatus.CREATED);
	}

	@PostMapping("/engineer")
	ResponseEntity<?> saveEngineer(@RequestBody Object engineer) {
		logger.info("Feign Client: Saving engineer: {}", engineer);
		return new ResponseEntity<>(userRequestService.saveEngineer(engineer), HttpStatus.CREATED);
	}

	@GetMapping
	ResponseEntity<?> findAllUsers() {
		logger.info("Feign Client: Finding all users");
		return new ResponseEntity<>(userRequestService.findAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/engineers")
	ResponseEntity<?> findAllEngineers() {
		logger.info("Feign Client: Finding all egineers");
		return new ResponseEntity<>(userRequestService.findAllEngineers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<?> findUserById(@PathVariable long id) {
		logger.info("Feign Client: Finding user by id: {}", id);
		return new ResponseEntity<>(userRequestService.findUserById(id), HttpStatus.OK);
	}

	@GetMapping("/authenticate/{username}/{password}")
	ResponseEntity<?> findUserByUsername(@PathVariable String username, @PathVariable String password) {
		logger.info("Feign Client: Finding user by username: {}", username);
		return new ResponseEntity<>(userRequestService.findUserByUsername(username, password), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteUserById(@PathVariable long id) {
		logger.info("Feign Client: Deleting user by id: {}", id);
		userRequestService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
