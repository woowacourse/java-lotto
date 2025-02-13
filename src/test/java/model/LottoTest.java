package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    static Stream<Arguments> provideValidLottoNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(21, 45, 11, 23, 32, 4)),
                Arguments.of(List.of(1, 22, 3, 44, 25, 36)),
                Arguments.of(List.of(12, 21, 33, 44, 25, 16))
        );
    }

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

    @ParameterizedTest
    @MethodSource("provideValidLottoNumbers")
    void 제대로된_번호가_오면_예외가_발생하지_않는다(List<Integer> numbers) {
        assertDoesNotThrow(() -> new Lotto(numbers));
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