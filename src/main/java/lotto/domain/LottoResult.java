package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class LottoResult {

    private final EnumMap<LottoRank, Integer> lottoResultStatistics;

    public LottoResult(LottoAnnouncement lottoAnnouncement, Lottos lottos) {
        this.lottoResultStatistics = getStatistics(lottoAnnouncement, lottos);
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
}
