package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    void matching_개수에_따라_해당_등수의_Rank를_반환() {
        assertThat(Rank.valueOf(0)).isEqualByComparingTo(Rank.MISS);
        assertThat(Rank.valueOf(1)).isEqualByComparingTo(Rank.MISS);
        assertThat(Rank.valueOf(3)).isEqualByComparingTo(Rank.FIFTH);
        assertThat(Rank.valueOf(4)).isEqualByComparingTo(Rank.FOURTH);
        assertThat(Rank.valueOf(5)).isEqualByComparingTo(Rank.THIRD);
        assertThat(Rank.valueOf(6)).isEqualByComparingTo(Rank.FIRST);
    }
}