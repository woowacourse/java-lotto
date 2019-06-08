package lotto.domain.ticket;

import lotto.domain.game.Rank;
import lotto.domain.game.WinningLotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoTicket getIdxLottoTicket(int order) {
        return lottoTickets.get(order);
    }

    public int lottoTicketsSize() {
        return lottoTickets.size();
    }

    public List<Rank> getTicketsRank(WinningLotto winningLotto) {
        List<Rank> ranks = lottoTickets.stream()
                .map(winningLotto::getRank)
                .collect(Collectors.toList());
        return ranks;
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
                .map(LottoTicket::toString)
                .collect(Collectors.joining("\n"));
    }
}
