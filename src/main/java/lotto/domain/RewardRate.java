package lotto.domain;

import java.util.Map;

public class RewardRate {
    public static int calculateRewardRate(int money, Map<WinningValue, Integer> winningValueResult) {
        int sum = 0;
        for (Map.Entry<WinningValue, Integer> result : winningValueResult.entrySet()) {
            WinningValue winningValue = result.getKey();
            int count = result.getValue();
            sum += winningValue.getReward() * count;
        }
        return (sum / money) / 100;
    }
}
