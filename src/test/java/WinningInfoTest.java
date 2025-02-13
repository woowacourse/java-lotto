import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningInfoTest {

    @DisplayName("당첨 결과에 맞는 결과 객체가 생성된다.")
    @ParameterizedTest
    @CsvSource(value = {"6,false,2_000_000_000"
            , "5,true,30_000_000"
            , "5,false,1_500_000"
            , "4,false,50_000"
            , "3,false,5_000"})
    void test1(int matchedCount, boolean isBonusMatched, int expected) {
        // given

        // when
        WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);

        // then
        assertThat(winningInfo.getPrice()).isEqualTo(expected);
    }

    @DisplayName("낮은 등수부터 리턴할 수 있다.")
    @Test
    void test2() {
        // given

        // when
        List<WinningInfo> sortedValues = WinningInfo.getSortedValues();

        // then
        assertThat(sortedValues).containsExactly(
                WinningInfo.FIFTH_PRIZE,
                WinningInfo.FOURTH_PRIZE,
                WinningInfo.THIRD_PRIZE,
                WinningInfo.SECOND_PRIZE,
                WinningInfo.FIRST_PRIZE
        );
    }
}