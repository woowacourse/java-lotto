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
        init();
    }

    public static void init() {
        lottoResult.put(FIRST, new ResultCounter());
        lottoResult.put(SECOND, new ResultCounter());
        lottoResult.put(THIRD, new ResultCounter());
        lottoResult.put(FOURTH, new ResultCounter());
        lottoResult.put(FIFTH, new ResultCounter());
        lottoResult.put(MISS, new ResultCounter());
    }

    public static Map<Rank, ResultCounter> create(TotalLottoGames totalLottoGames, WinningLotto winningLotto) {
        for (Lotto lotto : totalLottoGames.allGames()) {
            increase(winningLotto.resultOf(lotto));
        }
        return lottoResult;
    }

    public static Map<Rank, ResultCounter> get() {
        return lottoResult;
    }
  
    private static void increase(Rank rank) {
        lottoResult.get(rank).increase();
    }

    public static double rateOfReturn(PurchaseAmount amount) {
        int prize = INIT_PRIZE;
        for (Rank rank : Rank.values()) {
            prize += rank.totalAmount(lottoResult.get(rank));
        }
        return amount.rateOf(prize);
    }
  
    public static int resultAmount() {
        int resultAmount = 0;
        for (Map.Entry<Rank, ResultCounter> eachResult : lottoResult.entrySet()) {
            resultAmount += eachResult.getKey().getPrize() * eachResult.getValue().getCounter();
        }
        return resultAmount;
    }
}
