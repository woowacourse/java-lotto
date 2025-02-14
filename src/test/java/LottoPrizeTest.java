import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoPrizeTest {

    @DisplayName("당첨 결과에 맞는 결과 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"6,false,2_000_000_000"
            , "5,true,30_000_000"
            , "5,false,1_500_000"
            , "4,false,50_000"
            , "3,false,5_000"})
    void generateCorrectResult(int matchedCount, boolean isBonusMatched, int expected) {
        // given

        // when
        LottoPrize lottoPrize = LottoPrize.of(matchedCount, isBonusMatched);

        // then
        assertThat(lottoPrize.getPrice()).isEqualTo(expected);
    }

    @DisplayName("당첨 번호와 보너스 번호 모두 매칭되는 경우 알맞는 결과 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "4, true, 50_000",
            "3, true, 5_000"
    })
    void returnCorrectResultExceptCase(int matchedCount, boolean isBonusMatched, int expected) {
        // given

        // when
        LottoPrize lottoPrize = LottoPrize.of(matchedCount, isBonusMatched);

        // then
        assertThat(lottoPrize.getPrice()).isEqualTo(expected);
    }

    @DisplayName("낮은 등수부터 리턴할 수 있다.")
    @Test
    void returnSortedValues() {
        // given

        // when
        List<LottoPrize> sortedValues = LottoPrize.getSortedValues();

        // then
        assertThat(sortedValues).containsExactly(
                LottoPrize.FIFTH,
                LottoPrize.FOURTH,
                LottoPrize.THIRD,
                LottoPrize.SECOND,
                LottoPrize.FIRST
        );
    }
}
