package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {

    private static void assertInvokeExceptionWithMessageWhenCreateLottos(String input, String message) {
        assertThatThrownBy(() -> new Lottos(new LottoMoney("10000"), input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith(message);
    }

    @DisplayName("수동으로 구매할 로또 수의 입력값이 없을 때 예외가 발생하는지 확인")
    @NullAndEmptySource
    @ParameterizedTest
    void emptyValueExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateLottos(input, "수동으로 구매할 로또 수를 입력해 주세요.");
    }

    @DisplayName("수동으로 구매할 로또 수의 입력값이 숫자가 아닐 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1개", "세 줄", "two lottos", "5!!"})
    void notNumberExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateLottos(input, "수동으로 구매할 로또 수는 숫자만 입력 가능합니다.");
    }

    @DisplayName("수동으로 구매할 로또 수의 입력값이 0 이상 구매한 총 로또 수 이하가 아닐 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "11"})
    void notInRangeExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateLottos(input, "(=구매한 총 로또 수) 이하여야 합니다.");
    }
}
