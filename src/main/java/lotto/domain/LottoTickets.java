package lotto.domain;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<List<LottoNumber>> manualNumbers) {
        this.lottoTickets = manualNumbers.stream()
                .map(LottoTicket::new)
                .collect(toList());
    }

    public void combine(LottoTickets targetLottoTickets) {
        lottoTickets.addAll(targetLottoTickets.lottoTickets);
    }

    public LottoResult determine(WinningNumber winningNumber) {
        Map<Rank, Long> ranks = lottoTickets.stream()
                .map(winningNumber::compare)
                .collect(groupingBy(identity(), counting()));

        return new LottoResult(ranks);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
