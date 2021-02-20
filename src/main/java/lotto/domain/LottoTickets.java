package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public LottoResult checkPrizesByWinningTickets(WinningLotto winningLotto) {
        List<Prize> winningTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : this.lottoTickets) {
            Prize prize = winningLotto.matchPrize(lottoTicket);
            winningTickets.add(prize);
        }
        return new LottoResult(winningTickets);
    }
}
