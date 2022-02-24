package domain;

import java.util.Map;

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

    public Map<Rank, Integer> getResult(WinningNumbers winningNumbers) {
        return tickets.getResult(winningNumbers);
    }

    public double getYield(WinningNumbers winningNumbers) {
        return tickets.getYield(amount, winningNumbers);
    }

    public Tickets getTickets() {
        return tickets;
    }
}
