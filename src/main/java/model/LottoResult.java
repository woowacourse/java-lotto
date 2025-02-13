package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {

    private Map<RankType, Integer> result;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        result = winningLotto.evaluateRank(lottos.getLottos());
        sort();
    }

    public void sort() { // TODO: 리팩토링 필요
        result = result.entrySet().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getKey().getRank(), r1.getKey().getRank()))
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                ));
    }
}
