package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottoBunch = new ArrayList<>();

    public Lottos(List<Lotto> purchasedLottos) {
        lottoBunch.addAll(purchasedLottos);
    }

    public Lottos(int purchasedLottoCount) {
        for (int i = 0; i < purchasedLottoCount; i++) {
            lottoBunch.add(new Lotto());
        }
    }

    public int getSize() {
        return lottoBunch.size();
    }

    public Map<String, Integer> getStatistics(List<Integer> winningNumbers, int bonusNumber) {
        Map<String, Integer> getStatistics = setUpStatistics();
        for (Lotto lotto : lottoBunch) {
            String rankName = lotto.getLottoRank(winningNumbers, bonusNumber).name();
            getStatistics.replace(rankName, getStatistics.get(rankName) + 1);
        }
        return getStatistics;
    }

    private Map<String, Integer> setUpStatistics() {
        Map<String, Integer> setUpStatistics = new HashMap<>();
        for (LottoRank singleLottoRank : LottoRank.values()) {
            setUpStatistics.put(singleLottoRank.name(), 0);
        }
        return setUpStatistics;
    }
}
