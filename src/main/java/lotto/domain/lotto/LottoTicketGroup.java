package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LottoTicketGroup implements Iterable<LottoTicket> {
    private final List<LottoTicket> lottoTickets;

    public LottoTicketGroup(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public int size() {
        return lottoTickets.size();
    }

    public LottoTicketGroup combine(LottoTicketGroup lottos) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(this.lottoTickets);
        lottoTickets.addAll(lottos.lottoTickets);

        return new LottoTicketGroup(lottoTickets);
    }

    @Override
    public Iterator<LottoTicket> iterator() {
        return lottoTickets.iterator();
    }
}
