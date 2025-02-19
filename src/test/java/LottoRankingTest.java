import enums.LottoRanking;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoRankingTest {

    @ParameterizedTest
    @CsvSource({
            //given
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
        //when & then
        assertThat(LottoRanking.from(correctCount, isCorrectBonus)).isEqualTo(expectedRanking);
    }

}
