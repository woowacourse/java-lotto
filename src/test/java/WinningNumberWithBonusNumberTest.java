import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberWithBonusNumberTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호는 중복되면 예외를 던진다")
    void 당첨_번호와_보너스_번호는_중복되면_예외를_던진다() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(numbers);
        int bonusNumber = 1;
        assertThatThrownBy(() -> new WinningNumberWithBonusNumber(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

}