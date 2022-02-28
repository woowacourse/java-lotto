package domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserBalanceTest {

    @DisplayName("1000 미만이거나 1000의 배수가 아닌 입력 시 예외 발생")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(ints = {-1000, 100, 1500})
    void constructor_InvalidCurrency_ThrowsIllegalArgumentException(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UserBalance(value, 0))
                .withMessageMatching("구입금액은 1000원 이상이어야 하며 1000원 미만일 수 없습니다.");
    }

    @DisplayName("구입 가능한 로또 수 보다 많은 수동 로또를 입력 할 경우 예외 발생")
    @Test
    void constructor_InvalidLottoCount_ThrowsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UserBalance(1000, 2))
                .withMessageMatching("수동 입력 가능 횟수는 0번 이상 최대 구입 가능 이하여야 합니다.");
    }
}
