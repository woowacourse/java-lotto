import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
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
    void of_test(int matchedCount, boolean isBonusMatched, int expected) {
        // given

        // when
        WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);

        // then
        assertThat(winningInfo.getPrice()).isEqualTo(expected);
    }

}