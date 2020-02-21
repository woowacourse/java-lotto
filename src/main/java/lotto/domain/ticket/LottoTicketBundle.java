package lotto.domain.ticket;

import lotto.domain.result.MatchResultBundle;
import lotto.domain.result.win.WinningLotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicketBundle {
    private final List<LottoTicket> lottoTickets;

    public LottoTicketBundle(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public MatchResultBundle getMatchResultBundle(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::getResult)
                .collect(Collectors.collectingAndThen(Collectors.toList(), MatchResultBundle::new));
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(this.lottoTickets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicketBundle that = (LottoTicketBundle) o;
        return Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets);
    }
}
