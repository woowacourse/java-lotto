package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    public static final int TICKET_PRICE = 1000;

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

    public int getCostUsedToBuy() {
        return lottoTickets.size() * TICKET_PRICE;
    }
}
