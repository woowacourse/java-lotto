package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    @DisplayName("순위 찾기 테스트")
    void findRank() {
        assertThat(Rank.findRank(3,false).get())
                .isEqualTo(Rank.FIFTH_RANK);
    }
}
