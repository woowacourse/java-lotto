package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningNumberTest {

    private static final String LOTTO_NUMBER_DELIMITER = ", |,";

    @DisplayName("입력된 번호가 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} numbers={0}")
    @ValueSource(strings = {"12, 40, 43, 12,", "12, 4, 35, 2, 7, 5, 6"})
    void checkNumOfNumbers_throwIllegalException(final String inputNumbers) {
        int[] numbers = Arrays.stream(inputNumbers.split(LOTTO_NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .toArray();
        int bonus = 1;
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복되지 않은 6자리 숫자여야 합니다.");
    }

    @DisplayName("로또 번호가 중복된 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} numbers={0}")
    @ValueSource(strings = {"2, 2, 3, 4, 5, 6", "21, 2, 34, 34, 5, 6"})
    void checkDuplicateNumber_throwIllegalException(final String inputNumbers) {
        int[] numbers = Arrays.stream(inputNumbers.split(LOTTO_NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .toArray();
        int bonus = 1;
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복되지 않은 6자리 숫자여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호를 이미 갖고 있을 시 예외가 발생한다.")
    void checkBonusDuplicate_throwIllegalException() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int bonus = 3;
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호가 당첨 번호와 중복됩니다.");
    }
}
