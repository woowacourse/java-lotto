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
        Map<MatchResult, Integer> matchResults = new HashMap<>();
        for (Lotto lotto : lottos) {
            MatchResult lottoMatchResult = lotto.findMatchResult(winningNumbers, bonusNumber);
            int matchCount = matchResults.get(lottoMatchResult);
            matchResults.put(lottoMatchResult, matchCount++);
        }
        return matchResults;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
