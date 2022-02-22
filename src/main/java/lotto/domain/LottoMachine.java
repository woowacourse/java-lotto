package lotto.domain;

public class LottoMachine {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final Money money;
    private final Tickets tickets;

    public LottoMachine(String moneyInput) {
        this.money = new Money(moneyInput);
        this.tickets = Tickets.buyTicketsByAuto(getTicketCount(money));
    }

    private int getTicketCount(Money money) {
        return money.getMoney() / LOTTO_TICKET_PRICE;
    }
}
