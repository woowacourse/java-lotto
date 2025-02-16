package lotto.model;

import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_COUNT;
import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 테스트")
class LottoTest {

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("중복 없는 6개의 숫자로 로또를 생성할 수 있다.")
        @Test
        void createTest() {
            Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);

            assertThatCode(() -> new Lotto(numbers))
                    .doesNotThrowAnyException();
        }

        @DisplayName("로또가 특정 번호를 가지고 있는지 확인할 수 있다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6})
        void containsTest(int checkNumber) {
            Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
            Lotto lotto = LottoFixtures.createByNumbers(numbers);

            assertThat(lotto.contains(checkNumber))
                    .isTrue();
        }

        @DisplayName("로또를 서로 비교하여 일치하는 숫자의 개수를 확인할 수 있다.")
        @Test
        void countMatchingNumbersTest() {
            Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
            Lotto myLotto = LottoFixtures.createByNumbers(numbers);
            Lotto winningLotto = LottoFixtures.createByNumbers(numbers);

            assertThat(myLotto.countMatchingNumbers(winningLotto))
                    .isEqualTo(6);
        }

        @DisplayName("로또 번호는 정렬되어 생성된다.")
        @Test
        void lottoNumbersAreSorted() {
            Set<Integer> numbers = Set.of(6, 5, 4, 3, 2, 1);
            Lotto lotto = new Lotto(numbers);

            assertThat(lotto.getNumbers())
                    .containsExactly(1, 2, 3, 4, 5, 6);
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("로또 숫자가 6개가 아닌 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {5, 7})
        void shouldThrowException_WhenNumberCountIsNot6(int countOfNumbers) {
            Set<Integer> numbers = createUniqueNumbersByCount(countOfNumbers);

            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 %d개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }

        @DisplayName("로또 숫자 범위를 벗어나는 숫자가 있는 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {0, 46})
        void shouldThrowException_WhenNumberNotInRange(int number) {
            Set<Integer> numbers = Set.of(number, 1, 2, 3, 4, 5);

            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 %d부터 %d 사이의 수여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }

        @DisplayName("로또 숫자가 null인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenNumbersIsNull() {
            assertThatThrownBy(() -> new Lotto(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("로또 번호는 null이 될 수 없습니다.");
        }
    }

    private Set<Integer> createUniqueNumbersByCount(int count) {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
