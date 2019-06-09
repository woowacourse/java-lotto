package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Test
    void getTestA() {
        assertThat(LottoRank.valueOf(5, true).get()).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void getTestB() {
        assertThat(LottoRank.valueOf(5, false).get()).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void getTestC() {
        assertThat(LottoRank.valueOf(4, true).get()).isEqualTo(LottoRank.FOURTH);
    }
}