package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Test
    void getTestA() {
        assertThat(LottoRank.get(5, true).get()).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void getTestB() {
        assertThat(LottoRank.get(5, false).get()).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void getTestC() {
        assertThat(LottoRank.get(4, true).get()).isEqualTo(LottoRank.FOURTH);
    }
}