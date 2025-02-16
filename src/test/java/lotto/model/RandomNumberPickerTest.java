package lotto.model;

import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_MAX;
import static lotto.rule.LottoConstants.Number.LOTTO_NUMBER_MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("랜덤 번호 생성기 테스트")
class RandomNumberPickerTest {

    private final RandomNumberPicker randomNumberPicker = new RandomNumberPicker();

    @DisplayName("정상적인 경우")
    @Nested
    class SuccessCase {

        @DisplayName("요청한 개수만큼 번호를 생성할 수 있다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 10})
        void countOfPickedNumbers(int count) {
            Set<Integer> numbers = randomNumberPicker
                    .pickNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, count);

            assertThat(numbers)
                    .hasSize(count);
        }

        @DisplayName("요청한 범위 내에서만 번호를 생성할 수 있다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 10})
        void numbersInRange(int count) {
            Set<Integer> numbers = randomNumberPicker
                    .pickNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, count);

            for (Integer number : numbers) {
                assertThat(number)
                        .isBetween(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX);
            }
        }
    }

    @DisplayName("예외가 발생하는 경우")
    @Nested
    class ExceptionCase {

        @DisplayName("범위가 잘못된 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenInvalidRange() {
            assertThatThrownBy(() -> randomNumberPicker.pickNumbersInRange(1, 0, 1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("시작값은 끝값보다 작아야 합니다.");
        }

        @DisplayName("뽑을 숫자의 개수가 0개인 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenCountIsZero() {
            assertThatThrownBy(() -> randomNumberPicker.pickNumbersInRange(1, 10, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("뽑을 숫자의 개수는 1개 이상이어야 합니다.");
        }

        @DisplayName("요청한 개수가 범위 내 숫자보다 많은 경우 예외가 발생한다.")
        @Test
        void shouldThrowException_WhenCountIsGreaterThanRange() {
            assertThatThrownBy(() -> randomNumberPicker.pickNumbersInRange(1, 10, 11))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("요청한 개수가 범위 내 숫자보다 많을 수 없습니다.");
        }
    }
}
