package lotto.domain;

import java.util.Map;

import lotto.utils.LottoNumbersGenerator;

public class Lotto {
    private final Amount amount;
    private final Tickets tickets;

    public Lotto(int amount, LottoNumbersGenerator lottoNumbersGenerator) {
        this.amount = new Amount(amount);
        this.tickets = Tickets.of(getAmountDividedByLottoPrice(), lottoNumbersGenerator);
    }

    private int getAmountDividedByLottoPrice() {
        return amount.getAmountDividedByLottoPrice();
    }
    public int getTicketCount() {
        return tickets.getTicketCount();
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
