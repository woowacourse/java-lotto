package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoRankTest {
    @Test
    void 두개_이하_일치_로또() {
        assertThat(LottoRank.rankOf(2)).isEqualTo(LottoRank.FAIL);
    }

    @Test
    void 세개_일치_로또() {
        assertThat(LottoRank.rankOf(3)).isEqualTo(LottoRank.FOURTH);
    }
}