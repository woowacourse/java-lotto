package lotto.domain.lottoresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoRankTest {
    @Test
    void 두개_이하_일치_로또() {
        assertThat(LottoRank.rankOf(2, false)).isEqualTo(LottoRank.FAIL);
    }

    @Test
    void 세개_일치_로또() {
        assertThat(LottoRank.rankOf(3, false)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    void 다섯개_보너스_일치_로또() {
        assertThat(LottoRank.rankOf(5, true)).isEqualTo(LottoRank.SECOND);
    }
}