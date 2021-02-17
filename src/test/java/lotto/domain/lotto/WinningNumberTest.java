package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {

    @Test
    @DisplayName(",를 기준으로 당첨번호를 입력받는다.")
    void getStatistics() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6", "7");
        LottoNumbers expected = new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(new Number(1)),
                    new LottoNumber(new Number(2)),
                    new LottoNumber(new Number(3)),
                    new LottoNumber(new Number(4)),
                    new LottoNumber(new Number(5)),
                    new LottoNumber(new Number(6))
                )
            );

        assertThat(expected).isEqualTo(winningNumber.getLottoNumbers());
    }

    @Test
    @DisplayName("하나의 숫자가 아닌 경우 예외")
    void getBonusNumberFromStringInput() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new WinningNumber("1,2,3,4,5,6", "1, 3")
        ).withMessage("보너스 볼의 하나의 숫자로 이루어져야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복이 되는 경우 예외")
    void duplicateBonusNumberWithLottoNumbers() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new WinningNumber("1,2,3,4,5,6", "3")
        ).withMessage("보너스 번호는 로또 번호와 달라야 합니다.");
    }
}