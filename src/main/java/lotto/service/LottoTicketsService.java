package lotto.service;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsService {

    private LottoTicketsService() {

    }

    public static LottoTickets createManualTickets(List<String> manualLottoTicketInputs, Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (String lottoTicketInput : manualLottoTicketInputs) {
            lottoTicketGroup.add(LottoTicketService.createManualTicket(lottoTicketInput));
        }
        money.deduct(manualLottoTicketInputs.size());
        return new LottoTickets(lottoTicketGroup);
    }

    public static LottoTickets createRandomTickets(Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        int moneyLeftOver = money.ticketCount();
        for (int i = 0; i < moneyLeftOver; i++) {
            lottoTicketGroup.add(LottoTicketService.createTicket(LottoNumber.getRandomNumbers()));
        }
        money.deduct(moneyLeftOver);
        return new LottoTickets(lottoTicketGroup);
    }
}
