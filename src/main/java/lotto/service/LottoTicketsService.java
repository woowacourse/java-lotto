package lotto.service;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsService {

    private LottoTicketsService() {

    }

    public static LottoTickets createManualLottoTickets(List<String> manualLottoTicketInputs, Money money) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        for (String lottoTicketInput : manualLottoTicketInputs) {
            lottoTicketGroup.add(LottoTicketService.createManualLottoTicket(lottoTicketInput));
        }
        money.deductMoney(manualLottoTicketInputs.size());
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
