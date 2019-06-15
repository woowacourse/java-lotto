package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void MISS_확인() {
        assertThat(Rank.MISS).isEqualTo(Rank.valueOf(2, false));
    }

    @Test
    void FIRST_확인() {
        assertThat(Rank.FIRST).isEqualTo(Rank.valueOf(6, false));
        assertThat(Rank.FIRST).isEqualTo(Rank.valueOf(6, true));
    }

    @Test
    void SECOND_확인() {
        assertThat(Rank.SECOND).isEqualTo(Rank.valueOf(5, true));
        assertThat(Rank.SECOND).isNotEqualTo(Rank.valueOf(5, false));
    }

    @Test
    void THIRD_확인() {
        assertThat(Rank.THIRD).isEqualTo(Rank.valueOf(5, false));
        assertThat(Rank.THIRD).isNotEqualTo(Rank.valueOf(5, true));
    }

    @Test
    void FOURTH_확인() {
        assertThat(Rank.FOURTH).isEqualTo(Rank.valueOf(4, false));
        assertThat(Rank.FOURTH).isEqualTo(Rank.valueOf(4, true));
    }

    @Test
    void FIFTH_확인() {
        assertThat(Rank.FIFTH).isEqualTo(Rank.valueOf(3, false));
        assertThat(Rank.FIFTH).isEqualTo(Rank.valueOf(3, true));
    }
}
