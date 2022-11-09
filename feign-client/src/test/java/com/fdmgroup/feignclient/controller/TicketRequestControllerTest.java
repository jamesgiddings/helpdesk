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
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import com.fdmgroup.feignclient.request.TicketRequestService;

@ExtendWith(MockitoExtension.class)
public class TicketRequestControllerTest {

    @InjectMocks
    private TicketRequestController ticketRequestController;

    @Mock
    TicketRequestService ticketRequestService;

    Object ticket1, ticket2;

    Object message1, message2;

    List<Object> tickets;

    @BeforeEach
    public void setUp() {

        ticket1 = new Object();

        ticket2 = new Object();

        message1 = new Object();

        message2 = new Object();

        tickets = List.of(ticket1, ticket2);
    }

    @Test
    void testAddMessageToTicketByTicketId() {
        // given
        Long ticketId = 1L;
        given(ticketRequestService.addMessageToTicketByTicketId(ticketId, message1)).willReturn(message1);
        MockHttpServletResponse response = new MockHttpServletResponse();
        // when
        ticketRequestController.addMessageToTicketByTicketId(ticketId, message1);
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketRequestService, times(1)).addMessageToTicketByTicketId(ticketId, message1);

    }

    @Test
    void testDeleteMessageByTicketIdAndMessageId() {
        // given
        Long ticketId = 1L;
        Long messageId = 1L;
        // when
        ticketRequestController.deleteMessageByTicketIdAndMessageId(ticketId, messageId);
        // then
        verify(ticketRequestService, times(1)).deleteMessageByTicketIdAndMessageId(ticketId, messageId);

    }

    @Test
    void testDeleteTicketById() {
        // given
        Long id = 1L;
        // when
        ticketRequestController.deleteTicketById(id);
        // then
        verify(ticketRequestService, times(1)).deleteTicketById(id);

    }

    @Test
    void testFindAllTickets() {
        // given
        given(ticketRequestService.findAllTickets()).willReturn(tickets);
        // when
        assertThat(ticketRequestController.findAllTickets().getBody()).isEqualTo(tickets);
        // then
        verify(ticketRequestService, times(1)).findAllTickets();
    }

    @Test
    void testFindAllUnassignedTickets() {
        // given
        given(ticketRequestService.findAllUnassignedTickets()).willReturn(tickets);
        // when
        ticketRequestController.findAllUnassignedTickets();
        // then
        verify(ticketRequestService, times(1)).findAllUnassignedTickets();
    }

    @Test
    void testFindTicketById() {
        // given
        given(ticketRequestService.findTicketById(1)).willReturn(ticket1);
        // when
        ticketRequestController.findTicketById(1);
        // then
        verify(ticketRequestService, times(1)).findTicketById(1);
    }

    @Test
    void testFindTicketsByClientId() {
        // given
        Long id = 1L;
        given(ticketRequestService.findTicketsByClientId(id)).willReturn(ticket1);
        // when
        ticketRequestController.findTicketsByClientId(id);
        // then
        verify(ticketRequestService, times(1)).findTicketsByClientId(id);
    }

    @Test
    void testFindTicketsByEngineerId() {
        // given
        Long id = 1L;
        given(ticketRequestService.findTicketsByEngineerId(id)).willReturn(ticket1);
        // when
        ticketRequestController.findTicketsByEngineerId(id);
        // then
        verify(ticketRequestService, times(1)).findTicketsByEngineerId(id);
    }

    @Test
    void testAssignTicketToByEngineerId() {
        // given
        Long engineerId = 1L;
        Long ticketId = 1L;

        given(ticketRequestService.assignTicketEngineerById(engineerId, ticketId)).willReturn(ticket1);
        // when
        ticketRequestController.assignTicketEngineerById(engineerId, ticketId);
        // then
        verify(ticketRequestService, times(1)).assignTicketEngineerById(engineerId, ticketId);
    }

    @Test
    void testReopenTicketById() {
        // given
        Long id = 1L;
        // when
        ticketRequestController.reopenTicketById(id);
        // then
        verify(ticketRequestService, times(1)).reopenTicketById(id);

    }

    @Test
    void testResolveTicketById() {
        // given
        Long id = 1L;
        // when
        ticketRequestController.resolveTicketById(id);
        // then
        verify(ticketRequestService, times(1)).resolveTicketById(id);
    }

    @Test
    void testSaveTicket() {
        // given
        Object ticket = new Object();
        // when
        ticketRequestController.saveTicket(ticket);
        // then
        verify(ticketRequestService, times(1)).saveTicket(ticket);

    }

    @Test
    void testUpdateTicket() {
        // given
        Object ticket = new Object();
        // when
        ticketRequestController.updateTicket(ticket);
        // then
        verify(ticketRequestService, times(1)).updateTicket(ticket);
    }
}
