package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LottoTicketGroup implements Iterable<LottoTicket> {
    private final List<LottoTicket> lottoTickets;

    LottoTicketGroup(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    @Override
    public Iterator<LottoTicket> iterator() {
        return lottoTickets.iterator();
    }
}
