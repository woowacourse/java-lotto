package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public LottoResult checkPrizesByWinningTickets(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::matchPrize)
                .collect(collectingAndThen(toList(), LottoResult::new));
    }
}
