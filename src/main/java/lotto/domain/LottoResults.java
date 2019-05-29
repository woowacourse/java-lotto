package lotto.domain;

import java.util.*;

public class LottoResults {
    private static final int BASE_AMOUNT = 0;
    private static final int PERCENT = 100;

    private final Map<LottoRank, Integer> lottoRewards;
    private final LottoMoney lottoMoney;

    public LottoResults(final List<LottoTicket> lottoTickets, final WinningLotto winningLotto, final LottoMoney lottoMoney) {
        lottoRewards = initRewards();
        this.lottoMoney = lottoMoney;
        getRewards(lottoTickets, winningLotto);
    }

    private Map<LottoRank, Integer> initRewards() {
        Map<LottoRank, Integer> lottoRewards;
        lottoRewards = new TreeMap<>();
        for (LottoRank lottoReward : LottoRank.values()) {
            lottoRewards.put(lottoReward, BASE_AMOUNT);
        }
        return lottoRewards;
    }

    private void getRewards(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank lottoRank = winningLotto.getRank(lottoTicket);
            lottoRewards.put(lottoRank, lottoRewards.get(lottoRank) + 1);
        }
    }

    public double getYield() {
        return (double) getRewardMoney() / lottoMoney.getMoney() * PERCENT;
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
