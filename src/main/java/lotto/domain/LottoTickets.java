package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }

    public int getLottoTicketsCount() {
        return lottoTickets.size();
    }

    public void addLottoTicket(Lotto lottoTicket) {
        lottoTickets.add(lottoTicket);
    }
}
