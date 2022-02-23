package domain;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LottoGame {

    private final Lottos lottos;
    private final LottoReferee referee;
    private final TreeMap<LottoResult, Integer> resultsStatistics = Arrays
            .stream(LottoResult.values())
            .collect(Collectors.toMap(key -> key, value -> 0, (o1, o2) -> o1, TreeMap::new));

    public LottoGame(Lottos lottos, LottoReferee referee) {
        this.lottos = lottos;
        this.referee = referee;
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        for (Lotto lotto : lottos.getLottos()) {
            LottoResult result = referee.getLottoResult(lotto);
            if (result == null) {
                continue;
            }
            resultsStatistics.put(result, resultsStatistics.get(result) + 1);
        }

        return resultsStatistics;
    }
}

