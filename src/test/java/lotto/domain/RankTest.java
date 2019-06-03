package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RankTest {
    @Test
    void MISS_확인() {
        assertThat(Rank.MISS).isEqualTo(Rank.valueOf(2, false));
    }

    @Test
    void SECOND_확인() {
        assertThat(Rank.SECOND).isEqualTo(Rank.valueOf(5, true));
    }

    @Test
    void THIRD_확인() {
        assertThat(Rank.THIRD).isEqualTo(Rank.valueOf(5, false));
    }
}
