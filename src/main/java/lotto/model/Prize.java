package lotto.model;

public class Prize {

    private static double prize = 0;

    public static double sumPrize() {
        for (String key : LottoResultMap.resultCount.keySet())
            prize += LottoResultMap.resultCount.get(key).prizeResult();
        return prize;
    }
}
