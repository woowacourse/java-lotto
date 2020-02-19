package domain.lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {

    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<Rank> getLottoResults(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::getRank)
                .collect(Collectors.toList());
    }
}
