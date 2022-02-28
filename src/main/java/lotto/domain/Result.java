package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;

public class Result {

    private static final int INIT_COUNT = 0;
    private static final int ADD_NUMBER = 1;

    private final Map<LottoRanking, Integer> result = new EnumMap<LottoRanking, Integer>(LottoRanking.class);

    {
        for (LottoRanking value : LottoRanking.values()) {
            result.put(value, INIT_COUNT);
        }
    }

    public Result(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            LottoRanking lottoRanking = winningLotto.getLottoRanking(lotto);
            result.put(lottoRanking, result.get(lottoRanking) + ADD_NUMBER);
        }
    }

    public int getCount(LottoRanking key) {
        return result.get(key);
    }

    public double getRateOfProfit(Money money) {
        long totalMoney = 0L;
        for (Map.Entry<LottoRanking, Integer> entry : result.entrySet()) {
            totalMoney += entry.getKey()
                .multiply(entry.getValue());
        }
        return money.getRateOfProfit(totalMoney);
    }
}
