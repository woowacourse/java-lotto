package lotto.domain.ticket;

import lotto.domain.game.Rank;
import lotto.domain.game.WinningLotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        validateMinimumTicketNumber(lottoTickets);
        this.lottoTickets = lottoTickets;
    }

    private void validateMinimumTicketNumber(final List<LottoTicket> lottoTickets) {
        if (lottoTickets.size() <= 0) {
            throw new IllegalArgumentException("최소 한장의 티켓이 있어야 합니다.");
        }
    }

    public LottoTicket getIdxLottoTicket(int order) {
        return lottoTickets.get(order);
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
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
