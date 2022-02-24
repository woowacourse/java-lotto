package vo;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningCountTest {
    @Test
    @DisplayName("WinningCount를 음수로 생성할 경우 IAE 발생")
    void creatingWinningCountWithMinusIntShouldFail() {
        assertThatThrownBy(() -> new WinningCount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 횟수는 음수일 수 없습니다.");
    }

    @ParameterizedTest(name = "0 또는 양수로 WinningCount생성 시 오류가 없어야 함 - {0}")
    @ValueSource(ints = {0, 1})
    void creatingWinningCountWithZeroOrPositiveIntShouldSuccess(int count) {
        assertThatCode(() -> new WinningCount(count))
                .doesNotThrowAnyException();
    }
}
