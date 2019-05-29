package lotto.domain.factory;

import lotto.domain.LottoMoney;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsFactory {
    public static List<LottoTicket> create(final LottoMoney lottoMoney) {
        int amount = lottoMoney.getAmount();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottoTickets.add(LottoTicketFactory.create());
        }
        return lottoTickets;
    }
}
