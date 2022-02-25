package lotto.receiver;

import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyReceiverTest {

    @Test
    @DisplayName("입력된 구입금액을 검증하여 정수로 반환")
    void receive() {
        Assertions.assertThat(MoneyReceiver.receive("14000")).isEqualTo(14000);
    }

    @ParameterizedTest(name = "음수가 입력될 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"-1", "-1200", "-3000"})
    void checkPositive(String input) {
        Assertions.assertThatThrownBy(() -> MoneyReceiver.receive(input))
                .isInstanceOf(MoneyException.class)
                .hasMessage("구입금액이 음수일 수 없습니다.");
    }

    @ParameterizedTest(name = "1000원 단위가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"100", "1200", "1234"})
    void checkUnit(String input) {
        Assertions.assertThatThrownBy(() -> MoneyReceiver.receive(input))
                .isInstanceOf(MoneyException.class)
                .hasMessage("구입금액은 1000원 단위만 가능합니다.");
    }
}
