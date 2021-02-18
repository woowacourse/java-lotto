package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private final static int PRICE = 1000;

    public static LottoTickets buy(Money money) {
        List<LottoTicket> lottoTicket = new ArrayList<>();
        for (int i = 0; i < money.toInteger() / PRICE; i++) {
            lottoTicket.add(LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()));
        }
        return new LottoTickets(lottoTicket);
    }
}
