package lotto.domain.factory;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsFactory {

    public static LottoTickets create(Money money) {
        int amount = money.ticketAmount();
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            lottoTickets.add(LottoTicketFactory.create());
        }
        return new LottoTickets(lottoTickets);
    }

}
