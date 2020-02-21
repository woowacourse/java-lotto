package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    @DisplayName("순위 계산 테스트")
    void calculateRank() {
        Rank.findRank(3, false);
        assertThat(Rank.FIFTH_RANK.count)
                .isEqualTo(1);
    }
}
