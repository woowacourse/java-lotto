package lotto.lottoticket;

import lotto.lottogame.LottoCount;

import java.util.ArrayList;
import java.util.List;

public class Tickets {
    private final List<Ticket> tickets;

    public Tickets(LottoCount lottoCount, NumbersGenerator numbersGenerator) {
        this.tickets = new ArrayList<>();
        while (lottoCount.isGreaterThanZero()) {
            lottoCount = lottoCount.decreaseOne();
            tickets.add(new Ticket(numbersGenerator));
        }
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }
}
