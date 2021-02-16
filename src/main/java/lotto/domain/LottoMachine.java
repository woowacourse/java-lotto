package lotto.domain;

import java.util.List;

public interface LottoMachine {
    List<LottoTicket> createTickets(int numberOfTickets);
}
