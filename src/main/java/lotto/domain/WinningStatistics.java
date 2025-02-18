package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.constant.Limit.LOTTO_UNIT_PRICE;

public class WinningStatistics {
    private static final int TRUNCATION = 100;

    private final Map<Rank, Integer> winningStatistics;

    public WinningStatistics(final List<Lotto> lottos, final WinningNumbers winningNumbers,
                             final BonusNumber bonusNumber) {
        this.winningStatistics = calculateStatistics(lottos, winningNumbers, bonusNumber);
    }

    public Map<Rank, Integer> calculateStatistics(final List<Lotto> lottos, final WinningNumbers winningNumbers,
                                                 final BonusNumber bonusNumber) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (final Lotto lotto : lottos) {
            int matchCount = winningNumbers.calculateMatchCount(lotto.getNumbers());
            boolean hanBonusNumber = bonusNumber.isIncludedIn(lotto);
            Rank rank = Rank.getRank(matchCount, hanBonusNumber);
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }
        return statistics;
    }

    public double calculateReturnRate(final int amount) {
        int total = calculateTotal();
        double returnRate = (double) total / (amount * LOTTO_UNIT_PRICE.getValue());
        return Math.floor(returnRate * TRUNCATION) / TRUNCATION;
    }

    private int calculateTotal() {
        int total = 0;
        for (final Rank rank : winningStatistics.keySet()) {
            total += rank.getWinningAmountByCount(winningStatistics.get(rank));
        }
        return total;
    }

    public int getRankCount(final Rank rank) {
        return winningStatistics.getOrDefault(rank, 0);
    }
}
