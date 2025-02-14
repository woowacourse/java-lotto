package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {
    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외를 던진다")
    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외를_던진다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5));
        assertThatThrownBy(() -> winningNumbers.validateBonusNumberDuplicated(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
    }
}
