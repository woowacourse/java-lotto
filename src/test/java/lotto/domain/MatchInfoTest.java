package lotto.domain;

import static lotto.domain.MatchInfo.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchInfoTest {

    @Test
    @DisplayName("getMatchStatistics 정상 작동 테스트")
    void testGetMatchInfo() {
        assertThat(getMatchInfo(3, false)).isEqualTo(MATCH_THREE);
        assertThat(getMatchInfo(5, false)).isEqualTo(MATCH_FIVE);
        assertThat(getMatchInfo(5, true)).isEqualTo(MATCH_BONUS);
    }
}
