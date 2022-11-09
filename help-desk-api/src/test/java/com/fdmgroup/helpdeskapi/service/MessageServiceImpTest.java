package com.fdmgroup.helpdeskapi.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.fdmgroup.helpdeskapi.model.Message;
import com.fdmgroup.helpdeskapi.repository.MessageRepository;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImpTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImp messageService;

    Message message1, message2;

    List<Message> messages;

    @BeforeEach
    void setUp() throws Exception {
        message1 = new Message();
        message2 = new Message();
        messages = List.of(message1, message2);
    }

    @Test
    void testDeleteMessageById() {
        // given
        Long id = 1L;
        // when
        messageService.deleteMessageById(id);
        // then
        verify(messageRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAllMessages() {
        // given
        given(messageRepository.findAll()).willReturn(messages);
        // when
        assertThat(messageService.findAllMessages()).isEqualTo(messages);
        // then
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void testFindMessageById() {
        // given
        Long id = 1L;
        given(messageRepository.findById(id)).willReturn(java.util.Optional.of(message1));
        // when
        assertThat(messageService.findMessageById(id)).isEqualTo(message1);
        // then
        verify(messageRepository, times(1)).findById(id);
    }

    @Test
    void testSaveMessage() {
        // given
        given(messageRepository.save(message1)).willReturn(message1);
        // when
        Message actual = messageService.saveMessage(message1);
        // then
        assertThat(actual).isNotNull();
        verify(messageRepository, times(1)).save(message1);
    }
}
