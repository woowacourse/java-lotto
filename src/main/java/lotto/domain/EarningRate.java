package lotto.domain;

import lotto.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EarningRate {

    private static final double RATE = 100.0;

    public static double calculateEarningRate(Map<Rank, Long> eachRankCount, Money money) {
        double totalMoney = 0.0;
        List<Rank> rank = new ArrayList<>(Arrays.asList(Rank.values()));
        rank.remove(Rank.NO_RANK);

        for (Rank r : rank) {
            totalMoney += r.getWinningMoney() * eachRankCount.get(r);
        }
        ValidationUtils.validateUnderFlowOrOverFlow(totalMoney);

        return totalMoney / money.getMoney() * RATE;
    }
}
