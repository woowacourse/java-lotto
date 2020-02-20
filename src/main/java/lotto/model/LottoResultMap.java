package lotto.model;

import java.util.HashMap;

public class LottoResultMap {

    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String FIVE_BONUS = "5+";
    private static final String SIX = "6";

    public static HashMap<String, LottoResult> resultCount = new HashMap<>();

    static {
        resultCount.put(THREE, LottoResult.THREE);
        resultCount.put(FOUR, LottoResult.FOUR);
        resultCount.put(FIVE, LottoResult.FIVE);
        resultCount.put(FIVE_BONUS, LottoResult.FIVE_BONUS);
        resultCount.put(SIX, LottoResult.SIX);
    }
}
