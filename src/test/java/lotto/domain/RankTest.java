package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    @DisplayName("랭크 테스트")
    void testRankOf() {
        int countOfMatch = 5;

        assertThat(Rank.rankOf(countOfMatch, true)).isEqualTo(Rank.SECOND);
    }
}
