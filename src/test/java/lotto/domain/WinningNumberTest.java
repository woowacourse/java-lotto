package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberTest {

    private static final int WINNING_NUMBER_COUNT = 6;
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;

    private WinningNumber winningNumber;

    @Test
    @DisplayName("당첨 번호와 일치하는 번호의 개수를 센다.")
    void countMatchingNumber() {
        winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 45));
        List<Integer> numbers = List.of(1, 2, 3, 5, 7, 45);
        int actualCount = winningNumber.countMatchingNumber(numbers);
        int expectedCount = 5;

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @DisplayName("당첨 번호의 개수가 6개가 아닐 경우, 예외를 발생시킨다.")
    @MethodSource("winningNumbers")
    void validateSize(List<Integer> winningNumbers) {
        assertThatThrownBy(() -> new WinningNumber(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 " + WINNING_NUMBER_COUNT + "개여야 합니다.");
    }

    static Stream<Arguments> winningNumbers() {
        return Stream.of(
                Arguments.arguments(List.of(1)),
                Arguments.arguments(List.of(1, 2)),
                Arguments.arguments(List.of(1, 2, 3)),
                Arguments.arguments(List.of(1, 2, 3, 4)),
                Arguments.arguments(List.of(1, 2, 3, 4, 5))
        );
    }

    @Test
    @DisplayName("당첨 번호 중 중복된 수가 있을 경우, 예외를 발생시킨다")
    void validateDuplicate() {
        assertThatThrownBy(() -> new WinningNumber(List.of(1,2,3,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호 중 하나라도 1~45 범위 내 숫자가 아닌 경우, 예외를 발생시킨다.")
    void validateRange() {
        assertThatThrownBy(() -> new WinningNumber(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 " + LOTTO_START_NUMBER
                        + "~" + LOTTO_END_NUMBER + "사이의 수여야 합니다.");
    }
}
