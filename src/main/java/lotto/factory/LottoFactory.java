package lotto.factory;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;
import lotto.generator.NumberGenerator;
import lotto.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private LottoFactory() {
    }

    public static LottoTickets createLottoList(final int manualPurchaseCount, final Payment payment) {
        NumberGenerator randomGenerator = new RandomNumberGenerator();
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        int alreadyPurchased = manualPurchaseCount;
        int haveToPurchase = payment.getPurchasedCount() - alreadyPurchased;

        for (int i = 0; i < haveToPurchase; i++) {
            LottoTicket randomLottoTicket = new LottoTicket(randomGenerator.generateNumbers());
            lottoTicketList.add(randomLottoTicket);
        }
        return new LottoTickets(lottoTicketList);
    }
}
