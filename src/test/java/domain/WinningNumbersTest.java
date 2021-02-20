package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @DisplayName("유효한 값이면 객체 생성 성공")
    @Test
    void valueOf_validInput_exceptionThrown() {
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 7;

        assertThatCode(() -> WinningNumbers.valueOf(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 당첨 번호에 보너스 번호가 포함 되어 있는 경우 ")
    @Test
    void valueOf_includeBonusNumber_exceptionThrown() {
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 6;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.valueOf(winningNumbers, bonusNumber))
                .withMessageContaining("포함");
    }
}
