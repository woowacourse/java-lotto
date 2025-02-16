package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

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

    @ParameterizedTest
    @MethodSource("provideLottoAndWinningLottoAndExpectedPrizeTier")
    void 당첨로또와_비교해_등수를_매긴다(Lotto lotto, WinningLotto winningLotto, PrizeTier expectedPrizeTier) {
        lotto.rankTier(winningLotto);
        PrizeTier actualPrizeTier = lotto.getPrizeTier();

        assertEquals(expectedPrizeTier, actualPrizeTier);
    }

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

    public static Stream<Arguments> provideLottoAndWinningLottoAndExpectedPrizeTier() {
        return Stream.of(
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                winningLottoOf(1, 2, 3, 4, 5, 6, 45), PrizeTier.FIRST),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                winningLottoOf(1, 2, 3, 4, 5, 45, 6), PrizeTier.SECOND),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                winningLottoOf(1, 2, 3, 4, 5, 45, 44), PrizeTier.THIRD),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                winningLottoOf(1, 2, 3, 4, 43, 44, 6), PrizeTier.FOURTH),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                winningLottoOf(1, 2, 3, 42, 43, 44, 6), PrizeTier.FIFTH),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                winningLottoOf(1, 2, 41, 42, 43, 44, 6), PrizeTier.NONE)
        );
    }

    private static WinningLotto winningLottoOf(int... numbersEndWithBonus) {
        List<Integer> numbers = Arrays.stream(numbersEndWithBonus)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = numbers.removeLast();
        return new WinningLotto(new Lotto(numbers), bonusNumber);
    }
}
