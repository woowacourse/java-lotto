package lotto.domain;

import java.util.Map;

public class LottoResults {
    private static final int PERCENT = 100;
    
    private final Map<LottoRank, Integer> lottoRewards;
    private final LottoMoney lottoMoney;

    public LottoResults(final Map<LottoRank, Integer> lottoRewards, final LottoMoney lottoMoney) {
        this.lottoRewards = lottoRewards;
        this.lottoMoney = lottoMoney;
    }

    public double getYield() {
        return lottoMoney.divideMoney(getRewardMoney()) * PERCENT;
    }

    private long getRewardMoney() {
        long money = 0;
        for (Map.Entry<LottoRank, Integer> entry : lottoRewards.entrySet()) {
            money += entry.getKey().getRewardMoney() * entry.getValue();
        }
        return money;
    }

    public Map<LottoRank, Integer> getLottoRewards() {
        return lottoRewards;
    }
}
