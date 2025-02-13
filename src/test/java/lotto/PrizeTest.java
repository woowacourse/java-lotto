package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrizeTest {
    @DisplayName("일치 개수와 보너스 번호 일치 여부에 따라서 Prize를 반환한다")
    @CsvSource(value = {"6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:false:FOURTH", "3:false:FIFTH"},
            delimiterString = ":")
    @ParameterizedTest
    void 일치_개수와_보너스_번호_일치_여부에_따라서_Prize를_반환한다(int matchCount, boolean hasBonusNumber, Prize expected) {
        Prize result = Prize.getPrize(matchCount, hasBonusNumber);
        assertThat(result).isEqualTo(expected);
    }
}