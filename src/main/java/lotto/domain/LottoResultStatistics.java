package lotto.domain;

import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.domain.money.Money;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoResultStatistics {
    private static final int EARNING_RATE = 100;
    private static final int DEFAULT_COUNT = 0;

    private final Map<LottoRank, Integer> lottoResult;

    private LottoResultStatistics(final Map<LottoRank, Integer> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public static LottoResultStatistics calculateResultStatistics(
            final LottoTickets lottoTickets, final LottoWinner lottoWinner) {
        Map<LottoRank, Integer> lottoResult = setLottoResult();
        lottoTickets.putLottoResult(lottoResult, lottoWinner);
        return new LottoResultStatistics(lottoResult);
    }

    private static Map<LottoRank, Integer> setLottoResult() {
        Map<LottoRank, Integer> lottoResult = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(value -> lottoResult.put(value, DEFAULT_COUNT));
        return lottoResult;
    }

    public int calculateEarning(final Money money) {
        int totalReward = Arrays.stream(LottoRank.values())
                .mapToInt(lottoRank -> this.lottoResult.get(lottoRank) * lottoRank.getReward())
                .sum();

        double paidMoney = money.getMoney();

        return (int) (((totalReward - paidMoney) / paidMoney) * EARNING_RATE);
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return this.lottoResult;
    }
}
