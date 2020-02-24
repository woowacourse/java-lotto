package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final int RESULT_INCREMENT = 1;
    private static final int INITIAL_RESULT = 0;
    private final Map<LottoRank, Integer> result = new HashMap<>();

    public LottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, INITIAL_RESULT);
        }
    }
    public void countWinningLotto(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            addWinningRankCount(LottoRank
                    .findRank(WinningNumber.countWinningMatch(lotto),
                            WinningNumber.isBonusMatch(lotto))
            );
        }
    }

    private void addWinningRankCount(final LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + RESULT_INCREMENT);
        }
    }

    public int calculateTotalProfit() {
        return result.keySet()
                .stream()
                .mapToInt(rank -> rank.getWinningMoney() * getRankCount(rank))
                .sum();
    }

    public int getRankCount(final LottoRank rank) {
        Objects.requireNonNull(rank);
        return result.get(rank);
    }
}
