package strategy;

import domain.numberscontainer.Ticket;

public interface TicketFactory {

    Ticket createTicket();
}