package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("당첨 번호의 범위는 1 ~ 45이다.")
    @Test
    void 당첨_번호_범위_정상() {
        // given
        int winningNumber = 1;

        // when & then
        assertThatCode(() -> new WinningNumber(winningNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호의 범위가 1 ~ 45이외이면 예외를 던진다.")
    @Test
    void 당첨_번호_범위_이외() {
        // given
        int winningNumber = 0;

        // when & then
        assertThatThrownBy(() -> new WinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호의 범위는 1 ~ 45 사이입니다.");
    }

    @DisplayName("해당 당첨 번호가 보너스볼인지 여부를 확인한다.")
    @Test
    void 담청_번호가_보너스_볼인지_확인() {
        // given
        WinningNumber winningNumber = new WinningNumber(1, BallType.BONUS);

        // when
        boolean result = winningNumber.isBonus();

        // then
        assertThat(result).isTrue();
    }
}