package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @Test
    @DisplayName("우승 티켓 검증 테스트")
    void findLottoResult() {
        assertThat(LottoRank.findLottoRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.findLottoRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.findLottoRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.findLottoRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.findLottoRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.findLottoRank(2, false)).isEqualTo(LottoRank.FAILED);
        assertThat(LottoRank.findLottoRank(1, false)).isEqualTo(LottoRank.FAILED);
        assertThat(LottoRank.findLottoRank(0, false)).isEqualTo(LottoRank.FAILED);
    }
}