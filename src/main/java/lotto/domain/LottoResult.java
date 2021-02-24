package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class LottoResult {

    public static final int LOTTO_PRICE = 1000;
    private static final int DECIMAL_TRIM_NUMERATOR = 100;
    private static final double DECIMAL_TRIM_DENOMINATOR = 100.00;

    private final EnumMap<LottoRank, Integer> lottoResultStatistics;
    private final double profitRate;

    public LottoResult(LottoAnnouncement lottoAnnouncement, Lottos lottos) {
        this.lottoResultStatistics = getStatistics(lottoAnnouncement, lottos);
        int lottoBunchSize = lottos.getSize();
        this.profitRate = calculateProfitRate(lottoResultStatistics, lottoBunchSize);
    }

    public double calculateProfitRate(EnumMap<LottoRank, Integer> lottosResult, int lottoPiece) {
        double sum = 0;
        for (Entry<LottoRank, Integer> keyValue : lottosResult.entrySet()) {
            sum += keyValue.getKey().getPrizeMoney() * keyValue.getValue();
        }
        double investCapital = lottoPiece * LOTTO_PRICE;
        double rawProfitRate = sum / investCapital;
        return Math.round(rawProfitRate * DECIMAL_TRIM_NUMERATOR) / DECIMAL_TRIM_DENOMINATOR;
    }

    public EnumMap<LottoRank, Integer> getStatistics(LottoAnnouncement lottoAnnouncement,
        Lottos lottos) {
        EnumMap<LottoRank, Integer> getStatistics = setUpStatistics();
        List<Lotto> lottoBunch = lottos.getLottoBunch();
        for (Lotto lotto : lottoBunch) {
            LottoRank targetRank = lotto.getLottoRank(lottoAnnouncement);
            getStatistics.replace(targetRank, getStatistics.get(targetRank) + 1);
        }
        return getStatistics;
    }

    private EnumMap<LottoRank, Integer> setUpStatistics() {
        EnumMap<LottoRank, Integer> setUpStatistics = new EnumMap<>(LottoRank.class);
        for (LottoRank singleLottoRank : LottoRank.values()) {
            setUpStatistics.put(singleLottoRank, 0);
        }
        return setUpStatistics;
    }

    public EnumMap<LottoRank, Integer> getLottoResultStatistics() {
        return lottoResultStatistics;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
