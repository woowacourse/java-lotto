package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> results = new EnumMap<>(Rank.class);

    public LottoResult(LottoTickets lottoTickets, LottoTicket winningLotto) {
        initResults();

        for (int index = 0; index < lottoTickets.size(); index++) {
            LottoTicket lottoTicket = lottoTickets.getTicket(index);
            Rank rank = calculateRankWith(lottoTicket, winningLotto);
            results.put(rank, results.get(rank) + 1);
        }
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    private Rank calculateRankWith(LottoTicket lottoTicket, LottoTicket winningLotto) {
        int numOfMatching = lottoTicket.match(winningLotto);
        return Rank.valueOf(numOfMatching);
    }
}
