package com.fdmgroup.feignclient.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.feignclient.request.MessageRequestService;

@ExtendWith(MockitoExtension.class)
public class MessageRequestControllerTest {

    @InjectMocks
    private MessageRequestController messageRequestController;

    @Mock
    MessageRequestService messageRequestService;

    Object message1, message2;

    List<Object> messages;

    @BeforeEach
    public void setUp() {
        message1 = new Object();
        message2 = new Object();
        messages = List.of(message1, message2);
    }

    @Test
    void testDeleteMessageById() {
        // given
        Long id = 1L;
        // when
        messageRequestController.deleteMessageById(id);
        // then
        verify(messageRequestService, times(1)).deleteMessageById(id);
    }

    @Test
    void testFindAllMessages() {
        // given
        given(messageRequestService.findAllMessages()).willReturn(messages);
        // when
        assertThat(messageRequestController.findAllMessages().getBody()).isEqualTo(messages);
        // then
        verify(messageRequestService, times(1)).findAllMessages();
    }

    @Test
    void testFindMessageById() {
        // given
        given(messageRequestService.findMessageById(1)).willReturn(message1);
        // when
        messageRequestController.findMessageById(1);
        // then
        verify(messageRequestService, times(1)).findMessageById(1);
    }

    @Test
    void testSaveMessage() {
        // given
        Object message = new Object();
        // when
        messageRequestController.saveMessage(message);
        // then
        verify(messageRequestService, times(1)).saveMessage(message);
    }

    @Test
    void testUpdateMessage() {
        // given
        Object message = new Object();
        // when
        messageRequestController.updateMessage(message);
        // then
        verify(messageRequestService, times(1)).updateMessage(message);
    }
}
