package lotto.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PayOutTest {

    @Test
    @DisplayName("숫자를 입력 받는다.")
    void inputPayOutNumber() {
        PayOut payOut = new PayOut(new Number(10000));
        assertThat(payOut.getPayOut().equals(new Number(10000))).isTrue();

        payOut = new PayOut(new Number("10000"));
        assertThat(payOut.getPayOut().equals(new Number(10000))).isTrue();
    }

    @Test
    @DisplayName("음수를 입력하면 예외")
    void inputNegativeOrZeroPayOutNumber() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new PayOut(new Number(-1))
        ).withMessage("입력값이 양수가 아닙니다.");

        assertThatIllegalArgumentException().isThrownBy(
            () -> new PayOut(new Number(0))
        ).withMessage("입력값이 양수가 아닙니다.");
    }

    @ParameterizedTest
    @DisplayName("입력 금액에 따른 게임 횟수를 반환한다.")
    @CsvSource(value = {"14000:14", "11231:11", "1:0", "10101:10"}, delimiter = ':')
    void getGameCount(int input, int expected) {
        PayOut payOut = new PayOut(new Number(input));
        assertThat(payOut.getGameCount()).isEqualTo(expected);
    }
}