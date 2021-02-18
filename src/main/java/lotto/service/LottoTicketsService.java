package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsService {

    private LottoTicketsService() {

    }

    public static LottoTickets createLottoTickets(Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (int i = 0; i < money.lottoCount(); i++) {
            lottoTicketGroup.add(LottoTicketService.createLottoTicket());
        }
        return new LottoTickets(lottoTicketGroup);
    }
}
