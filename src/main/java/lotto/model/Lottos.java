package lotto.model;

import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoStatistics checkRank(WinningLotto winningLotto) {
        Map<Rank, Integer> rankMap = Rank.initMap();

        lottos.stream()
            .map(lotto -> lotto.calculateRank(winningLotto))
            .forEach(rank -> rankMap.replace(rank, rankMap.get(rank) + 1));

        return new LottoStatistics(rankMap);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
