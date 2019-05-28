package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RankTest {
    @Test
    void SECOND_THIRD_등수_확인() {
        assertThat(Rank.SECOND).isEqualTo(Rank.valueOf(5, true));
        assertThat(Rank.THIRD).isEqualTo(Rank.valueOf(5, false));
    }
}
