package lotto.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @DisplayName("중복 없는 6개의 숫자로 로또를 생성할 수 있다.")
    @Test
    void ok() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);

        assertDoesNotThrow(() -> new Lotto(numbers));
    }

    @DisplayName("로또 숫자가 6개가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    void shouldThrowException_WhenNumberCountIsNot6(int count) {
        Set<Integer> numbers = createNumbers(count);

        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    private Set<Integer> createNumbers(int count) {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    @DisplayName("로또 숫자의 범위가 올바르지 않은 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNumberNotInRange() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 46);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(numbers)
        );
    }

    @DisplayName("특정 번호를 가지고 있는지 확인할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2,3,4,5,6})
    void containsTest(int number) {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertTrue(lotto.contains(number));
    }

    @DisplayName("로또를 비교할 수 있다.")
    @Test
    void matchTest() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto1 = new Lotto(numbers);
        Lotto lotto2 = new Lotto(numbers);

        assertEquals(6, lotto1.getMatchCount(lotto2));
    }
}
