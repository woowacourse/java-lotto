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

    public List<Integer> checkHitCount(LottoTicket winningTicket) {
        List<Integer> winningCount = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            winningCount.add(lottoTicket.compareNumbers(winningTicket));
        }
        return winningCount;
    }

    public List<LottoTicket> toList() {
        return new ArrayList<>(lottoTickets);
    }

    public void makeTicketByCount(int counts) {
        for (int i = 0; i < counts; i++) {
            lottoTickets.add(RandomTicketFactory.makeTicket());
        }
    }
}
