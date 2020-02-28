package domain;

import java.util.List;

public class AutoLottoTickets implements Tickets {
    private List<LottoTicket> autoLottoTickets;

    public AutoLottoTickets(List<LottoTicket> autoLottoTickets) {
        this.autoLottoTickets = autoLottoTickets;
    }

    @Override
    public List<LottoTicket> getTickets() {
        return this.autoLottoTickets;
    }
}
