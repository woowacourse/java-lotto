package service;

import domain.Ticket;

public class LottoService {

    public Ticket createTicket(int price) {
        return Ticket.create(price);
    }
}
