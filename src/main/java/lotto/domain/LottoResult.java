package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private static final Map<Rank, Integer> result = new LinkedHashMap<>();

    private final LottoTicket lottoTicket;
    private final WinningLotto winningLotto;

    public LottoResult(LottoTicket lottoTicket, WinningLotto winningLotto) {
        this.lottoTicket = lottoTicket;
        this.winningLotto = winningLotto;
        init();
    }

    private void init() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    public Map<Rank, Integer> matchLotto() {
        lottoTicket.matchLotto(winningLotto)
                .stream()
                .forEach(lotto -> putRank(lotto));

        return result;
    }

    private void putRank(Rank rank) {
        result.put(rank, result.get(rank) + 1);
    }
}
