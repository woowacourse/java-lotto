package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> lottoResult;

    public LottoResult(final Map<Rank, Integer> lottoResult) {
        this.lottoResult = new HashMap<>(lottoResult);
    }

    public Money getTotalWinningPrize() {
        Money totalWinningPrize = new Money(0);
        for (Rank rank : Rank.values()) {
            totalWinningPrize.add(getWinningPrize(rank));
        }
        return totalWinningPrize;
    }

    private Money getWinningPrize(Rank rank) {
        Money winningPrize = new Money(0);
        winningPrize.add(rank.getPrizeMoney());
        winningPrize.multiply(lottoResult.get(rank));
        return winningPrize;
    }

    public Map<Rank, Integer> getLottoResult() {
        return Map.copyOf(lottoResult);
    }
}
