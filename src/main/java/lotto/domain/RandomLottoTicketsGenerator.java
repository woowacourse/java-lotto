package lotto.domain;

import lotto.service.LottoTicketService;

import java.util.ArrayList;
import java.util.List;

public class RandomLottoTicketsGenerator implements LottoTicketsGenerator {
    @Override
    public LottoTickets create(Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        int moneyLeftOver = money.ticketCount();
        for (int i = 0; i < moneyLeftOver; i++) {
            lottoTicketGroup.add(LottoTicketService.createTicket(LottoNumber.getRandomNumbers()));
        }
        money.deduct(moneyLeftOver);
        return new LottoTickets(lottoTicketGroup);
    }
}
