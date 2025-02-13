package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LottoRankTest {

    @Test
    void 로또티켓의_당첨_결과를_계산한다() {
        // when
        LottoRank rank = LottoRank.findByMatchCondition(5, true);

        // then
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void 로또티켓의_결과가_순위_밖이라면_null을_반환한다() {
        // when
        LottoRank rank = LottoRank.findByMatchCondition(2, false);

        // then
        assertThat(rank).isNull();
    }
}
