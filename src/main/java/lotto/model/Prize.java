package lotto.model;

import java.util.HashMap;

public class Prize {
    private static double prize = 0;
    public static double sumPrize(HashMap<String, Integer> resultCount) {
        prize += LottoResult.THREE.prizeResult(resultCount.get("3"));
        prize += LottoResult.FOUR.prizeResult(resultCount.get("4"));
        prize += LottoResult.FIVE.prizeResult(resultCount.get("5"));
        prize += LottoResult.FIVE_BONUS.prizeResult(resultCount.get("5+"));
        prize += LottoResult.SIX.prizeResult(resultCount.get("6"));
        return prize;
    }
}
