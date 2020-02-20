package lotto.domain;

import java.util.Map;

public class RewardRate {

    private static final int RATE = 100;

    public static int calculateRewardRate(int money, Map<WinningValue, Integer> winningValueResult) {
        return winningValueResult.entrySet()
                .stream()
                .mapToInt(result ->
                        result.getKey().getReward()
                                * result.getValue())
                .sum() / money * RATE;
    }
}
