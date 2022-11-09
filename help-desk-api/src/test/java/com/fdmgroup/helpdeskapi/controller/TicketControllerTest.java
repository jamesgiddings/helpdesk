package com.fdmgroup.helpdeskapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.IOException;
import java.util.ArrayList;
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
import com.fdmgroup.helpdeskapi.model.Client;
import com.fdmgroup.helpdeskapi.model.Engineer;
import com.fdmgroup.helpdeskapi.model.Message;
import com.fdmgroup.helpdeskapi.model.Ticket;
import com.fdmgroup.helpdeskapi.service.MessageService;
import com.fdmgroup.helpdeskapi.service.TicketService;
import com.fdmgroup.helpdeskapi.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private MessageService messageService;

    @InjectMocks
    TicketController ticketController;

    private MockMvc mockMvc;

    Ticket ticket1, ticket2, ticket3;

    Message message1, message2;

    Client client1;

    Engineer engineer1;

    List<Ticket> tickets, unassignedTickets;

    private JacksonTester<Ticket> jsonTicket;

    @BeforeEach
    void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();

        client1 = new Client();
        client1.setFullName("Joe Bloggs");
        client1.setEmail("joe@msn");
        client1.setUsername("JBloggs");
        client1.setPassword("password");

        engineer1 = new Engineer();
        engineer1.setFullName("Jane Bloggs");
        engineer1.setEmail("ja@msn");
        engineer1.setUsername("JaBloggs");
        engineer1.setPassword("password");
        engineer1.setSpecialism("Java");

        ticket1 = new Ticket();
        ticket1.setTitle("Test Ticket 1");
        ticket1.setResolved(false);
        ticket1.setClientId(1L);

        ticket2 = new Ticket();
        ticket2.setTitle("Test Ticket 2");
        ticket2.setResolved(false);
        ticket2.setClientId(1L);

        ticket3 = new Ticket();
        ticket3.setTitle("Test Ticket 3");
        ticket3.setResolved(false);
        ticket3.setClientId(1L);
        ticket3.setEngineerId(1L);

        message1 = new Message();
        message1.setBody("Test Message 1");
        message1.setUser(client1);

        message2 = new Message();
        message2.setBody("Test Message 2");
        message2.setUser(client1);

        tickets = List.of(ticket1, ticket2, ticket3);
        unassignedTickets = List.of(ticket1, ticket2);
    }

    @Test
    void testDeleteTicketById() {
        // given
        given(ticketService.findTicketById(1)).willReturn(ticket1);
        // when
        ticketController.deleteTicketById(1L);
        // then
        verify(ticketService).findTicketById(1);
        verify(ticketService).deleteTicketById(1);
    }

    @Test
    void testDeleteTicketByIdWhenIdNull() {
        // given
        given(ticketService.findTicketById(1)).willReturn(null);
        // when
        ticketController.deleteTicketById(1L);
        // then
        verify(ticketService).findTicketById(1);
        verify(ticketService, times(0)).deleteTicketById(1L);
    }

    @Test
    void testFindAllTickets() throws Exception {
        // given
        given(ticketService.findAllTickets()).willReturn(tickets);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/tickets/")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        for (Ticket ticket : tickets) {
            assertThat(response.getContentAsString()).contains(ticket.getTitle());
        }
        verify(ticketService, times(1)).findAllTickets();
    }

    @Test
    void testFindAllUnassignedTickets() throws Exception {
        // given
        given(ticketService.findAllUnassignedTickets()).willReturn(unassignedTickets);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/tickets/unassigned")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        for (Ticket ticket : unassignedTickets) {
            assertThat(response.getContentAsString()).contains(ticket.getTitle());
        }
        verify(ticketService, times(1)).findAllUnassignedTickets();
    }

    @Test
    void testFindTicketById() throws Exception {
        // given
        given(ticketService.findTicketById(1)).willReturn(ticket1);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/tickets/1")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonTicket.write(ticket1).getJson());
        verify(ticketService, times(2)).findTicketById(1);
    }

    @Test
    void testFindTicketByIdWhenNull() throws Exception {
        // given
        given(ticketService.findTicketById(1)).willReturn(null);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/tickets/1")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(ticketService, times(1)).findTicketById(1);
    }

    @Test
    void testFindTicketsByEngineerId() throws Exception {
        // given
        given(ticketService.findTicketsByEngineerId(1L)).willReturn(tickets);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/tickets/engineer/1")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketService, times(1)).findTicketsByEngineerId(1L);
    }

    @Test
    void testFindTicketsByClientId() throws Exception {
        // given
        given(ticketService.findTicketsByClientId(1L)).willReturn(tickets);
        // when
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/tickets/client/1")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketService, times(1)).findTicketsByClientId(1L);
    }

    @Test
    void testSaveTicket() throws IOException, Exception {
        // given
        given(ticketService.saveTicket(ticket1)).willReturn(ticket1);
        // when
        MockHttpServletResponse response = mockMvc
                .perform(post("/api/v1/tickets/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTicket.write(ticket1).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(ticketService).saveTicket(ticket1);
    }

    @Test
    void testResolveTicketById() throws IOException, Exception {
        // given
        ticket3.setResolved(false); // just to be sure
        Long id = ticket3.getId();
        given(ticketService.findTicketById(ticket3.getId())).willReturn(ticket3);
        // when
        MockHttpServletResponse httpResponse = mockMvc.perform(get("/api/v1/tickets/resolve/" + id)).andReturn()
                .getResponse();
        MockHttpServletResponse objectResponse = mockMvc.perform(get("/api/v1/tickets/" + id)).andReturn()
                .getResponse();
        Ticket actualTicket = jsonTicket.parseObject(objectResponse.getContentAsString());
        // then
        assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualTicket.isResolved()).isTrue();

        verify(ticketService, times(3)).findTicketById(id);
    }

    @Test
    void testReopenTicketById() throws IOException, Exception {
        // given
        ticket3.setResolved(true);
        Long id = ticket3.getId();
        given(ticketService.findTicketById(ticket3.getId())).willReturn(ticket3);
        // when
        MockHttpServletResponse httpResponse = mockMvc.perform(get("/api/v1/tickets/reopen/" + id)).andReturn()
                .getResponse();
        MockHttpServletResponse objectResponse = mockMvc.perform(get("/api/v1/tickets/" + id)).andReturn()
                .getResponse();
        Ticket actualTicket = jsonTicket.parseObject(objectResponse.getContentAsString());
        // then
        assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualTicket.isResolved()).isFalse();
        verify(ticketService, times(3)).findTicketById(id);
    }

    @Test
    void testReopenTicketByIdWhenNull() throws IOException, Exception {
        // given
        ticket3.setResolved(true);
        Long id = ticket3.getId();
        given(ticketService.findTicketById(ticket3.getId())).willReturn(null);
        // when
        MockHttpServletResponse httpResponse = mockMvc.perform(get("/api/v1/tickets/reopen/" + id)).andReturn()
                .getResponse();
        // then
        assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(ticketService, times(1)).findTicketById(id);
    }

    @Test
    void testResolveTicketByIdWhenNull() throws IOException, Exception {
        // given
        ticket3.setResolved(false);
        Long id = ticket3.getId();
        given(ticketService.findTicketById(ticket3.getId())).willReturn(null);
        // when
        MockHttpServletResponse httpResponse = mockMvc.perform(get("/api/v1/tickets/resolve/" + id)).andReturn()
                .getResponse();
        // then
        assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        verify(ticketService, times(1)).findTicketById(id);
    }

    @Test
    void testUpdateTicket() throws IOException, Exception {
        // given
        given(ticketService.saveTicket(ticket1)).willReturn(ticket1);
        given(ticketService.findTicketById(ticket1.getId())).willReturn(ticket1);
        // when
        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/tickets/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTicket.write(ticket1).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketService).findTicketById(ticket1.getId());
        verify(ticketService).saveTicket(ticket1);
    }

    @Test
    void testUpdateTicketWhenTickeNull() throws IOException, Exception {
        // given
        given(ticketService.findTicketById(ticket1.getId())).willReturn(null);
        // when
        MockHttpServletResponse response = mockMvc
                .perform(put("/api/v1/tickets/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTicket.write(ticket1).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        verify(ticketService).findTicketById(ticket1.getId());
        verify(ticketService, times(0)).saveTicket(ticket1);
    }

    @Test
    void testAddMessageToTicketByTicketId() throws IOException, Exception {
        // given
        Long id = ticket1.getId();
        ticket1.setMessages(new ArrayList<>());
        ticket1.addMessage(message1);
        given(ticketService.saveTicket(ticket1)).willReturn(ticket1);
        given(userService.findUserById(client1.getId())).willReturn(client1);
        given(ticketService.findTicketById(id)).willReturn(ticket1);
        // when
        ticketController.addMessageToTicketByTicketId(id, message1);
        // then
        verify(ticketService, times(2)).findTicketById(ticket1.getId());
        verify(userService).findUserById(client1.getId());
        verify(ticketService).saveTicket(ticket1);
    }

    @Test
    void testAddMessageToTicketByTicketIdWhenNull() throws IOException, Exception {
        // given
        Long id = ticket1.getId();
        given(ticketService.findTicketById(id)).willReturn(null);
        // when
        ticketController.addMessageToTicketByTicketId(id, message1);
        // then
        verify(ticketService, times(1)).findTicketById(ticket1.getId());
        verify(userService, times(0)).findUserById(client1.getId());
        verify(ticketService, times(0)).saveTicket(ticket1);
    }

    @Test
    void testDeleteMessageFromTicketByTicketId() throws IOException, Exception {
        // given
        Long id = ticket1.getId();
        Long messageId = message1.getId();
        ticket1.setMessages(new ArrayList<>());
        ticket1.addMessage(message1);
        given(ticketService.findTicketById(id)).willReturn(ticket1);
        given(messageService.findMessageById(messageId)).willReturn(message1);
        // when
        ticketController.deleteMessageByTicketIdAndMessageId(id, messageId);
        // then
        verify(ticketService, times(2)).findTicketById(ticket1.getId());
        verify(messageService, times(2)).findMessageById(messageId);
        verify(ticketService).saveTicket(ticket1);
    }

    @Test
    void testDeleteMessageFromTicketByTicketIdWhenNull() throws IOException, Exception {
        // given
        Long id = ticket1.getId();
        Long messageId = message1.getId();
        given(ticketService.findTicketById(id)).willReturn(null);
        // when
        ticketController.deleteMessageByTicketIdAndMessageId(id, messageId);
        // then
        verify(ticketService, times(1)).findTicketById(ticket1.getId());
        verify(userService, times(0)).findUserById(client1.getId());
        verify(ticketService, times(0)).saveTicket(ticket1);
    }

    @Test
    void testAssignTicketToEngineerById() throws IOException, Exception {
        // given
        Long id = ticket1.getId();
        ticket1.setMessages(new ArrayList<>());
        ticket1.addMessage(message1);
        given(ticketService.saveTicket(ticket1)).willReturn(ticket1);
        // given(userService.findUserById(engineer1.getId())).willReturn(engineer1);
        given(ticketService.findTicketById(id)).willReturn(ticket1);
        // when
        ticketController.assignTicketEngineerById(engineer1.getId(), ticket1.getId());
        // then
        verify(ticketService, times(1)).findTicketById(ticket1.getId());
        // verify(userService).findUserById(engineer1.getId());
        verify(ticketService).saveTicket(ticket1);
    }

    @Test
    void testAssignTicketToEngineerByIdWhenNull() throws IOException, Exception {
        // given
        Long id = ticket1.getId();
        given(ticketService.findTicketById(id)).willReturn(null);
        // when
        ticketController.assignTicketEngineerById(engineer1.getId(), ticket1.getId());
        // then
        verify(ticketService, times(1)).findTicketById(ticket1.getId());
        verify(userService, times(0)).findUserById(client1.getId());
        verify(ticketService, times(0)).saveTicket(ticket1);
    }
}
