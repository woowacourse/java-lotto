package lotto.domain.ticket.generator;

import lotto.domain.ticket.LottoTicket;

import java.util.List;

public interface Generator {
    List<LottoTicket> generateTickets();
}
