package com.fdmgroup.helpdeskapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.helpdeskapi.model.Ticket;
import com.fdmgroup.helpdeskapi.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImpTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImp ticketService;

    Ticket ticket1;

    List<Ticket> tickets;

    @BeforeEach
    void setUp() throws Exception {
        ticket1 = new Ticket();
        ticket1.setTitle("Test Ticket 1");
        tickets = List.of(ticket1);
    }

    @Test
    void testDeleteTicketById() {
        // given
        Long id = 1L;
        // when
        ticketService.deleteTicketById(id);
        // then
        verify(ticketRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAllTickets() {
        // given
        given(ticketRepository.findAll()).willReturn(tickets);
        // when
        assertThat(ticketService.findAllTickets()).isEqualTo(tickets);
        // then
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testFindAllUnassignedTickets() {
        // given
        given(ticketRepository.findTicketsByEngineerIdIsNullAndResolvedFalse()).willReturn(tickets);
        // when
        assertThat(ticketService.findAllUnassignedTickets()).isEqualTo(tickets);
        // then
        verify(ticketRepository, times(1)).findTicketsByEngineerIdIsNullAndResolvedFalse();
    }

    @Test
    void testFindTicketById() {
        // given
        Long id = 1L;
        Optional<Ticket> optionalTicket = Optional.of(ticket1);
        given(ticketRepository.findById(id)).willReturn(optionalTicket);
        // when
        assertThat(ticketService.findTicketById(id)).isEqualTo(optionalTicket.get());
        // then
        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void testFindTicketsByClientId() {
        // given
        Long id = 1L;
        given(ticketRepository.findTicketsByClientId(id)).willReturn(tickets);
        // when
        assertThat(ticketService.findTicketsByClientId(id)).isEqualTo(tickets);
        // then
        verify(ticketRepository, times(1)).findTicketsByClientId(id);
    }

    @Test
    void testFindTicketsByEngineerId() {
        // given
        Long id = 1L;
        given(ticketRepository.findTicketsByEngineerId(id)).willReturn(tickets);
        // when
        assertThat(ticketService.findTicketsByEngineerId(id)).isEqualTo(tickets);
        // then
        verify(ticketRepository, times(1)).findTicketsByEngineerId(id);
    }

    @Test
    void testSaveTicket() {
        // given
        given(ticketRepository.save(ticket1)).willReturn(ticket1);
        // when
        assertThat(ticketService.saveTicket(ticket1)).isEqualTo(ticket1);
        // then
        verify(ticketRepository, times(1)).save(ticket1);
    }
}
