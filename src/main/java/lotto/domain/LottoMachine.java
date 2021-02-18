package lotto.domain;

import java.util.List;

public interface LottoMachine {
    LottoTickets createTickets(int numberOfTickets);
    LottoTickets createTicketsByMoney(int money);
}
