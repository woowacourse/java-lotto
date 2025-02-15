package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {
    @DisplayName("6개의 고유한 번호가 아니라면 예외를 던진다")
    @MethodSource("returnWrongWinningNumbers")
    @ParameterizedTest
    void _6개의_고유한_번호가_아니라면_예외를_던진다(List<Integer> winningNumbers) {
        assertThatThrownBy(() -> new WinningNumbers(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 고유한 번호를 입력해야 합니다.");
    }

    static Stream<Arguments> returnWrongWinningNumbers() {
        return Stream.of(
                Arguments.arguments(List.of("1", "2", "3", "4", "5")),
                Arguments.arguments(List.of("1", "2", "3", "4", "5", "6", "7")),
                Arguments.arguments(List.of("1", "2", "3", "4", "6", "6"))
        );
    }
}
