package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public final List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public LottoTickets addTickets(List<LottoTicket> manualTickets) {
        manualTickets.addAll(lottoTickets);
        lottoTickets = manualTickets;
        return this;
    }
}
