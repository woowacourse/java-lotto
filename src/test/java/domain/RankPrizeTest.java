package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankPrizeTest {

    @DisplayName("일치하는 당첨 번호 갯수에 해당하는 상금 반환을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"6,FIRST", "4,FOURTH"})
    void find_winPrice_by_matchedCount(final int matchedCount, RankPrize rankPrize) {
        final RankPrize prize = RankPrize.of(matchedCount, false);

        assertThat(prize).isEqualTo(rankPrize);
    }

    @DisplayName("보너스볼 여부로 2등과 3등에 대한 구분을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"false, THIRD", "true, SECOND"})
    void find_second_or_third_prize_by_matchedCount(final boolean isBonusMatched, RankPrize rankPrize) {
        final RankPrize prize = RankPrize.of(5, isBonusMatched);

        assertThat(prize).isEqualTo(rankPrize);
    }
}
