import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankingTest {

    @ParameterizedTest
    @DisplayName("올바른 등수를 반환한다.")
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true, FOURTH",
            "3, false, FIFTH",
            "3, true, FIFTH",
            "2, false, LOSING",
    })
    void 올바른_등수를_반환한다(int correctCount, boolean isCorrectBonus, LottoRanking expectedRanking) {
        assertThat(LottoRanking.from(correctCount, isCorrectBonus)).isEqualTo(expectedRanking);
    }

}
