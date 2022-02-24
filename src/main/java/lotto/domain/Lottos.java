package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int CHECK_BONUS_COUNT = 5;

    private final List<Lotto> lottos;

    public Lottos(int count) {
        lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public void addMatchingCount(LottoResult lottoResult, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            int matchingCount = lotto.getMatchingCount(winningLotto.getWinningLotto());
            boolean isBonus = matchingCount == CHECK_BONUS_COUNT && lotto.contains(winningLotto.getBonusBall());
            lottoResult.increaseRankCount(Rank.getRank(matchingCount, isBonus));
        }
    }
}
