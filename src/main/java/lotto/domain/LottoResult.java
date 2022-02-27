package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_COUNT = 0;
    private static final int CHECK_BONUS_COUNT = 5;

    private final Map<Rank, Integer> lottoResult = new HashMap<>();

    public LottoResult() {
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, DEFAULT_COUNT);
        }
    }

    public Map<Rank, Integer> getLottoResult() {
        return lottoResult;
    }

    public void match(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = lotto.compareTo(winningLotto.getWinningNumbers());
            boolean bonus = matchingCount == CHECK_BONUS_COUNT && lotto.contains(winningLotto.getBonusBall());
            increaseRankCount(Rank.getRank(matchingCount, bonus));
        }
    }

    private void increaseRankCount(Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank) + 1);
    }

    public int getTotalMoney() {
        int totalMoney = 0;
        for (Rank rank : Rank.values()) {
            totalMoney += rank.getMoney() * lottoResult.get(rank);
        }
        return totalMoney;
    }
}
