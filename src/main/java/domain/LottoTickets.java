package domain;

import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> LottoTickets;

    public LottoTickets(List<LottoTicket> LottoTickets) {
        this.LottoTickets = LottoTickets;
    }

    public int getSize() {
        return LottoTickets.size();
    }

    public List<LottoTicket> getLottoTickets() {
        return LottoTickets;
    }
}
