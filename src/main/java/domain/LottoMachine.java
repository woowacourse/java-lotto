package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final static int PRICE = 1000;

    public static LottoTickets buy(Money money) {
        List<LottoTicket> lottoTicket = new ArrayList<>();
        for (int i = 0; i < money.toLong() / PRICE; i++) {
            lottoTicket.add(new LottoTicket(LottoNumberRepository.shuffleLottoNumbers()));
        }
        return new LottoTickets(lottoTicket);
    }
}
