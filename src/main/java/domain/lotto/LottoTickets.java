package domain.lotto;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        ArrayList<LottoTicket> copy = new ArrayList<>(this.lottoTickets);
        return Collections.unmodifiableList(copy);
    }

    public List<LottoRank> findMatches(WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = new ArrayList<>();
        lottoTickets.stream()
                .map(lotto -> lotto.findMatchesNumber(winningLotto))
                .forEach(match -> {
                    lottoRanks.add(match);
                });
        return lottoRanks;
    }
}
