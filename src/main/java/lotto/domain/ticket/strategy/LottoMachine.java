package lotto.domain.ticket.strategy;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.manual.ManualNumber;

import java.util.List;

public interface LottoMachine {
    List<LottoTicket> buyTickets(int ticketCount, List<ManualNumber> numbers);
}
