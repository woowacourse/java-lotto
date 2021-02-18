package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LottoResultStatistics {
    private static final int EARNING_RATE = 100;

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

    public static Map<LottoRank, Integer> setLottoResult() {
        Map<LottoRank, Integer> lottoResult = new TreeMap<>(LottoRank.matchCountComparator);
        Arrays.stream(LottoRank.values())
                .forEach(value -> lottoResult.put(value, 0));

        return lottoResult;
    }

    public int calculateEarning(final Money money) {
        int totalReward = 0;

        for (LottoRank lottoRank : LottoRank.values()) {
            totalReward += this.lottoResult.get(lottoRank) * lottoRank.getReward();
        }
        double paidMoney = money.getMoney();
        int result = (int) (((totalReward - paidMoney) / paidMoney) * EARNING_RATE);

        return result;
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return lottoResult;
    }
}
