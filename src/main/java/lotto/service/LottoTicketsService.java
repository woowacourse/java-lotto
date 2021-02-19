package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.money.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoTicketsService {

    private LottoTicketsService() {
    }

    public static LottoTickets createLottoTickets(Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        IntStream.range(0, money.getLottoCount())
                .forEach(i -> lottoTicketGroup.add(LottoTicketService.createLottoTicket()));
        return new LottoTickets(lottoTicketGroup);
    }
}
