package lotto.domain.Factory;

import lotto.domain.LottoTicket;

import java.util.List;

public interface LottoTicketsFactoryStrategy {
    List<LottoTicket> purchaseLottoTickets();
}
