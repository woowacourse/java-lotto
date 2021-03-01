package domain.lotto;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        ArrayList<LottoTicket> copy = new ArrayList<>(this.lottoTickets);
        return Collections.unmodifiableList(copy);
    }

    public List<LottoRank> findLottoRanks(final WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(lottoTicket -> lottoTicket.findLottoRankByWinningLotto(winningLotto))
                .collect(Collectors.toList());
    }
}
