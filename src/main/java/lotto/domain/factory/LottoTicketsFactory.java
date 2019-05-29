package lotto.domain.factory;

import lotto.domain.LottoMoney;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsFactory {
    public static LottoTickets create(LottoMoney lottoMoney) {
        int amount = lottoMoney.getAmount();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottoTickets.add(LottoTicketFactory.create());
        }
        return new LottoTickets(lottoTickets);
    }
}
