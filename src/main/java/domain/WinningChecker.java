package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningChecker {

    private final int WINNING_COUNT = 0;
    private final int BONUS_COUNT = 1;

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

    private Rewards checkWinning(Lotto lotto) {

        List<Integer> winningAndBonusCount = lotto.getWinningAndBonusCount(winningNumbers);

        return Rewards.findReward(winningAndBonusCount.get(WINNING_COUNT),
            winningAndBonusCount.get(BONUS_COUNT));

    }

}
