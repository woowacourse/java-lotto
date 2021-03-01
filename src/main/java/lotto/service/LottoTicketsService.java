package lotto.service;

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
}
