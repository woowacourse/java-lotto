package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    List<LottoTicket> lottoTickets = new ArrayList<>();

    public void add(final LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public List<LottoTicket> toList() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getTicketsCount() {
        return lottoTickets.size();
    }
}
