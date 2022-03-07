package domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningChecker {

    private static final int WINNING_COUNT = 0;
    private static final int BONUS_COUNT = 1;
    private static final int NO_COUNT = 0;

    private static final int WINNING_COUNT_LIMIT = 3;
    private static final int SECOND_PRIZE_CONDITION = 5;
    private static final int NO_MEANING_COUNT = 0;

    private static final int LOTTO_PRICE = 1000;

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

    public double getYield() {
        return sumRewards() / (LOTTO_PRICE * lottos.getSize());
    }

    public LinkedHashMap<String, Integer> getStatisticMap() {

        return new LinkedHashMap<>() {
            {
                put("3개 일치 (5000원)- %d개\n", getRewardsCount(Rewards.FIFTH_REWARD));
                put("4개 일치 (50000원)- %d개\n", getRewardsCount(Rewards.FOURTH_REWARD));
                put("5개 일치 (1500000원)- %d개\n", getRewardsCount(Rewards.THIRD_REWARD));
                put("5개 일치, 보너스 볼 일치(30000000원)- %d개\n", getRewardsCount(Rewards.SECOND_REWARD));
                put("6개 일치 (2000000000원)- %d개\n", getRewardsCount(Rewards.FIRST_REWARD));
            }
        };
    }

    public int getRewardsCount(Rewards rewards) {
        return rewardsCountMap.getOrDefault(rewards, NO_COUNT);
    }

    private double sumRewards() {
        return rewardsCountMap.entrySet()
            .stream()
            .map(entry -> entry.getKey().getReward() * entry.getValue())
            .mapToDouble(d -> d)
            .sum();
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
