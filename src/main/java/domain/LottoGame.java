package domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGame {

    private final Lottos lottos;
    private final LottoReferee referee;
    private final Map<LottoResult, Integer> resultsStatistics = Arrays
            .stream(LottoResult.values())
            .collect(Collectors.toMap(lottoResult -> lottoResult, i -> 0));

    public LottoGame(Lottos lottos, LottoReferee referee) {
        this.lottos = lottos;
        this.referee = referee;
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        for (Lotto lotto : lottos.getLottos()) {
            LottoResult result = referee.getLottoResult(lotto);
            if (result == null) continue;
            resultsStatistics.put(result, resultsStatistics.get(result) + 1);
        }

        return resultsStatistics;
    }
}

