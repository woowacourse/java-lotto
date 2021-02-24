package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.generator.LottoGenerator;

public class Lottos {

    private final List<Lotto> lottoBunch = new ArrayList<>();
    private final LottoGenerator lottoGenerator;

    public Lottos(LottoGenerator lottoGenerator, int purchasedLottoCount) {
        this.lottoGenerator = lottoGenerator;
        for (int i = 0; i < purchasedLottoCount; i++) {
            lottoBunch.add(new Lotto(this.lottoGenerator.generateNumbers()));
        }
    }

    public EnumMap<LottoRank, Integer> getStatistics(LottoAnnouncement lottoAnnouncement) {
        EnumMap<LottoRank, Integer> getStatistics = setUpStatistics();
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

    public List<Lotto> getLottoBunch() {
        return Collections.unmodifiableList(lottoBunch);
    }

    public int getSize() {
        return lottoBunch.size();
    }
}
