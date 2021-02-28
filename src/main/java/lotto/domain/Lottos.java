package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.generator.LottoNumberGenerator;

public class Lottos {

    private final List<Lotto> lottoBunch;

    private Lottos(final List<Lotto> lottoBunch) {
        this.lottoBunch = lottoBunch;
    }

    public static Lottos generateLottos(LottoNumberGenerator lottoNumberGenerator,
        Piece purchasedLottoCount) {
        int purchasedRawLottoCount = purchasedLottoCount.getPieceNumber();
        final List<Lotto> candidateLottoBunch = new ArrayList<>(purchasedRawLottoCount);
        for (int i = 0; i < purchasedRawLottoCount; i++) {
            candidateLottoBunch.add(new Lotto(lottoNumberGenerator.generateNumbers()));
        }

        return new Lottos(candidateLottoBunch);
    }

    public Lottos mergeLottos(Lottos targetLottos) {
        final List<Lotto> mergedLottosBunch = new ArrayList(lottoBunch.size()
            + targetLottos.lottoBunch.size());
        mergedLottosBunch.addAll(lottoBunch);
        mergedLottosBunch.addAll(targetLottos.lottoBunch);
        return new Lottos(mergedLottosBunch);
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
