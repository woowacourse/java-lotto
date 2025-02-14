package lotto.domain;

import static lotto.domain.MatchRank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchRankTest {

    @Test
    @DisplayName("getMatchStatistics 정상 작동 테스트")
    void testGetMatchStatistics() {
        assertThat(getMatchRank(3, false)).isEqualTo(MATCH_THREE);
        assertThat(getMatchRank(5, false)).isEqualTo(MATCH_FIVE);
        assertThat(getMatchRank(5, true)).isEqualTo(MATCH_BONUS);
    }
}