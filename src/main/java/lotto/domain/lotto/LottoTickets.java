package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets createFrom(LottoTickets manual, LottoTickets auto) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(manual.lottoTickets);
        lottoTickets.addAll(auto.lottoTickets);
        return new LottoTickets(lottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
