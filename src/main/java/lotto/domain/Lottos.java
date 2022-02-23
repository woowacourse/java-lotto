package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult createResult(WinLotto winLotto) {
        final Map<Rank, Integer> resultMap = new HashMap<>();
        resultMap.put(Rank.FIRST, 0);
        resultMap.put(Rank.SECOND, 0);
        lottos.stream()
                .map(winLotto::matchResult)
                .forEach(result -> resultMap.put(result, resultMap.get(result) + 1));
        return new LottoResult(resultMap);
    }
}
