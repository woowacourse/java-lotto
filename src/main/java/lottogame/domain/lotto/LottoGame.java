package lottogame.domain.lotto;

import lottogame.domain.stats.LottoResults;
import lottogame.domain.stats.Money;
import lottogame.domain.stats.Rank;
import lottogame.domain.stats.Yield;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class LottoGame {
    private Lottos lottos;

    public LottoGame(Lottos lottos) {
        this.lottos = lottos;
    }

    public LottoResults results(WinningLotto winningLotto, Money money) {
        Map<Rank, Integer> results = lottos.matchLottos(winningLotto);
        Yield yield = Yield.of(totalPrizeMoney(results), money);
        return new LottoResults(results, yield);
    }

    public Money totalPrizeMoney(Map<Rank, Integer> results) {
        int prizeMoney = Arrays.stream(Rank.values())
                .filter(Rank::isFound)
                .mapToInt(rank -> results.get(rank) * rank.getMoney())
                .sum();
        return Money.of(prizeMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoGame lottoGame = (LottoGame) o;
        return Objects.equals(lottos, lottoGame.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
