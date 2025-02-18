package domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
    @DisplayName("로또가 정상적으로 저장된다")
    @Test
    void saveLotto() {
        assertDoesNotThrow(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("로또 한 개는 여섯 개가 아닌 숫자로 이루어져 있을 시 예외가 발생한다")
    @ParameterizedTest
    @MethodSource
    void containsSixNumbersInlotto(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> containsSixNumbersInlotto() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(List.of(1, 2, 3, 4, 5))
        );
    }

    @DisplayName("로또 번호가 일 이상 사십오 이하의 숫자가 아닐 경우 예외가 발생한다")
    @ParameterizedTest
    @MethodSource
    void containsNumbersInRange(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> containsNumbersInRange() {
        return Stream.of(
                Arguments.of(List.of(-1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(0, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 2_100_000_000))
        );
    }

    @DisplayName("로또 번호 사이의 중복이 있을 시 예외가 발생한다.")
    @Test
    void verifyNoDuplicationInOneLotto() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호와 일치하는 경우 True를 반환한다")
    @ParameterizedTest
    @MethodSource
    void shouldReturnTrueWhenDuplicateNumbers(List<Integer> numbers) {
        List<Integer> targetNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.isSameWith(targetNumbers)).isTrue();
    }

    private static Stream<Arguments> shouldReturnTrueWhenDuplicateNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(6, 5, 4, 3, 2, 1)),
                Arguments.of(List.of(1, 3, 5, 2, 4, 6))
        );
    }

    @DisplayName("로또 번호와 중복되지 않는 번호를 가진 경우 False를 반환한다")
    @Test
    void shouldReturnFalseWhenNoDuplicateNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.isSameWith(List.of(1, 2, 3, 4, 5, 7))).isFalse();
    }

    @DisplayName("로또 번호와 다른 갯수의 숫자가 입력될 경우 False를 반환한다")
    @ParameterizedTest
    @MethodSource
    void shouldReturnFalseWhenCountDifferent(List<Integer> targetNumbers) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.isSameWith(targetNumbers)).isFalse();
        assertThat(lotto.isSameWith(targetNumbers)).isFalse();
    }

    private static Stream<Arguments> shouldReturnFalseWhenCountDifferent() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }
}
