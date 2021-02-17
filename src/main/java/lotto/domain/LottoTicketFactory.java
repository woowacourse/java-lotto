package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketFactory {
    private final List<Integer> lottoNumberRange;

    public LottoTicketFactory() {
        this.lottoNumberRange = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            this.lottoNumberRange.add(i);
        }
    }

    public LottoTickets buyLottoTickets(Money money) {
        int length = money.getValue() / 1000;
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            lottoTickets.add(new LottoTicket(lottoNumberRange));
        }
        return new LottoTickets(lottoTickets);
    }
}
