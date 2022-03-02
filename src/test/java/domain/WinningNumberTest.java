package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningNumberTest {

    @DisplayName("입력된 번호가 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} numbers={0}")
    @ValueSource(strings = {"12, 40, 43, 12,", "12, 4, 35, 2, 4, 5, 6"})
    void checkNumOfNumbers_throwIllegalException(final String numbers) {
        String bonus = "1";
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개의 번호를 입력해줘야 합니다.");
    }

    @DisplayName("로또 번호가 숫자가 아닌경우 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} numbers={0}")
    @ValueSource(strings = {"12, 안녕, 43, 12, 1, 3", "12, 4, 1, qwe, 4, 5"})
    void checkNonNumbers_throwIllegalException(final String numbers) {
        String bonus = "1";
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 숫자만 입력해줘야 합니다.");
    }

    @DisplayName("로또 번호가 중복된 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} numbers={0}")
    @ValueSource(strings = {"2, 2, 3, 4, 5, 6", "21, 2, 34, 34, 5, 6"})
    void checkDuplicateNumber_throwIllegalException(final String numbers) {
        String bonus = "1";
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복되지 않은 6자리 숫자여야 합니다.");
    }

    @DisplayName("보너스 번호가 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} bonus={0}")
    @ValueSource(strings = {"보너스", "넘버"})
    void checkBonusNonInteger_throwIllegalException(final String bonus) {
        String numbers = "1,2,3,4,5,6";
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 숫자만 입력해줘야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호를 이미 갖고 있을 시 예외가 발생한다.")
    void checkBonusDuplicate_throwIllegalException() {
        String numbers = "1,2,3,4,5,6";
        String bonus = "3";

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호가 당첨 번호와 중복됩니다.");
    }

}