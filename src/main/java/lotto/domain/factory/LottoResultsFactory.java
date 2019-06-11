package lotto.domain.factory;

import lotto.domain.*;

import java.util.Map;
import java.util.TreeMap;

public class LottoResultsFactory {
    private static final int BASE_AMOUNT = 0;

    public static LottoResults create(final LottoTickets lottoTickets, final WinningLotto winningLotto, final LottoMoney lottoMoney) {
        return new LottoResults(getRewards(lottoTickets, winningLotto), lottoMoney);
    }

    private static Map<LottoRank, Integer> getRewards(LottoTickets lottoTickets, WinningLotto winningLotto) {
        Map<LottoRank, Integer> lottoRewards = initRewards();
        lottoTickets.getRewards(winningLotto).forEach((reward, countOfReward) -> {
            lottoRewards.put(reward, countOfReward);
        });

        return lottoRewards;
    }

    private static Map<LottoRank, Integer> initRewards() {
        Map<LottoRank, Integer> lottoRewards = new TreeMap<>();
        for (LottoRank lottoReward : LottoRank.values()) {
            lottoRewards.put(lottoReward, BASE_AMOUNT);
        }
        return lottoRewards;
    }
}
