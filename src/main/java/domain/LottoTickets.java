package domain;

import java.util.List;

public class LottoTickets implements Tickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    @Override
    public List<LottoTicket> getTickets() {
        return this.lottoTickets;
    }
}
