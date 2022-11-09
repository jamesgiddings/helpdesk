package com.fdmgroup.feignclient.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ticket-service", path = "/api/v1/tickets", url = "${helpdeskapi.server.url}")
public interface TicketRequestService {

	@PostMapping
	Object saveTicket(@RequestBody Object ticket);

	@GetMapping
	List<Object> findAllTickets();

	@GetMapping("/unassigned")
	List<Object> findAllUnassignedTickets();

	@PutMapping
	Object updateTicket(@RequestBody Object ticket);

	@GetMapping("/{id}")
	Object findTicketById(@PathVariable long id);

	@DeleteMapping("/{id}")
	void deleteTicketById(@PathVariable long id);

	@GetMapping("/engineer/{id}")
	Object findTicketsByEngineerId(@PathVariable Long id);

	@GetMapping("/client/{id}")
	Object findTicketsByClientId(@PathVariable Long id);

	@GetMapping("/resolve/{id}")
	Object resolveTicketById(@PathVariable Long id);

	@GetMapping("/reopen/{id}")
	Object reopenTicketById(@PathVariable Long id);

	@GetMapping("/engineer/{engineerId}/addto/{ticketId}")
	Object assignTicketEngineerById(@PathVariable Long engineerId, @PathVariable Long ticketId);

	@DeleteMapping("/deleteMessage/{ticketId}/{messageId}")
	Object deleteMessageByTicketIdAndMessageId(@PathVariable long ticketId, @PathVariable long messageId);

	@PutMapping("/addMessage/{ticketId}")
	Object addMessageToTicketByTicketId(@PathVariable long ticketId, @RequestBody Object message);
}
