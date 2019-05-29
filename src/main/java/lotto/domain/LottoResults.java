package lotto.domain;

import java.util.*;

public class LottoResults {
    private static final int BASE_AMOUNT = 0;
    private static final int PERCENT = 100;

    private final Map<LottoRewards, Integer> lottoRewards;
    private final Money money;

    public LottoResults(LottoTickets lottoTickets, LottoTicket rewardTicket, Money money) {
        lottoRewards = initRewards();
        this.money = money;

        List<Integer> results = lottoTickets.getRewards(rewardTicket);

        for (Integer result : results) {
            lottoRewards.put(LottoRewards.valueOf(result), lottoRewards.getOrDefault(LottoRewards.valueOf(result), 0) + 1);
        }
    }

    private Map<LottoRewards, Integer> initRewards() {
        Map<LottoRewards, Integer> lottoRewards;
        lottoRewards = new TreeMap<>();
        for (LottoRewards lottoReward : LottoRewards.values()) {
            lottoRewards.put(lottoReward, BASE_AMOUNT);
        }
        return lottoRewards;
    }

    public double getYield() {
        return (double) getRewardMoney() / money.getMoney() * PERCENT;
    }

    private long getRewardMoney() {
        long money = 0;
        for (Map.Entry<LottoRewards, Integer> entry : lottoRewards.entrySet()) {
            money += entry.getKey().getRewardMoney() * entry.getValue();
        }
        return money;
    }

    public Map<LottoRewards, Integer> getLottoRewards() {
        return lottoRewards;
    }
}
