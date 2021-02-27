package lotto.domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottoBunch = new ArrayList<>();

    public Lottos(List<Lotto> lottos) {
        lottoBunch.addAll(lottos);
    }

    public Lottos(Lottos manualLottos, Lottos autoLottos) {
        lottoBunch.addAll(manualLottos.getLottoBunch());
        lottoBunch.addAll(autoLottos.getLottoBunch());
    }

    public Map<LottoRank, Integer> getStatistics(WinningLotto winningLotto) {
        Map<LottoRank, Integer> getStatistics = setUpStatistics();
        lottoBunch.stream()
                .map(winningLotto::getLottoResult)
                .forEach(rank -> getStatistics.replace(rank, getStatistics.get(rank) + 1));

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