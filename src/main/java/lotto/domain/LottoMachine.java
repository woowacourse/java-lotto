package lotto.domain;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.purchaseamount.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static LottoTicketGroup createManualLottos(List<String> manualNumbersText, PurchaseAmount purchaseAmount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        LottoTicket lottoTicket;

        for (String manualNumberText : manualNumbersText) {
            lottoTicket = LottoTicket.create(manualNumberText);
            purchaseAmount.buy(lottoTicket);
            lottoTickets.add(LottoTicket.create(manualNumberText));
        }

        return new LottoTicketGroup(lottoTickets);
    }

    public static LottoTicketGroup createAutoLottos(PurchaseAmount purchaseAmount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        LottoTicket lottoTicket = LottoTicket.create();

        while (purchaseAmount.buy(lottoTicket)) {
            lottoTickets.add(lottoTicket);
            lottoTicket = LottoTicket.create();
        }

        return new LottoTicketGroup(lottoTickets);
    }
}
