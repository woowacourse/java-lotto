package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tickets {
    private final List<Ticket> tickets;

    public Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static Tickets of(int count, LottoNumbersGenerator lottoNumbersGenerator) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(lottoNumbersGenerator));
        }
        return new Tickets(tickets);
    }

    public void add(Tickets add) {
        tickets.addAll(add.tickets);
    }

    public List<Rank> getRanks(WinningNumbers winningNumbers) {
        return tickets.stream()
                .map(winningNumbers::getTicketRank)
                .collect(Collectors.toList());
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
