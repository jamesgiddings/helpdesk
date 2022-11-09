package com.fdmgroup.feignclient.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "message-service", path = "/api/v1/messages", url = "${helpdeskapi.server.url}")
public interface MessageRequestService {

    @PostMapping
    Object saveMessage(@RequestBody Object message);

    @GetMapping
    List<Object> findAllMessages();

    @PutMapping
    Object updateMessage(Object message);

    @GetMapping("/{id}")
    Object findMessageById(@PathVariable long id);

    @DeleteMapping("/{id}")
    void deleteMessageById(@PathVariable long id);
}
