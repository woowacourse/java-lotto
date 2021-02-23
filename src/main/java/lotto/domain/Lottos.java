package lotto.domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottoBunch = new ArrayList<>();
    private final int manualLottoCount;

    public Lottos(List<String> manualLottoNumbers, int purchasedLottoCount) {
        manualLottoCount = manualLottoNumbers.size();
        int autoLottoCount = purchasedLottoCount - manualLottoCount;
        for (String manualLottoNumber : manualLottoNumbers) {
            lottoBunch.add(LottoGenerator.createManualLotto(manualLottoNumber));
        }
        for (int i = 0; i < autoLottoCount; i++) {
            lottoBunch.add(LottoGenerator.createAutoLotto());
        }
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

    public int getManualLottoCount() {
        return manualLottoCount;
    }
}