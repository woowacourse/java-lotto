package lotto.domain.ticket.strategy;

import lotto.domain.ticket.LottoTicket;

import java.util.List;

public interface LottoMachine {
    List<LottoTicket> buyTickets();
}
