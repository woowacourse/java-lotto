package lotto.domain;

import java.util.Map;

import lotto.utils.RandomLottoNumbersGenerator;

public class Lotto {
    private final Amount amount;
    private final Tickets tickets;

    public Lotto(int amount) {
        this.amount = new Amount(amount);
        this.tickets = Tickets.of(getTicketCount(), new RandomLottoNumbersGenerator());
    }

    public int getTicketCount() {
        return amount.getAmountDividedByLottoPrice();
    }

    public Map<Rank, Integer> getResult(WinTicket winTicket) {
        return tickets.getResult(winTicket);
    }

    public double getYield(WinTicket winTicket) {
        return tickets.getYield(amount, winTicket);
    }

    public Tickets getTickets() {
        return tickets;
    }
}
