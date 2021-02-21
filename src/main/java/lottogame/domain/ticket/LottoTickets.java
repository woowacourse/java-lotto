package lottogame.domain.ticket;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public void add(final LottoTicket lottoTicket) {
        this.lottoTickets.add(lottoTicket);
    }

    public List<String> getLottoTickets() {
        List<String> ticketsList = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ticketsList.add(lottoTicket.getLottoNumbers().toString());
        }
        return ticketsList;
    }
}
