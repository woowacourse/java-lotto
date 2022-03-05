package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> lottoResult;

    public LottoResult(final Map<Rank, Integer> lottoResult) {
        this.lottoResult = new HashMap<>(lottoResult);
    }

    public Money calculateTotalWinningPrize() {
        Money totalWinningPrize = new Money();
        for (Rank rank : Rank.values()) {
            totalWinningPrize.add(calculateWinningPrize(rank));
        }
        return totalWinningPrize;
    }

    private Money calculateWinningPrize(Rank rank) {
        Money winningPrize = new Money();
        winningPrize.add(rank.getPrizeMoney());
        winningPrize.multiply(lottoResult.get(rank));
        return winningPrize;
    }

    public Map<Rank, Integer> getLottoResult() {
        return Map.copyOf(lottoResult);
    }
}
