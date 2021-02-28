package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsService {

    private LottoTicketsService() {

    }

    public static LottoTickets createManualLottoTickets(Money money, int manualTicketsCount) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (int i = 0; i < manualTicketsCount; i++) {
            lottoTicketGroup.add(LottoTicketService.createManualLottoTicket(InputView.getUserInput()));
        }
        money.deductMoney(manualTicketsCount);
        return new LottoTickets(lottoTicketGroup);
    }

    public static LottoTickets createAutoLottoTickets(Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        int moneyLeftOver = money.lottoCount();
        for (int i = 0; i < moneyLeftOver; i++) {
            lottoTicketGroup.add(LottoTicketService.createAutoLottoTicket());
        }
        money.deductMoney(moneyLeftOver);
        return new LottoTickets(lottoTicketGroup);
    }
}
