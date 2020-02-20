package lotto.model;

public class Prize {

    private static double prize = 0;

    public static double sumPrize() {
        prize += LottoResult.THREE.prizeResult();
        prize += LottoResult.FOUR.prizeResult();
        prize += LottoResult.FIVE.prizeResult();
        prize += LottoResult.FIVE_BONUS.prizeResult();
        prize += LottoResult.SIX.prizeResult();
        return prize;
    }
}
