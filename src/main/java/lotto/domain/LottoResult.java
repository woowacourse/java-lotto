package lotto.domain;

import java.util.HashMap;
import java.util.Map;

import static lotto.domain.Rank.*;

public class LottoResult {
    private static final Map<Rank, Counter> lottoResult;

    static {
        lottoResult = new HashMap<>();
        lottoResult.put(FIRST, Counter.create());
        lottoResult.put(THIRD, Counter.create());
        lottoResult.put(FOURTH, Counter.create());
        lottoResult.put(FIFTH, Counter.create());
        lottoResult.put(MISS, Counter.create());
    }

    public static Map<Rank, Counter> create(LottoGames lottoGames, WinLotto winLotto) {
        for (Lotto lotto : lottoGames) {
            increase(winLotto.getRank(lotto));
        }
        return lottoResult;
    }

    private static void increase(Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank).increase());
    }

    public static double getRateOfReturn(PurchaseAmount amount) {
        int prize = 0;
        for (Rank rank : Rank.values()) {
            prize += lottoResult.get(rank).totalAmount(rank);
        }
        return amount.rateOf(prize);
    }
}
