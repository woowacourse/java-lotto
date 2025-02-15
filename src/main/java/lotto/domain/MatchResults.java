package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchResults {

    private final List<MatchResultDto> matchResults;

    public MatchResults(List<MatchResultDto> matchResults) {
        this.matchResults = matchResults;
    }

    public Map<LottoRank, Integer> getWinningResult() {
        Map<LottoRank, Integer> winningInfo = new HashMap<>();

        for (MatchResultDto matchResult : matchResults) {
            LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResult);
            winningInfo.put(lottoRank, winningInfo.getOrDefault(lottoRank, 0) + 1);
        }
        return winningInfo;
    }
}
