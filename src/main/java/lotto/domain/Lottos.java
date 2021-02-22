package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Lottos merge(Lottos that) {
        List<Lotto> newLottos = Stream.concat(this.lottos.stream(), that.lottos.stream())
                .collect(Collectors.toList());
        return new Lottos(newLottos);
    }
}
