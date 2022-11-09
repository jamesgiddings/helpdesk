package com.fdmgroup.helpdeskapi.controller;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.helpdeskapi.model.Message;
import com.fdmgroup.helpdeskapi.service.MessageService;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    MessageController messageController;

    private MockMvc mockMvc;

    Message message1, message2;

    List<Message> messages;

    private JacksonTester<Message> jsonMessage;

    @BeforeEach
    void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();

        message1 = new Message();
        message1.setBody("Test Message 1");

        message2 = new Message();
        message2.setBody("Test Message 2");

        messages = List.of(message1, message2);
    }

    @Test
    void testDeleteMessageById() {
        // given
        given(messageService.findMessageById(1)).willReturn(message1);
        // when
        messageController.deleteMessageById(1);
        // then
        verify(messageService).findMessageById(1);
        verify(messageService).deleteMessageById(1);
    }

    @Test
    void testDeleteMessageByIdWhenNull() {
        // given
        long id = 1;
        given(messageService.findMessageById(id)).willReturn(null);
        // when
        messageController.deleteMessageById(id);
        // then
        verify(messageService).findMessageById(id);
        verify(messageService, times(0)).deleteMessageById(id);
    }

    @Test
    void testFindAllMessages() throws Exception {
        // given
        given(messageService.findAllMessages()).willReturn(messages);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/messages/")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        for (Message message : messages) {
            assertThat(response.getContentAsString()).contains(message.getBody());
        }
        verify(messageService, times(1)).findAllMessages();
    }

    @Test
    void testFindMessageById() throws Exception {
        // given
        given(messageService.findMessageById(1)).willReturn(message1);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/messages/1")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonMessage.write(message1).getJson());
        verify(messageService, times(2)).findMessageById(1);
    }

    @Test
    void testFindMessageByIdWhenNull() throws Exception {
        // given
        given(messageService.findMessageById(1)).willReturn(null);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/messages/1")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        verify(messageService, times(1)).findMessageById(1);
    }

    @Test
    void testSaveMessage() throws IOException, Exception {
        // given
        lenient().when(messageService.saveMessage(message1)).thenReturn(message1);
        // when
        MockHttpServletResponse response = mockMvc
                .perform(post("/api/v1/messages/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonMessage.write(message1).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void testUpdateMessage() throws IOException, Exception {
        // given
        lenient().when(messageService.saveMessage(message1)).thenReturn(message1);
        given(messageService.findMessageById(message1.getId())).willReturn(message1);
        // when
        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/messages/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonMessage.write(message1).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(messageService).findMessageById(message1.getId());
    }

    @Test
    void testUpdateMessageWhenNull() throws IOException, Exception {
        // given
        given(messageService.findMessageById(message1.getId())).willReturn(null);
        // when
        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/messages/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonMessage.write(message1).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        verify(messageService).findMessageById(message1.getId());
    }
}
