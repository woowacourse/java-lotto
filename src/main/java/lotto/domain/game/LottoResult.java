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
    private static final int INIT_PRIZE = 0;

    static {
        lottoResult = new HashMap<>();
        lottoResult.put(FIRST, new ResultCounter());
        lottoResult.put(SECOND, new ResultCounter());
        lottoResult.put(THIRD, new ResultCounter());
        lottoResult.put(FOURTH, new ResultCounter());
        lottoResult.put(FIFTH, new ResultCounter());
        lottoResult.put(MISS, new ResultCounter());
    }

    public static Map<Rank, ResultCounter> create(TotalLottoGames totalLottoGames, WinningLotto winningLotto) {
        for (Lotto lotto : totalLottoGames.getAllGames()) {
            increase(winningLotto.resultOf(lotto));
        }
        return lottoResult;
    }

    private static void increase(Rank rank) {
        lottoResult.get(rank).increase();
    }

    public static double getRateOfReturn(PurchaseAmount amount) {
        int prize = INIT_PRIZE;
        for (Rank rank : Rank.values()) {
            prize += rank.totalAmount(lottoResult.get(rank));
        }
        return amount.rateOf(prize);
    }
}
