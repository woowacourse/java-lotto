package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public LottoTickets createLottoTickets(int buyingQuantity) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i=0; i<buyingQuantity; i++) {
            lottoTickets.add(LottoTicket.create());
        }
        return new LottoTickets(lottoTickets);
    }
}
