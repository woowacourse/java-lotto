package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    static Stream<Arguments> provideInvalidLottoNumbersCount() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(21, 45, 11, 23, 32, 4, 1))
        );
    }

    static Stream<Arguments> provideInvalidDuplicatedLottoNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 5)),
                Arguments.of(List.of(11, 21, 45, 11, 23, 32))
        );
    }

    static Stream<Arguments> provideInvalidLottoNumbersRange() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(41, 42, 43, 44, 45, 46))
        );
    }

    @Test
    void 제대로된_번호가_오면_예외가_발생하지_않는다() {
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> new Lotto(validNumbers));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbersCount")
    void 번호가_6개가_아니면_예외가_발생한다(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDuplicatedLottoNumbers")
    void 번호가_중복되면_예외가_발생한다(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbersRange")
    void 범위를_벗어난_번호가_오면_예외가_발생한다(List<Integer> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }
}
