package lotto.model;

public class Prize {

    private static final int PERCENT = 100;
    private static double prize;

    public Prize() {
        prize = setPrize();
    }

    private static double setPrize() {
        return LottoResultMap.resultCount.keySet().stream()
            .mapToDouble(lottoResult -> LottoResultMap.resultCount.get(lottoResult).prizeResult())
            .sum();
    }

    public static int countYieldMoney() {
        return (int) (prize / Payment.payment * PERCENT);
    }

    public static double getPrize() {
        return prize;
    }
}
