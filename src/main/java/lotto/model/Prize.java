package lotto.model;

import java.util.HashMap;

public class Prize {
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String FIVE_BONUS = "5+";
    public static final String SIX = "6";

    private static double prize = 0;

    public static double sumPrize(HashMap<String, Integer> resultCount) {
        prize += LottoResult.THREE.prizeResult(resultCount.get(THREE));
        prize += LottoResult.FOUR.prizeResult(resultCount.get(FOUR));
        prize += LottoResult.FIVE.prizeResult(resultCount.get(FIVE));
        prize += LottoResult.FIVE_BONUS.prizeResult(resultCount.get(FIVE_BONUS));
        prize += LottoResult.SIX.prizeResult(resultCount.get(SIX));
        return prize;
    }
}
