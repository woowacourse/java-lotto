package lotto.domain.game;

import java.util.HashMap;
import java.util.Map;

import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;

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

    public static Map<Rank, ResultCounter> create(TotalLottoGames totalLottoGames, WinningLotto winningLotto) {
        for (Lotto lotto : totalLottoGames.getAll()) {
            increase(winningLotto.resultOf(lotto));
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
