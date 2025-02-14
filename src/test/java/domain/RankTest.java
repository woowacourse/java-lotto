package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {
    @Test
    void fromResult() {
        assertThat(Rank.fromResult(6, true)).isEqualTo(Rank.FIRST);
        assertThat(Rank.fromResult(6, false)).isEqualTo(Rank.FIRST);

        assertThat(Rank.fromResult(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.fromResult(5, false)).isEqualTo(Rank.THIRD);

        assertThat(Rank.fromResult(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.fromResult(4, false)).isEqualTo(Rank.FOURTH);

        assertThat(Rank.fromResult(3, true)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.fromResult(3, false)).isEqualTo(Rank.FIFTH);

        assertThat(Rank.fromResult(2, true)).isEqualTo(Rank.NONE);
        assertThat(Rank.fromResult(2, false)).isEqualTo(Rank.NONE);
    }
}
