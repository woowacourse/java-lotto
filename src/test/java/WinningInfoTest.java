import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningInfoTest {

    @ParameterizedTest
    @DisplayName("일치한 번호 개수, 보너스 번호 일치 여부가 주어졌을 때 당첨 결과에 맞는 WinningInfo 객체를 가져올 수 있는지 확인한다")
    @CsvSource(value = {"6,false,2_000_000_000"
            , "5,true,30_000_000"
            , "5,false,1_500_000"
            , "4,false,50_000"
            , "3,false,5_000"})
    void should_return_correct_WinningInfo_by_matchedCount_and_bonus(int matchedCount, boolean isBonusMatched,
                                                                     int expected) {
        // when
        WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);

        // then
        assertThat(winningInfo.getPrice()).isEqualTo(expected);
    }

    @Test
    @DisplayName("getSortedValues()가 낙첨을 제외한 낮은 등수부터 정렬된 List를 반환하는지 확인한다")
    void should_return_sorted_WinningInfo_list_excluding_None() {
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