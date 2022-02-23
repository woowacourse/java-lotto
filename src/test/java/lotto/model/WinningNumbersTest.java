package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    @Test
    @DisplayName("지난 주 당첨 번호 개수가 6개 미만인 경우 예외 처리")
    void validateNumberOfWinningNumbersUnder6Test() {
        assertThatThrownBy(() -> {
            new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지난 주 당첨 번호 개수는 6개로 입력해주세요.");
    }

    @Test
    @DisplayName("지난 주 당첨 번호 개수가 6개 초과인 경우 예외 처리")
    void validateNumberOfWinningNumbersOver6Test() {
        assertThatThrownBy(() -> {
            new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지난 주 당첨 번호 개수는 6개로 입력해주세요.");
    }

    @Test
    @DisplayName("지난 주 당첨 번호의 범위가 1~45가 아닌 경우 예외 처리")
    void validateRangeWinningNumbersTest() {
        assertThatThrownBy(() -> {
            new WinningNumbers(Arrays.asList(1, 2, 3, 4, 55, 6));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호의 범위는 1 ~ 45 사이로 입력해주세요.");
    }

    @Test
    @DisplayName("지난 주 당첨 번호 중 중복된 값이 입력될 경우 예외 처리")
    void validateDuplicationWinningNumbersTest() {
        assertThatThrownBy(() -> {
            new WinningNumbers(Arrays.asList(1, 2, 3, 3, 4, 6));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호에 중복이 존재합니다.");
    }
}
