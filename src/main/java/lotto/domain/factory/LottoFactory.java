package lotto.domain.factory;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ManualPurchase;
import lotto.domain.Payment;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private LottoFactory() {
    }

    public static LottoTickets createLottoList(ManualPurchase manualPurchase, Payment payment) {
        NumberGenerator randomGenerator = new RandomNumberGenerator();
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        int alreadyPurchased = manualPurchase.getManualCount();
        int haveToPurchase = payment.getPurchasedCount() - alreadyPurchased;

        for (int i = 0; i < haveToPurchase; i++) {
            LottoTicket randomLottoTicket = new LottoTicket(randomGenerator.generateNumbers());
            lottoTicketList.add(randomLottoTicket);
        }
        manualPurchase.getManualTickets().addAll(lottoTicketList);
        return new LottoTickets(manualPurchase.getManualTickets());
    }
}
