package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningChecker {

    private static final int WINNING_COUNT_LIMIT = 3;
    private static final int SECOND_PRIZE_CONDITION = 5;
    private static final int NO_MEANING_COUNT = 0;

    private final Lottos lottos;
    private final WinningNumbers winningNumbers;
    private final Map<Rewards, Integer> rewardsCountMap = new EnumMap<>(Rewards.class);

    public WinningChecker(Lottos lottos, WinningNumbers winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
    }

    public void check() {
        for (Lotto lotto : lottos.getLottos()) {
            Rewards rewards = checkWinning(lotto, winningNumbers.getWinningNumbers(),
                winningNumbers.getBonusNumber());
            rewardsCountMap.put(rewards, rewardsCountMap.getOrDefault(rewards, 0) + 1);
        }
    }

    public double sumRewards() {
        return rewardsCountMap.entrySet()
            .stream()
            .map(entry -> entry.getKey().getReward() * entry.getValue())
            .mapToDouble(d -> d)
            .sum();
    }

    private Rewards checkWinning(Lotto lotto, List<Integer> winningNumbers, Integer bonusNumber) {

        int winningCount = countWinningNumbers(lotto, winningNumbers);
        int bonusCount = countBonusNumber(lotto, bonusNumber);

        winningCount = checkNoReward(winningCount);
        bonusCount = checkSecondPrize(winningCount, bonusCount);

        return Rewards.findReward(winningCount, bonusCount);
    }

    private int countWinningNumbers(Lotto lotto, List<Integer> winningNumbers) {
        return (int) lotto.getLottoNumbers().stream()
            .filter(winningNumbers::contains)
            .count();
    }

    private int countBonusNumber(Lotto lotto, Integer bonusNumber) {
        return (int) lotto.getLottoNumbers().stream()
            .filter(bonusNumber::equals)
            .count();
    }

    private int checkNoReward(Integer winningCount) {
        if (winningCount < WINNING_COUNT_LIMIT) {
            return NO_MEANING_COUNT;
        }
        return winningCount;
    }

    private int checkSecondPrize(Integer winningCount, Integer bonusCount) {
        if (winningCount != SECOND_PRIZE_CONDITION) {
            return NO_MEANING_COUNT;
        }
        return bonusCount;
    }

}
