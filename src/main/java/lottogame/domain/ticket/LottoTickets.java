package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public void add(final LottoTicket lottoTicket) {
        this.lottoTickets.add(lottoTicket);
    }

    public List<LottoTicket> toList() {
        return Collections.unmodifiableList(this.lottoTickets);
    }

    public int getTicketsCount() {
        return this.lottoTickets.size();
    }
}
