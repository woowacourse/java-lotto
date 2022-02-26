package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    @Test
    @DisplayName("당첨 번호에 보너스 볼이 있으면 예외 발생")
    public void checkBonusBallInWinningNumbersTest() {
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(4);
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(
                () -> new WinningLotto(lotto, bonusBall)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
