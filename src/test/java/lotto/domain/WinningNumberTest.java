package lotto.domain;

import static lotto.domain.enumeration.BallType.BONUS;
import static lotto.domain.enumeration.BallType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WinningNumberTest {

    @DisplayName("해당 당첨 번호가 보너스볼인지 여부를 확인한다.")
    @Test
    void 당첨_번호가_보너스_볼인지_확인() {
        // given
        WinningNumber winningNumber = new WinningNumber(1, BONUS);

        // when
        boolean result = winningNumber.isBonus();
        
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("1~45 사이의 숫자인지 검증한다.")
    @Test
    public void 번호가_범위에_맞는지_검증_0() {
        // given & when & then
        assertThatThrownBy(() -> new WinningNumber(0, NORMAL))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~45 사이의 숫자인지 검증한다.")
    @Test
    public void 번호가_범위에_맞는지_검증_46() {
        // given & when & then
        assertThatThrownBy(() -> new WinningNumber(46, NORMAL))
                .isInstanceOf(IllegalArgumentException.class);
    }
}