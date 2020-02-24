package lotto.model;

public class Prize {
    private static double prize = 0;

    public static double sumPrize(LottoResult lottoResult) {
        prize += LottoRank.FIRST.prizeResult(lottoResult.getKey(LottoRank.FIRST.getCorrect()));
        prize += LottoRank.SECOND.prizeResult(lottoResult.getKey(LottoRank.SECOND.getCorrect()));
        prize += LottoRank.THIRD.prizeResult(lottoResult.getKey(LottoRank.THIRD.getCorrect()));
        prize += LottoRank.FOURTH.prizeResult(lottoResult.getKey(LottoRank.FOURTH.getCorrect()));
        prize += LottoRank.FIFTH.prizeResult(lottoResult.getKey(LottoRank.FIFTH.getCorrect()));
        return prize;
    }

    public static double getPrize() {
        return prize;
    }
}
