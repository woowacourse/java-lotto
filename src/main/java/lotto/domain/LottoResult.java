package lotto.domain;

import java.util.HashMap;
import java.util.Map;

import static lotto.domain.Rank.*;

public class LottoResult {
    private static final Map<Rank, ResultCounter> lottoResult;

    static {
        lottoResult = new HashMap<>();
        lottoResult.put(FIRST, ResultCounter.create());
        lottoResult.put(SECOND, ResultCounter.create());
        lottoResult.put(THIRD, ResultCounter.create());
        lottoResult.put(FOURTH, ResultCounter.create());
        lottoResult.put(FIFTH, ResultCounter.create());
        lottoResult.put(MISS, ResultCounter.create());
    }

    public static Map<Rank, ResultCounter> create(AutoLottoGames autoLottoGames, WinningLotto winningLotto) {
        for (Lotto lotto : autoLottoGames) {
            increase(winningLotto.getRank(lotto));
        }
        return lottoResult;
    }

    private static void increase(Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank).increase());
    }

    public static double getRateOfReturn(PurchaseAmount amount) {
        int prize = 0;
        for (Rank rank : Rank.values()) {
            prize += rank.totalAmount(lottoResult.get(rank));
        }
        return amount.rateOf(prize);
    }
}
