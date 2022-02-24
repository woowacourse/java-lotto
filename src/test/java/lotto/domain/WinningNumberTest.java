package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WinningNumberTest {

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