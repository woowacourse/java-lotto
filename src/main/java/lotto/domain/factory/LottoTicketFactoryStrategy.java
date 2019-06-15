package lotto.domain.factory;

import lotto.domain.LottoTicket;


public interface LottoTicketFactoryStrategy {
    LottoTicket makeLotto();
}
