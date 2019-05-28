package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {
    @Test
    void getTestA() {
        assertThat(LottoRank.get(5, true)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void getTestB() {
        assertThat(LottoRank.get(5, false)).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void getTestC() {
        assertThat(LottoRank.get(4, true)).isEqualTo(LottoRank.FOURTH);
    }

}