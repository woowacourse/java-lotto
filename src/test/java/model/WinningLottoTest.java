package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(OrderAnnotation.class)
class WinningLottoTest {
    static Stream<Arguments> provideBasicLottoAndInvalidBonusNumber() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 0),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 46)
        );
    }

    static Stream<Arguments> provideBasicLottoAndBonusNumber() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBasicLottoAndInvalidBonusNumber")
    void 유효하지_않은_보너스번호가_들어오면_예외를_발생시킨다(Lotto basicLotto, int bonusNumber) {
        assertThrows(IllegalArgumentException.class, () -> new WinningLotto(basicLotto, bonusNumber));
    }

    @ParameterizedTest
    @MethodSource("provideBasicLottoAndBonusNumber")
    void 정상적인_보너스번호가_들어오면_예외를_발생시키지않는다(Lotto basicLotto, int bonusNumber) {
        assertDoesNotThrow(() -> new WinningLotto(basicLotto, bonusNumber));
    }
}