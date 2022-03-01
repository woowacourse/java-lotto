package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningNumberTest {

    @Test
    @DisplayName("보너스 번호를 이미 갖고 있을 시 예외가 발생한다.")
    void checkBonusDuplicate_throwIllegalException() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers lotto = new WinningNumbers(numbers);
        int bonus = 3;

        assertThatThrownBy(() -> lotto.addBonusNumber(bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호가 당첨 번호와 중복됩니다.");
    }

    @DisplayName("로또 번호가 중복된 경우 예외가 발생한다.")
    @Test
    void checkDuplicateNumber_throwIllegalException() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 1, 3, 4, 5, 6));

        assertThatThrownBy(() -> new WinningNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복되지 않은 6자리 숫자여야 합니다.");
    }

}