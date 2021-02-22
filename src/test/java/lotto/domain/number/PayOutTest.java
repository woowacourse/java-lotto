package lotto.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PayOutTest {

    @Test
    @DisplayName("숫자를 입력 받는다.")
    void inputPayOutNumber() {
        PayOut payOut = new PayOut(10000);
        assertThat(payOut.getValueAsInt()).isEqualTo(10000);

        payOut = new PayOut("10000");
        assertThat(payOut.getValueAsInt()).isEqualTo(10000);
    }

    @Test
    @DisplayName("음수를 입력하면 예외")
    void inputNegativeOrZeroPayOutNumber() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new PayOut(new Number(-1))
        ).withMessage("입력값이 양수가 아닙니다.");
    }

    @Test
    @DisplayName("뺄셈을 하면 빼진 수의 PayOut 객체를 반환한다.")
    void subtract() {
        PayOut _10000 = new PayOut(10000);
        PayOut _5000 = new PayOut(5000);

        assertThat(_10000.subtract(_5000)).isEqualTo(_5000);
    }
}