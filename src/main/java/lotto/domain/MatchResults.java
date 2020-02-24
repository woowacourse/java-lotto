package lotto.domain;

import java.util.HashMap;
import java.util.Map;

/* 각 등수 별로 몇 개의 우승 로또가 있는지 나타내는 클래스 */
public class MatchResults {
    private Map<WinningInfo, Integer> matchResults;

    public MatchResults(Map<WinningInfo, Integer> matchResult) {
        this.matchResults = matchResult;
    }

    public int getMatchCount(WinningInfo winningInfo) {
        return matchResults.getOrDefault(winningInfo, 0);
    }
}
