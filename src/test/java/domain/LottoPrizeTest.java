package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoPrizeTest {
    
    @ParameterizedTest
    @CsvSource({
            "6, true, FIRST", "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, true, FOURTH", "4, false, FOURTH",
            "3, true, FIFTH", "3, false, FIFTH",
            "2, true, NONE", "2, false, NONE",
            "1, true, NONE", "1, false, NONE",
            "0, true, NONE", "0, false, NONE",
    })
    void 로또_당첨_등수를_확인한다(int matchCount, boolean isBonusMatch, LottoPrize result) {
        // expected
        assertThat(LottoPrize.match(matchCount, isBonusMatch)).isEqualTo(result);
    }
    
    @ParameterizedTest
    @CsvSource({
            "8, true", "8, false",
            "7, true", "7, false",
    })
    void 존재하지_않는_당첨_정보이면_예외가_발생한다(int matchCount, boolean isBonusMatch) {
        // expected
        assertThatThrownBy(() -> LottoPrize.match(matchCount, isBonusMatch))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("해당하는 당첨 정보가 존재하지 않습니다.");
    }
}
