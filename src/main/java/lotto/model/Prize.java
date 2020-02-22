package lotto.model;

public class Prize {
    private static double prize = 0;

    public static double sumPrize(LottoResult lottoResult) {
        prize += LottoRank.FIRST.prizeResult(lottoResult.getKey(LottoRank.FIRST.getRank()));
        prize += LottoRank.SECOND.prizeResult(lottoResult.getKey(LottoRank.SECOND.getRank()));
        prize += LottoRank.THIRD.prizeResult(lottoResult.getKey(LottoRank.THIRD.getRank()));
        prize += LottoRank.FOURTH.prizeResult(lottoResult.getKey(LottoRank.FOURTH.getRank()));
        prize += LottoRank.FIFTH.prizeResult(lottoResult.getKey(LottoRank.FIFTH.getRank()));
        return prize;
    }

    public static double getPrize() {
        return prize;
    }
}
