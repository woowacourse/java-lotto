package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

public class LottoTickets {

    private List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets publishLottoTickets(List<Set<Integer>> lottoTicketsNumbers) {
        List<LottoTicket> lottoTickets = lottoTicketsNumbers.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    public static LottoTickets createFrom(LottoTickets manual, LottoTickets auto) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(manual.lottoTickets);
        lottoTickets.addAll(auto.lottoTickets);
        return new LottoTickets(lottoTickets);
    }

    public LottoResult getLottoResults(WinningLotto winningLotto) {
        Map<Rank, Integer> rankToCount = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(Function.identity(), rank -> 0));

        lottoTickets.stream()
                .map(winningLotto::checkOutRank)
                .forEach(rank -> rankToCount.computeIfPresent(rank, (key, value) -> value + 1));

        return new LottoResult(rankToCount);
    }

    public List<String> getLottoTickets() {
        return lottoTickets.stream()
                .map(LottoTicket::toString)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoTickets that = (LottoTickets)o;
        return Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets);
    }
}
