package lotto.domain;

import static lotto.domain.MatchRank.getMatchRank;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchRankTest {

    @DisplayName("getMatchStatistics 정상 작동 테스트")
    @ParameterizedTest
    @CsvSource({
            "0, false, NO_MATCH",
            "1, false, NO_MATCH",
            "2, false, NO_MATCH",
            "3, false, MATCH_THREE",
            "4, false, MATCH_FOUR",
            "5, false, MATCH_FIVE",
            "5, true, MATCH_BONUS",
            "6, false, MATCH_SIX",
    })
    void testGetMatchStatistics(int matchCount, boolean isBonusMatched, String expectedRankStr) {
        MatchRank expectedRank = MatchRank.valueOf(expectedRankStr);

        MatchRank matchRank = getMatchRank(new MatchResult(matchCount, isBonusMatched));
        assertThat(matchRank).isEqualTo(expectedRank);
    }
}