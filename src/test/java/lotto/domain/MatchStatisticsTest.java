package lotto.domain;

import static lotto.domain.MatchStatistics.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchStatisticsTest {

    @Test
    @DisplayName("getMatchStatistics 정상 작동 테스트")
    void testGetMatchStatistics() {
        assertThat(getMatchStatistics(3, false)).isEqualTo(MATCH_THREE);
        assertThat(getMatchStatistics(5, false)).isEqualTo(MATCH_FIVE);
        assertThat(getMatchStatistics(5, true)).isEqualTo(MATCH_BONUS);
    }
}