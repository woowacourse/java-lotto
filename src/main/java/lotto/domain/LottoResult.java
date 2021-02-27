package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> lottoResultStatistics;
    private final Piece issuedLottoPieces;

    public LottoResult(LottoAnnouncement lottoAnnouncement, Lottos lottos) {
        this.lottoResultStatistics = getStatistics(lottoAnnouncement, lottos);
        Money investedMoney = new Money(lottos.getSize() * Lotto.LOTTO_PRICE);
        this.issuedLottoPieces = new Piece(investedMoney, lottos.getSize());
    }

    public Map<LottoRank, Integer> getStatistics(LottoAnnouncement lottoAnnouncement,
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

    public LottoProfitRate getProfitRate() {
        return new LottoProfitRate(this, issuedLottoPieces);
    }

    public Map<LottoRank, Integer> getLottoResultStatistics() {
        return Collections.unmodifiableMap(lottoResultStatistics);
    }
}
