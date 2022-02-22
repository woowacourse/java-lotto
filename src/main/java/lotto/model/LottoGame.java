package lotto.model;

import java.util.List;

public class LottoGame {

    private final LottoMatcher lottoMatcher;

    public LottoGame(List<Integer> winningNumbers, Integer bonusNumber) {
        this.lottoMatcher = new LottoMatcher(winningNumbers, bonusNumber);
    }

    public float calculateYield(int money, List<Lotto> lottos) {
        ResultMap winningResult = lottoMatcher.getWinningResult(lottos);
        return getWinningAmount(winningResult) / (float) money;
    }

    private Long getWinningAmount(ResultMap rank) {
        return rank.getResult().entrySet().stream()
                .map(entry -> entry.getKey().getMoney() * entry.getValue())
                .mapToLong(i -> i)
                .sum();
    }
}
