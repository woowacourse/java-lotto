package domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningChecker {

    private static final int WINNING_COUNT = 0;
    private static final int BONUS_COUNT = 1;
    private static final int NO_COUNT = 0;

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
            Rewards rewards = checkWinning(lotto);
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

    public int getRewardsCount(Rewards rewards) {
        return rewardsCountMap.getOrDefault(rewards, NO_COUNT);
    }

    private Rewards checkWinning(Lotto lotto) {

        List<Integer> winningAndBonusCount = findWinningAndBonusCount(lotto, winningNumbers);

        return Rewards.findReward(winningAndBonusCount.get(WINNING_COUNT),
            winningAndBonusCount.get(BONUS_COUNT));

    }

    private List<Integer> findWinningAndBonusCount(Lotto lotto, WinningNumbers winningNumbers) {
        int winningCount = lotto.countWinningNumbers(winningNumbers);
        int bonusCount = lotto.countBonusNumber(winningNumbers);

        winningCount = checkNoReward(winningCount);
        bonusCount = checkSecondPrize(winningCount, bonusCount);

        return new ArrayList<>(List.of(winningCount, bonusCount));
    }

    private int checkNoReward(int winningCount) {
        if (winningCount < WINNING_COUNT_LIMIT) {
            return NO_MEANING_COUNT;
        }
        return winningCount;
    }

    private int checkSecondPrize(int winningCount, int bonusCount) {
        if (winningCount != SECOND_PRIZE_CONDITION) {
            return NO_MEANING_COUNT;
        }
        return bonusCount;
    }

}
