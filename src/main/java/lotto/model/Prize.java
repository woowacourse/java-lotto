package lotto.model;

public class Prize {
    private static double prize = 0;

    public static double sumPrize(LottoResult lottoResult) {
        for (LottoRank lottoRank : LottoRank.values()) {
            prize += lottoRank.prizeResult(lottoResult.getKey(lottoRank.getRank()));
        }
        return prize;
    }

    public static double getPrize() {
        return prize;
    }
}
