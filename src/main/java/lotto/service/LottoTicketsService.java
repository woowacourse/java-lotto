package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.money.Money;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicketsService {

    private LottoTicketsService() {
    }

    public static LottoTickets createLottoTickets(Money money) {
        List<LottoTicket> lottoTicketGroup = Stream.generate(LottoTicketService::createAutoLottoTicket)
                .limit(money.getLottoCount())
                .collect(Collectors.toList());
        return new LottoTickets(lottoTicketGroup);
    }
}
