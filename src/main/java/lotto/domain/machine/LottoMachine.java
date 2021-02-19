package lotto.domain.machine;

import lotto.domain.ticket.LottoTickets;

public interface LottoMachine {
    LottoTickets createTickets(int numberOfTickets);

    LottoTickets createTicketsByMoney(int money);
}
