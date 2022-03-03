package lotto.domain;

import static lotto.domain.enumeration.BallType.BONUS;
import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("해당 당첨 번호가 보너스볼인지 여부를 확인한다.")
    @Test
    void checkBonusBall() {
        // given
        WinningNumber winningNumber = new WinningNumber(LottoNumber.from(1), BONUS);

        // when
        boolean result = winningNumber.isBonus();
        
        // then
        assertThat(result).isTrue();
    }
}