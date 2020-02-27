package lotto.domain.lotto;

import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets createFrom(LottoTickets manual, LottoTickets auto) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(manual.lottoTickets);
        lottoTickets.addAll(auto.lottoTickets);
        return new LottoTickets(lottoTickets);
    }

    public LottoResult getLottoResults(WinningLotto winningLotto) {
        Map<Rank, Integer> rankMap = new HashMap<>();

        Arrays.stream(Rank.values())
                .forEach(rank -> rankMap.put(rank, 0));

        lottoTickets.stream()
                .map(winningLotto::getRank)
                .forEach(rank -> rankMap.computeIfPresent(rank, (key, value) -> value + 1));

        return new LottoResult(rankMap);
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
