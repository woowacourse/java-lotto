package lotto.domain;

import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult createResult(final WinLotto winLotto) {
        final Map<Rank, Integer> resultMap = Rank.initResultMap();
        lottos.stream()
                .map(winLotto::matchResult)
                .forEach(result -> rankCountUp(resultMap, result));
        return new LottoResult(resultMap);
    }

    private Integer rankCountUp(Map<Rank, Integer> resultMap, Rank result) {
        return resultMap.replace(result, resultMap.get(result) + 1);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
