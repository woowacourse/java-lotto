package lotto.domain.strategy;

import lotto.domain.ticket.LottoTickets;

public interface GenerateStrategy {
    LottoTickets generateTickets();
}
