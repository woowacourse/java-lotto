package lotto.domain.lotto;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LottoTicketGroup implements Iterable<LottoTicket> {
    private final List<LottoTicket> lottoTickets;

    public LottoTicketGroup(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    @Override
    public Iterator<LottoTicket> iterator() {
        return lottoTickets.iterator();
    }

    public int size() {
        return lottoTickets.size();
    }

    public LottoTicketGroup combine(LottoTicketGroup lottos) {
        lottoTickets.addAll(lottos.lottoTickets);
        return this;
    }
}
