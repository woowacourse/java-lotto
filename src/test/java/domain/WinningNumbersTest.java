package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class WinningNumbersTest {
    @DisplayName("유효한 값이면 객체 생성 성공")
    @Test
    void valueOf_validInput_exceptionThrown() {
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 7;

        assertThatCode(() -> new WinningNumbers(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 당첨 번호에 보너스 번호가 포함 되어 있는 경우 ")
    @Test
    void valueOf_includeBonusNumber_exceptionThrown() {
        final int bonusNumber = 6;
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, bonusNumber);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningNumbers(winningNumbers, bonusNumber))
                .withMessageContaining("당첨 번호에 보너스 번호가 포함되어 있습니다");
    }
}
