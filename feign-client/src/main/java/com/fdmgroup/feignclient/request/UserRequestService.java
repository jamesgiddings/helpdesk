package com.fdmgroup.feignclient.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service", path = "/api/v1/users", url = "${helpdeskapi.server.url}")
public interface UserRequestService {

	@PostMapping("/admin")
	Object saveAdmin(@RequestBody Object admin);

	@PostMapping("/engineer")
	Object saveEngineer(@RequestBody Object engineer);

	@PostMapping("/client")
	Object saveClient(@RequestBody Object client);

	@GetMapping
	List<Object> findAllUsers();

	@GetMapping("/{id}")
	Object findUserById(@PathVariable long id);

	@GetMapping("/authenticate/{username}/{password}")
	Object findUserByUsername(@PathVariable String username, @PathVariable String password);

	@DeleteMapping("/{id}")
	void deleteUserById(@PathVariable long id);

	@GetMapping("/engineers")
	List<Object> findAllEngineers();

}
