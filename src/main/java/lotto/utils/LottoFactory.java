package lotto.utils;

import lotto.domain.LottoTicket;
import lotto.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static List<LottoTicket> createLottoList(final Payment payment) {
        NumberGenerator randomGenerator = new RandomNumberGenerator();
        List<LottoTicket> lottoTicketList = new ArrayList<>();

        for (int i = 0; i < payment.getLottoCount(); i++) {
            lottoTicketList.add(new LottoTicket(randomGenerator
                    .generateNumbers()));
        }
        return lottoTicketList;
    }
}
