package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<LottoTicket> toList() {
        return new ArrayList<>(lottoTickets);
    }

    public void addManuallyCreatedTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets.addAll(lottoTickets);
    }

    public void generateTicketAutomatically(int counts) {
        for (int i = 0; i < counts; i++) {
            lottoTickets.add(RandomNumberTicketFactory.makeTicket());
        }
    }
}
