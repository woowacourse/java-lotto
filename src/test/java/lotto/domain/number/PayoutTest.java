package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PayoutTest {

    @Test
    @DisplayName("숫자를 입력 받는다.")
    void inputPayOutNumber() {
        Payout payout = Payout.valueOf("2147483647");
        assertThat(payout.unwrap()).isEqualTo(2147483647);
    }

    @ParameterizedTest
    @DisplayName("양수가 아닌 수를 예외")
    @ValueSource(strings = {"-1", "0"})
    void inputNegativeOrZeroPayOutNumber(String input) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Payout.valueOf(input)
        ).withMessage("입력값이 양수가 아닙니다.");
    }

    @ParameterizedTest
    @DisplayName("Integer 로 받을 수 없는 입력일 경우 예외")
    @ValueSource(strings = {"ab", "2147483648"})
    void inputString(String input) {
        assertThatIllegalArgumentException().isThrownBy(
            () -> Payout.valueOf(input)
        ).withMessage("입력이 숫자가 아니거나 Integer 범위를 벗어났습니다.");
    }

    @ParameterizedTest
    @DisplayName("입력 금액에 따른 게임 횟수를 반환한다.")
    @CsvSource(value = {"14000:14", "11231:11", "1:0", "10101:10"}, delimiter = ':')
    void getGameCount(String input, int expected) {
        Payout payout = Payout.valueOf(input);
        assertThat(expected).isEqualTo(payout.getNumberOfStuff(1000));
    }
}