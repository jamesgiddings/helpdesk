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

import com.fdmgroup.feignclient.request.MessageRequestService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gateway/messages")
@RequiredArgsConstructor
public class MessageRequestController {

    Logger logger = LoggerFactory.getLogger(MessageRequestController.class);

    @NonNull
    private MessageRequestService messageRequestService;

    @PostMapping
    ResponseEntity<?> saveMessage(@RequestBody Object message) {
        logger.info("Feign Client: Saving message: {}", message);
        return new ResponseEntity<>(messageRequestService.saveMessage(message), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<?> findAllMessages() {
        logger.info("Feign Client: Finding all messages");
        return new ResponseEntity<>(messageRequestService.findAllMessages(), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<?> updateMessage(@RequestBody Object message) {
        logger.info("Feign Client: Updating message: {}", message);
        return new ResponseEntity<>(messageRequestService.updateMessage(message), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findMessageById(@PathVariable long id) {
        logger.info("Feign Client: Finding message by id: {}", id);
        return new ResponseEntity<>(messageRequestService.findMessageById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMessageById(@PathVariable long id) {
        logger.info("Feign Client: Deleting message by id: {}", id);
        messageRequestService.deleteMessageById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
