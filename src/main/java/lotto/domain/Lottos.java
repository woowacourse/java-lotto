package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<MatchResult, Integer> createMatchResults(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<MatchResult, Integer> matchResults = setUpMatchResults();
        for (Lotto lotto : lottos) {
            MatchResult lottoMatchResult = lotto.findMatchResult(winningNumbers, bonusNumber);
            updateMatchResults(matchResults, lottoMatchResult);
        }
        return matchResults;
    }

    private Map<MatchResult, Integer> setUpMatchResults() {
        Map<MatchResult, Integer> matchResults = new HashMap<>();
        MatchResult[] results = MatchResult.values();
        for (MatchResult result : results) {
            matchResults.put(result, 0);
        }
        return matchResults;
    }

    private void updateMatchResults(Map<MatchResult, Integer> matchResults, MatchResult lottoMatchResult) {
        if (lottoMatchResult == null) {
            return;
        }
        int matchCount = matchResults.get(lottoMatchResult);
        matchResults.put(lottoMatchResult, ++matchCount);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
