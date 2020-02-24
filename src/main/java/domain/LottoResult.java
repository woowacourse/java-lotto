package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final Map<LottoRank, Integer> result = new HashMap<>();
    private static final int RESULT_INCREMENT = 1;
    private static final int INITIAL_RESULT = 0;

    static {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, INITIAL_RESULT);
        }
    }
    public static void countWinningLotto() {
        for (Lotto lotto : Lottos.getLottos()) {
            addWinningRankCount(LottoRank
                    .findRank(WinningNumber.countWinningMatch(lotto),
                            WinningNumber.isBonusMatch(lotto))
            );
        }
    }

    private static void addWinningRankCount(final LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + RESULT_INCREMENT);
        }
    }

    public static int calculateTotalProfit() {
        return result.keySet()
                .stream()
                .mapToInt(rank -> rank.getWinningMoney() * getRankCount(rank))
                .sum();
    }

    public static int getSize() {
        return result.size();
    }

    public static int getRankCount(final LottoRank rank) {
        Objects.requireNonNull(rank);
        return result.get(rank);
    }
}
