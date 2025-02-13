package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @ParameterizedTest
    @DisplayName("숫자가 6개가 아니면 예외가 발생한다.")
    @MethodSource("makeNotSixNumbers")
    void 숫자가_6개가_아니면_예외가_발생한다(List<Integer> numbers) {
        assertThatThrownBy(() ->
            new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("로또 숫자가 6개가 아닙니다.");
    }

    private static Stream<Arguments> makeNotSixNumbers() {
        return Stream.of(
                Arguments.of(List.of(1,2,3,4,5)),
                Arguments.of(List.of(1,2,3,4,5,6,7))
        );
    }

    @ParameterizedTest
    @DisplayName("숫자가 범위를 벗어나면 예외가 발생한다.")
    @MethodSource("makeWrongRangeNumbers")
    void 숫자가_범위를_벗어나면_예외가_발생한다(List<Integer> numbers) {
        assertThatThrownBy(() ->
                new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1 이상 45 이하만 가능합니다.");
    }

    private static Stream<Arguments> makeWrongRangeNumbers() {
        return Stream.of(
                Arguments.of(List.of(1,2,3,4,5,46)),
                Arguments.of(List.of(0,1,2,3,4,5)),
                Arguments.of(List.of(-1,1,2,3,4,5))
        );
    }

    @ParameterizedTest
    @DisplayName("중복된 숫자가 있으면 예외가 발생한다.")
    @MethodSource("makeDuplicateNumbers")
    void 중복된_숫자가_있으면_예외가_발생한다(List<Integer> numbers) {
        assertThatThrownBy(() ->
                new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 숫자가 존재합니다.");
    }

    private static Stream<Arguments> makeDuplicateNumbers() {
        return Stream.of(
                Arguments.of(List.of(1,2,3,4,5,5)),
                Arguments.of(List.of(1,2,3,5,5,5))
        );
    }
}