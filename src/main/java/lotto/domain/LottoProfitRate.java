package lotto.domain;

import java.util.Map;
import java.util.Map.Entry;

public class LottoProfitRate {

    private static final int DECIMAL_TRIM_NUMERATOR = 100;
    private static final double DECIMAL_TRIM_DENOMINATOR = 100.00;

    private final double profitRate;

    public LottoProfitRate(LottoResult lottoResult, Piece purchasedLottoPiece) {
        this.profitRate = calculateProfitRate(lottoResult.getLottoResultStatistics(),
            purchasedLottoPiece);
    }

    private double calculateProfitRate(Map<LottoRank, Integer> lottosResultStatistics,
        Piece lottoPiece) {
        double sum = 0;
        for (Entry<LottoRank, Integer> keyValue : lottosResultStatistics.entrySet()) {
            sum += keyValue.getKey().getPrizeMoney() * keyValue.getValue();
        }
        double investCapital = lottoPiece.getPieceNumber() * Lotto.LOTTO_PRICE;
        double rawProfitRate = sum / investCapital;
        return Math.round(rawProfitRate * DECIMAL_TRIM_NUMERATOR) / DECIMAL_TRIM_DENOMINATOR;
    }

    public double getProfitRate() {
        return this.profitRate;
    }

}
