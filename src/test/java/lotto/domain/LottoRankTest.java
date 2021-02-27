package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRankTest {

    @DisplayName("맞는 숫자를 넣으면 알맞는 당첨 순위를 반환해야한다")
    @Test
    void 당첨_순위_테스트() {
        // given, when
        long noMatch = 2;
        long fifthMatch = 3;
        long fourthMatch = 4;
        long thirdMatch = 5;
        long firstMatch = 6;
        boolean isMatchBonus = false;

        // then
        assertThat(LottoRank.valueOf(noMatch, isMatchBonus)).isSameAs(LottoRank.NO_MATCH);
        assertThat(LottoRank.valueOf(fifthMatch, isMatchBonus)).isSameAs(LottoRank.FIFTH);
        assertThat(LottoRank.valueOf(fourthMatch, isMatchBonus)).isSameAs(LottoRank.FOURTH);
        assertThat(LottoRank.valueOf(thirdMatch, isMatchBonus)).isSameAs(LottoRank.THIRD);
        assertThat(LottoRank.valueOf(firstMatch, isMatchBonus)).isSameAs(LottoRank.FIRST);
    }

    @DisplayName("5개가 맞고, 보너스 번호도 맞으면 SECOND를 반환해야한다")
    @Test
    void SECOND_당첨_테스트() {
        // given, when
        long numOfMatch = 5;
        boolean isMatchBonus = true;

        // then
        assertThat(LottoRank.valueOf(numOfMatch, isMatchBonus)).isSameAs(LottoRank.SECOND);
    }

}