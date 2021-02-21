package lotto.domain.machine;

import lotto.domain.ticket.LottoTickets;

public interface LottoMachine {
    LottoTickets createTicketsByMoney(int money);
}
