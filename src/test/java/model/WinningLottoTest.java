package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// 싱글톤 패턴 적용으로 인해 메서드 테스트 순서 고정 ㅠㅠ
@TestMethodOrder(OrderAnnotation.class)
class WinningLottoTest {
    static Stream<Arguments> provideBasicLottoAndInvalidBonusNumber() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 1),
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

    @Order(1)
    @ParameterizedTest
    @MethodSource("provideBasicLottoAndInvalidBonusNumber")
    void 유효하지_않은_보너스번호가_들어오면_예외를_발생시킨다(Lotto basicLotto, int bonusNumber) {
        assertThrows(IllegalArgumentException.class, () -> WinningLotto.initialize(basicLotto, bonusNumber));
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("provideBasicLottoAndBonusNumber")
    void 정상적인_보너스번호가_들어오면_예외를_발생시키지않는다(Lotto basicLotto, int bonusNumber) {
        assertDoesNotThrow(() -> WinningLotto.initialize(basicLotto, bonusNumber));
    }
}