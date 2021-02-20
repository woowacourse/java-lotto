package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Rank> getResults(WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningLotto);
            boolean bonusMatch = lotto.hasBonusNumber(winningLotto);
            ranks.add(Rank.getCorrespondingRank(matches, bonusMatch));
        }
        return ranks;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }
}
