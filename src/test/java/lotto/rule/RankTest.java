package lotto.rule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("당첨 등수 테스트")
class RankTest {

    @DisplayName("매칭 개수와 보너스 볼 여부에 따라 당첨 등수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, NO_PRIZE",
            "1, false, NO_PRIZE",
            "0, false, NO_PRIZE"
    }, delimiter = ',')
    void classifyRankTest(int matchCount, boolean isBonusMatch, Rank expected) {
        Rank actual = Rank.classifyRank(matchCount, isBonusMatch);

        assertThat(actual)
                .isEqualTo(expected);
    }

    @DisplayName("보너스 볼 여부가 상관 없는 경우 무시한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "6, true, FIRST",
            "4, true, FOURTH",
            "3, true, FIFTH",
            "2, true, NO_PRIZE",
            "1, true, NO_PRIZE",
            "0, true, NO_PRIZE",
    }, delimiter = ',')
    void classifyRankIgnoreBonusTest(int matchCount, boolean isBonusMatch, Rank expected) {
        Rank actual = Rank.classifyRank(matchCount, isBonusMatch);

        assertThat(actual)
                .isEqualTo(expected);
    }
}
