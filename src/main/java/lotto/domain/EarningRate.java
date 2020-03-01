package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EarningRate {
    private double totalMoney = 0.0;

    public double calculateEarningRate(Map<Rank,Long> eachRankCount,Money money) {
        List<Rank> rank = new ArrayList<>(Arrays.asList(Rank.values()));
        rank.remove(Rank.NO_RANK);

        for(Rank r : rank){
            this.totalMoney += r.getWinningMoney() * eachRankCount.get(r);
        }

        return this.totalMoney / money.getMoney() * 100.0;
    }
}
