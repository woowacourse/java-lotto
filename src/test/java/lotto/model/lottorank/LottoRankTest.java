package lotto.model.lottorank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @Test
    void 낙첨() {
        assertThat(LottoRank.findRank(0, false)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRank(1, false)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRank(2, false)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRank(0, true)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRank(1, true)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRank(2, true)).isEqualTo(LottoRank.MISS);
    }

    @Test
    void _5등() {
        assertThat(LottoRank.findRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.findRank(3, true)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    void _4등() {
        assertThat(LottoRank.findRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.findRank(4, true)).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    void _3등() {
        assertThat(LottoRank.findRank(5, false)).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void _2등() {
        assertThat(LottoRank.findRank(5, true)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void _1등() {
        assertThat(LottoRank.findRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.findRank(6, true)).isEqualTo(LottoRank.FIRST);
    }
}
