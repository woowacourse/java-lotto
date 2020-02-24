package lotto.utils;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private LottoFactory() {
    }

    public static LottoTickets createLottoList(final Payment payment) {
        NumberGenerator randomGenerator = new RandomNumberGenerator();
        List<LottoTicket> lottoTicketList = new ArrayList<>();

        for (int i = 0; i < payment.getLottoCount(); i++) {
            LottoTicket randomLottoTicket = new LottoTicket(randomGenerator.generateNumbers());
            lottoTicketList.add(randomLottoTicket);
        }
        return new LottoTickets(lottoTicketList);
    }
}
