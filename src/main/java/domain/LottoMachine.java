package domain;

import java.util.Map;

public class LottoMachine {
    private final Amount amount;
    private final Tickets tickets;

    public static LottoMachine from(int amount, LottoNumbersGenerator lottoNumbersGenerator) {
        return new LottoMachine(new Amount(amount), lottoNumbersGenerator);
    }

    private LottoMachine(Amount amount, LottoNumbersGenerator lottoNumbersGenerator) {
        this.amount = amount;
        this.tickets = Tickets.of(getTicketCount(), lottoNumbersGenerator);
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
