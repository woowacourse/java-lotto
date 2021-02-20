package lotto.domain;

public interface LottoMachine {
    LottoTickets createTickets(int numberOfTickets);

    LottoTickets createTicketsByMoney(int money);
}
