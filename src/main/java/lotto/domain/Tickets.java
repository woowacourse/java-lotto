package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tickets {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final List<Ticket> tickets;

    private Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static Tickets buyTicketsByAuto(Money money) {
        int ticketCount = getTicketCount(money);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Ticket(LottoNumber.getRandomLottoNumbers()));
        }
        return new Tickets(tickets);
    }

    private static int getTicketCount(Money money) {
        return money.getMoney() / LOTTO_TICKET_PRICE;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
