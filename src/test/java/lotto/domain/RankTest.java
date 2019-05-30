package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RankTest {
    @Test
    void 일등() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 이등() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 삼등() {
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 사등() {
        assertThat(Rank.valueOf(4, false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 오등() {
        assertThat(Rank.valueOf(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 꽝() {
        assertThat(Rank.valueOf(1, false)).isEqualTo(Rank.MISS);
    }

    @Test
    void 유효하지_않은_값() {
        assertThrows(IllegalArgumentException.class, () -> Rank.valueOf(7, false));
    }
}
