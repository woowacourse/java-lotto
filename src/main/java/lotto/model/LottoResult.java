package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    Map<Rank, Integer> result;

    public LottoResult() {
        this.result = new HashMap<>();
    }

    public LottoResult generate(Lottos lottos, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        lottos.getLottos().forEach(lotto -> {
            Rank currentRank = match(lotto, winningNumbers, bonusNumber);
            result.put(currentRank, getOrDefault(currentRank) + 1);
        });
        return this;
    }

    public Rank match(Lotto lotto, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        return Rank.find(lotto.matchWinningNumbers(winningNumbers),lotto.matchBonusNumber(bonusNumber));
    }

    public Integer getOrDefault(Rank rank) {
        return result.getOrDefault(rank, 0);
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
