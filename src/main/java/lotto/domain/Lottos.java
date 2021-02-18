package lotto.domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottoBunch = new ArrayList<>();

    public Lottos(List<Lotto> purchasedLottos) {
        lottoBunch.addAll(purchasedLottos);
    }

    public Lottos(int purchasedLottoCount) {
        for (int i = 0; i < purchasedLottoCount; i++) {
            lottoBunch.add(LottoGenerator.createLotto());
        }
    }

    public Map<LottoRank, Integer> getStatistics(WinningLotto winningLotto) {
        Map<LottoRank, Integer> getStatistics = setUpStatistics();
        for (Lotto lotto : lottoBunch) {
            LottoRank lottoRank = winningLotto.getLottoResult(lotto);
            getStatistics.replace(lottoRank, getStatistics.get(lottoRank) + 1);
        }
        return getStatistics;
    }

    private Map<LottoRank, Integer> setUpStatistics() {
        Map<LottoRank, Integer> setUpStatistics = new LinkedHashMap<>();
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