package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result = new HashMap<>();

    private LottoResult() {
    }

    public static LottoResult create(Lottos lottos, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto: lottos.getLottos()) {
            Rank currentRank = Rank.find(lotto.matchWinningNumbers(winningNumbers), lotto.isNumberMatch(bonusNumber));
            lottoResult.result.put(currentRank, lottoResult.getRankCount(currentRank) + 1);
        }

        return lottoResult;
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
