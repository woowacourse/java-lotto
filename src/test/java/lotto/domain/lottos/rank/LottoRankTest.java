package lotto.domain.lottos.rank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @Test
    @DisplayName("당첨 숫자 6개 매칭이면 FIRST_PLACE 반환")
    public void matchCount_6() {
        assertThat(LottoRank.matchLottoRank(6, false))
                .isEqualTo(LottoRank.FIRST_PLACE);
    }

    @Test
    @DisplayName("당첨 숫자 5개와 보너스 숫자가 일치하면 SECOND_PLACE 반환")
    public void matchCount_5_and_matchBonusNumber() {
        assertThat(LottoRank.matchLottoRank(5, true))
                .isEqualTo(LottoRank.SECOND_PLACE);
    }

    @Test
    @DisplayName("당첨 숫자 5개 매칭이면 THIRD_PLACE 반환")
    public void matchCount_5() {
        assertThat(LottoRank.matchLottoRank(5, false))
                .isEqualTo(LottoRank.THIRD_PLACE);
    }

    @Test
    @DisplayName("당첨 숫자 4개 매칭이면 FOURTH_PLACE 반환")
    public void matchCount_4() {
        assertThat(LottoRank.matchLottoRank(4, false))
                .isEqualTo(LottoRank.FOURTH_PLACE);
    }

    @Test
    @DisplayName("당첨 숫자 3개 매칭이면 FIFTH_PLACE 반환")
    public void matchCount_3() {
        assertThat(LottoRank.matchLottoRank(3, false))
                .isEqualTo(LottoRank.FIFTH_PLACE);
    }

    @ParameterizedTest
    @ValueSource(ints = { 2,1,0 })
    @DisplayName("당첨 숫자 2개 이하 매칭이면 SIXTH_PLACE 반환")
    public void matchCount_2or1or0(int matchCount) {
        assertThat(LottoRank.matchLottoRank(matchCount, false))
                .isEqualTo(LottoRank.SIXTH_PLACE);
    }

}