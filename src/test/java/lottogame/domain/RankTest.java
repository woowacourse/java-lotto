package lottogame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("맞춘 개수별 등수 확인")
    void testGetPrice() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.RANK1);
        assertThat(Rank.of(5, true)).isEqualTo(Rank.RANK2);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.RANK3);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.RANK4);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.RANK5);
    }
}
