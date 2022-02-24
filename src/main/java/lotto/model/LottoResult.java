package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result = new HashMap<>();

    private LottoResult() {
    }

    public static LottoResult create(Lottos lottos, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        LottoResult lottoResult = new LottoResult();
        lottos.getLottos().forEach(lotto -> {
            Rank currentRank = lottoResult.match(lotto, winningNumbers, bonusNumber);
            lottoResult.result.put(currentRank, lottoResult.getRankCount(currentRank) + 1);
        });
        return lottoResult;
    }

    private Rank match(Lotto lotto, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        return Rank.find(lotto.matchWinningNumbers(winningNumbers),lotto.matchNumber(bonusNumber));
    }

    public Integer getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0);
    }

    public Long getTotalWinningMoney() {
        return result.entrySet().stream()
                .map(entry -> entry.getKey().getMoney() * entry.getValue())
                .mapToLong(i -> i)
                .sum();
    }
}
