package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int CHECK_BONUS_COUNT = 5;

    private final List<Lotto> lottos;

    public Lottos(int count) {
        this(createRandomLottos(count));
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

    private static List<Lotto> createRandomLottos(int count) {
        List<Lotto> randomLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            randomLottos.add(new Lotto());
        }
        return randomLottos;
    }
}
