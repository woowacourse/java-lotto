package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultsTest {
    private List<MatchResult> matchResults;
    private Map<MatchResult, Integer> expectedMatchResultCounter;

    @BeforeEach
    void setUp() {
        List<MatchResult> matchResults = new ArrayList<>();
        matchResults.add(MatchResult.FIVE_MATCH_WITH_BONUS_BALL);
        matchResults.add(MatchResult.THREE_MATCH);
        matchResults.add(MatchResult.FIVE_MATCH_WITH_BONUS_BALL);
        matchResults.add(MatchResult.FOUR_MATCH);
        matchResults.add(MatchResult.FOUR_MATCH);
        matchResults.add(MatchResult.THREE_MATCH);
        matchResults.add(MatchResult.THREE_MATCH);
        matchResults.add(MatchResult.FIVE_MATCH);
        this.matchResults = matchResults;

        Map<MatchResult, Integer> expectedMatchResultCounter = new HashMap<>();
        expectedMatchResultCounter.put(MatchResult.THREE_MATCH, 3);
        expectedMatchResultCounter.put(MatchResult.FOUR_MATCH, 2);
        expectedMatchResultCounter.put(MatchResult.FIVE_MATCH, 1);
        expectedMatchResultCounter.put(MatchResult.FIVE_MATCH_WITH_BONUS_BALL, 2);
        this.expectedMatchResultCounter = expectedMatchResultCounter;
    }

    @DisplayName("로또 번호와 당첨 번호의 결과들을 통계로 낸 자료를 올바르게 만드는지 확인")
    @Test
    void createMatchResultCounterTest() {
        MatchResults results = new MatchResults(matchResults);
        assertThat(results.get()).isEqualTo(expectedMatchResultCounter);
    }

    @DisplayName("총수익을 올바르게 계산하는지 확인")
    @Test
    void calculateTotalEarningsTest() {
        MatchResults results = new MatchResults(matchResults);
        int result = results.calculateTotalEarnings();
        assertThat(result).isEqualTo(61615000);
    }
}
