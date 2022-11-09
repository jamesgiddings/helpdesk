package com.fdmgroup.feignclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.feignclient.request.TicketRequestService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gateway/tickets")
@RequiredArgsConstructor
public class TicketRequestController {

    Logger logger = LoggerFactory.getLogger(TicketRequestController.class);

    @NonNull
    private TicketRequestService ticketRequestService;

    @PostMapping
    ResponseEntity<?> saveTicket(@RequestBody Object ticket) {
        logger.info("Feign Client: Saving ticket: {}", ticket);
        return new ResponseEntity<>(ticketRequestService.saveTicket(ticket), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<?> findAllTickets() {
        logger.info("Feign Client: Finding all tickets");
        return new ResponseEntity<>(ticketRequestService.findAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/unassigned")
    ResponseEntity<?> findAllUnassignedTickets() {
        logger.info("Feign Client: Finding all unassigned tickets");
        return new ResponseEntity<>(ticketRequestService.findAllUnassignedTickets(), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<?> updateTicket(@RequestBody Object ticket) {
        logger.info("Feign Client: Updating ticket: {}", ticket);
        return new ResponseEntity<>(ticketRequestService.updateTicket(ticket), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findTicketById(@PathVariable long id) {
        logger.info("Feign Client: Finding ticket by id: {}", id);
        return new ResponseEntity<>(ticketRequestService.findTicketById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTicketById(@PathVariable long id) {
        logger.info("Feign Client: Deleting ticket by id: {}", id);
        ticketRequestService.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/engineer/{id}")
    ResponseEntity<?> findTicketsByEngineerId(@PathVariable Long id) {
        logger.info("Feign Client: Finding tickets by engineer id: {}", id);
        return new ResponseEntity<>(ticketRequestService.findTicketsByEngineerId(id), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    ResponseEntity<?> findTicketsByClientId(@PathVariable Long id) {
        logger.info("Feign Client: Finding tickets by client id: {}", id);
        return new ResponseEntity<>(ticketRequestService.findTicketsByClientId(id), HttpStatus.OK);
    }

    @GetMapping("/resolve/{id}")
    public ResponseEntity<?> resolveTicketById(@PathVariable Long id) {
        logger.info("Feign Client: Resolving ticket by id: {}", id);
        return new ResponseEntity<>(ticketRequestService.resolveTicketById(id), HttpStatus.OK);
    }

    @GetMapping("/reopen/{id}")
    public ResponseEntity<?> reopenTicketById(@PathVariable Long id) {
        logger.info("Feign Client: Reopening ticket by id: {}", id);
        return new ResponseEntity<>(ticketRequestService.reopenTicketById(id), HttpStatus.OK);
    }

    @GetMapping("/engineer/{engineerId}/addto/{ticketId}")
    public ResponseEntity<?> assignTicketEngineerById(@PathVariable Long engineerId, @PathVariable Long ticketId) {
        logger.info("Feign Client: Assigning engineer with id: {} to ticket with id: {}", engineerId, ticketId);
        return new ResponseEntity<>(ticketRequestService.assignTicketEngineerById(engineerId, ticketId), HttpStatus.OK);
    }

    @PutMapping("/addMessage/{ticketId}")
    public ResponseEntity<?> addMessageToTicketByTicketId(@PathVariable long ticketId, @RequestBody Object message) {
        logger.info("Feign Client: Adding message to ticket with id: {}", ticketId);
        return new ResponseEntity<>(ticketRequestService.addMessageToTicketByTicketId(ticketId, message),
                HttpStatus.OK);
    }

    @DeleteMapping("/deleteMessage/{ticketId}/{messageId}")
    public ResponseEntity<?> deleteMessageByTicketIdAndMessageId(@PathVariable long ticketId,
            @PathVariable long messageId) {
        logger.info("Feign Client: Deleting message with id: {} from ticket with id: {}", messageId, ticketId);
        return new ResponseEntity<>(ticketRequestService.deleteMessageByTicketIdAndMessageId(ticketId, messageId),
                HttpStatus.OK);
    }

}
