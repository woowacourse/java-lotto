import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinningNumberWithBonusNumberTest {

    @Test
    void 당첨_번호와_보너스_번호는_중복되면_예외를_던진다() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto winningNumber = new Lotto(numbers);
        int bonusNumber = 1;
        assertThatThrownBy(() -> new WinningNumberWithBonusNumber(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
