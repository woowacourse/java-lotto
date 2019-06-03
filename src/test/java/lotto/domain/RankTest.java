package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RankTest {
    @Test
    void SECOND_THIRD_등수_확인() {
        assertThat(Rank.FIRST).isEqualTo(Rank.valueOf(6, true));
        assertThat(Rank.SECOND).isEqualTo(Rank.valueOf(5, true));
        assertThat(Rank.THIRD).isEqualTo(Rank.valueOf(5, false));
        assertThat(Rank.FOURTH).isEqualTo(Rank.valueOf(4, false));
        assertThat(Rank.FIFTH).isEqualTo(Rank.valueOf(3, true));
        assertThat(Rank.MISS).isEqualTo(Rank.valueOf(2, false));

    }
}
