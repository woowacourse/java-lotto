package domain;

import java.util.Map;

public class Lotto {
    private final Amount amount;
    private final Tickets tickets;

    public static Lotto from(int amount) {
        return new Lotto(new Amount(amount));
    }

    private Lotto(Amount amount) {
        this.amount = amount;
        this.tickets = Tickets.of(getTicketCount(), new RandomLottoNumbersGenerator());
    }

    public int getTicketCount() {
        return amount.getAmountDividedByLottoPrice();
    }

    public Tickets getTickets() {
        return tickets;
    }

    public Map<Rank, Integer> getResult(WinningNumbers winningNumbers) {
        return tickets.getResult(winningNumbers);
    }

    public double getYield(WinningNumbers winningNumbers) {
        return tickets.getYield(amount, winningNumbers);
    }

}
