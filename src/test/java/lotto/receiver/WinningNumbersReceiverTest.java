package lotto.receiver;

import lotto.exception.WinningNumbersException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersReceiverTest {

    @ParameterizedTest(name = "숫자가 6개가 아닌 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void checkSize(String input) {
        Assertions.assertThatThrownBy(() -> WinningNumbersReceiver.receive(input))
                .isInstanceOf(WinningNumbersException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @ParameterizedTest(name = "숫자가 중복되는 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1, 1, 3, 4, 5, 6", "1, 2, 3, 4, 2, 6"})
    void checkNaturalNumber(String input) {
        Assertions.assertThatThrownBy(() -> WinningNumbersReceiver.receive(input))
                .isInstanceOf(WinningNumbersException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }
}
