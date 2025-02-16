package lotto.domain;

import static lotto.domain.MatchInfo.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchInfoTest {

    @DisplayName("당첨 번호가 없거나 2개 이하인 경우 NO_MATCH를 반환한다")
    @ParameterizedTest
    @CsvSource({
        "0, false",
        "1, false",
        "2, false"
    })
    void test_ReturnNoMatch_MatchNumberIsLessThanStandard(int matchCount, boolean hasBonus) {
        assertThat(MatchInfo.getMatchInfo(matchCount, hasBonus)).isEqualTo(NO_MATCH);
    }

    @DisplayName("당첨 번호가 3개 이상인 경우 해당하는 등수를 반환한다")
    @ParameterizedTest
    @CsvSource({
        "3, false, MATCH_THREE",
        "4, false, MATCH_FOUR",
        "5, false, MATCH_FIVE",
        "6, false, MATCH_SIX"
    })
    void test_ReturnCorrectRank(int matchCount, boolean hasBonus, MatchInfo expected) {
        assertThat(MatchInfo.getMatchInfo(matchCount, hasBonus)).isEqualTo(expected);
    }

    @DisplayName("5개 번호가 일치하고 보너스 번호가 일치하면 2등을 반환한다")
    @Test
    void test_ReturnMatchBonus_CorrectMatchNumberWithBonus() {
        assertThat(MatchInfo.getMatchInfo(5, true)).isEqualTo(MATCH_BONUS);
    }

    @DisplayName("유효하지 않은 입력값에 대해 NO_MATCH를 반환한다")
    @ParameterizedTest
    @CsvSource({
        "-1, false",
        "7, false",
        "8, true"
    })
    void test_ReturnNoMatch_InvalidInput(int matchCount, boolean hasBonus) {
        assertThat(MatchInfo.getMatchInfo(matchCount, hasBonus)).isEqualTo(NO_MATCH);
    }
}
