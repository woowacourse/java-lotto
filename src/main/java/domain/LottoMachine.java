package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final static int PRICE = 1000;

    public static List<LottoTicket> buy(Money money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < money.toLong() / PRICE; i++) {
            lottoTickets.add(new LottoTicket(LottoNumberRepository.shuffleLottoNumbers()));
        }
        return lottoTickets;
    }
}
