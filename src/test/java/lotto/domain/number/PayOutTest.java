package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PayOutTest {

    @Test
    @DisplayName("숫자를 입력 받는다.")
    void inputPayOutNumber() {
        PayOut payOut = PayOut.valueOf("10000");
        assertThat(payOut.toInt()).isEqualTo(10000);
    }

    @Test
    @DisplayName("음수를 입력하면 예외")
    void inputNegativeOrZeroPayOutNumber() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> PayOut.valueOf("-1")
        ).withMessage("입력값이 양수가 아닙니다.");

        assertThatIllegalArgumentException().isThrownBy(
            () -> PayOut.valueOf("0")
        ).withMessage("입력값이 양수가 아닙니다.");
    }

    @ParameterizedTest
    @DisplayName("입력 금액에 따른 게임 횟수를 반환한다.")
    @CsvSource(value = {"14000:14", "11231:11", "1:0", "10101:10"}, delimiter = ':')
    void getGameCount(String input, int expected) {
        PayOut payOut = PayOut.valueOf(input);
        assertThat(payOut.getGameCount()).isEqualTo(expected);
    }
}