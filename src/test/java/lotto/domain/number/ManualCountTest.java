package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ManualCountTest {

    @ParameterizedTest
    @DisplayName("입력이 숫자가 아니거나 Integer 범위 밖인 경우 예외처리")
    @ValueSource(strings = {"ab", "", "2147483648"})
    void newInstanceWithString(String input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> ManualCount.valueOf(input))
            .withMessage("입력이 숫자가 아니거나 Integer 범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("입력이 음수일 경우 예외처리")
    void newInstanceWithNegative() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> ManualCount.valueOf("-1"))
            .withMessage("불가능한 로또 개수 입니다.");
    }

    @ParameterizedTest
    @DisplayName("정상적인 수동 개수 입력")
    @ValueSource(strings = {"0", "15", "2147483647"})
    void newInstance() {
        assertThatCode(() -> ManualCount.valueOf("0")).doesNotThrowAnyException();
    }
}
