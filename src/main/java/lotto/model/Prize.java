package lotto.model;

public class Prize {

    private static double prize = 0;

    public static double sumPrize() {
        prize += LottoResult.THREE.prizeResult(LottoResult.THREE.getCount());
        prize += LottoResult.FOUR.prizeResult(LottoResult.FOUR.getCount());
        prize += LottoResult.FIVE.prizeResult(LottoResult.FIVE.getCount());
        prize += LottoResult.FIVE_BONUS.prizeResult(LottoResult.FIVE_BONUS.getCount());
        prize += LottoResult.SIX.prizeResult(LottoResult.SIX.getCount());
        return prize;
    }
}
