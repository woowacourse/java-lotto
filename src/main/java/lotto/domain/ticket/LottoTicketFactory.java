package lotto.domain.ticket;

import lotto.domain.machine.Purchase;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketFactory {
    public static LottoTickets of(Purchase purchaseInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(LottoType.AUTOMATIC.generate(purchaseInformation.getAutoManualNumsInformation().get(LottoType.AUTOMATIC)));
        lottoTickets.addAll(LottoType.MANUAL.generate(purchaseInformation.getAutoManualNumsInformation().get(LottoType.MANUAL), purchaseInformation.getManualNumbers()));
        return new LottoTickets(lottoTickets);
    }
}
