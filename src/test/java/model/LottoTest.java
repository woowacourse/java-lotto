package model;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또는 중복되지 않은 1이상 45이하의 6개 정수이다")
    @Test
    void lottoGenerateTest() {
        Set<Integer> numbers = new HashSet<>(Set.of(1, 2, 3, 4, 5, 45));
        Assertions.assertDoesNotThrow(() -> new Lotto(numbers));

    }

    @DisplayName("로또의 크기가 6이 아니면 에러가 발생한다")
    @Test
    void lottoSizeExceptionTest() {
        assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Lotto(Set.of(1, 2, 3, 4, 5))),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Lotto(Set.of(1, 2, 3, 4, 5, 6, 7)))
        );
    }

    @DisplayName("로또가 1이상 45이하가 아니면 에러가 발생한다")
    @Test
    void lottoBoundaryExceptionTest() {
        assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Lotto(Set.of(1, 2, 3, 4, 5, 46))),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Lotto(Set.of(0, 2, 3, 4, 5, 6)))
        );
    }
}