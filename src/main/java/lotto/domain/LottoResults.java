package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResults {
    private static final int PERCENT = 100;

    private final List<LottoRewards> lottoRewards;

//    public LottoResults(List<Integer> results) {
//        lottoRewards = new ArrayList<>();
//        for (Integer result : results) {
//            lottoRewards.add(LottoRewards.valueOf(result));
//        }
//    }

    public LottoResults(LottoTickets lottoTickets, UserLottoTicket rewardTicket) {
        lottoRewards = new ArrayList<>();
        List<Integer> results = lottoTickets.getRewards(rewardTicket);
        for (Integer result : results) {
            lottoRewards.add(LottoRewards.valueOf(result));
        }
    }

    public double getYield() {
        return (double) getRewardMoney() / getBuyMoney() * PERCENT;
    }

    private int getRewardMoney() {
        int money = 0;
        for (LottoRewards lottoReward : lottoRewards) {
            money += lottoReward.getRewardMoney();
        }
        return money;
    }

    private int getBuyMoney() {
        return lottoRewards.size() * 1000;
    }

    public List<LottoRewards> getLottoRewards() {
        return lottoRewards;
    }
}
