package domain;

import java.util.List;

public class ManualLottoTickets implements Tickets {
    List<LottoTicket> manualLottoTickets;

    public ManualLottoTickets(List<LottoTicket> manualLottoTickets) {
        this.manualLottoTickets = manualLottoTickets;
    }

    @Override
    public List<LottoTicket> getTickets() {
        return manualLottoTickets;
    }
}
