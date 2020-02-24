package lotto.model;

public class Prize {
    private static double prize = 0;

    public static double sumPrize(LottoResult lottoResult) {
        for (LottoRank lottoRank : LottoRank.values()) {
            prize += lottoRank.prizeResult(lottoResult.rankResult(lottoRank));
        }
        return prize;
    }
}
