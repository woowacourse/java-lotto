package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void 결과가_SECOND() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 결과가_MISS() {
        assertThat(Rank.valueOf(2, false)).isEqualTo(Rank.MISS);
    }

    @Test
    void 결과가_FIRST() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
    }
}
