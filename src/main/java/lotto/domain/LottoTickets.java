package lotto.domain;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public static LottoTickets createManualLottoTickets(List<List<Integer>> manualNumbers) {
        List<LottoTicket> lottoTickets = manualNumbers.stream()
                .map(LottoTicket::new)
                .collect(toList());

        return new LottoTickets(lottoTickets);
    }

    public LottoTickets combine(LottoTickets targetLottoTickets) {
        List<LottoTicket> joined = new ArrayList<>(lottoTickets);
        joined.addAll(targetLottoTickets.lottoTickets);

        return new LottoTickets(joined);
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
