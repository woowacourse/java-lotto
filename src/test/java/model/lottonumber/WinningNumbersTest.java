package model.lottonumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import model.lottonumber.vo.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호와 보너스볼 번호가 생성되는지 확인한다.")
    void generateWinningNumbers() {
        final List<Integer> numbers = Arrays.asList(1, 2, 4, 37, 41, 23);
        final int bonusNumber = 7;

        final WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);
        for (Number winningNumber : winningNumbers.getWinningNumbers()) {
            assertThat(numbers).contains(winningNumber.getNumber());
        }
        assertThat(winningNumbers.getBonusNumber().getNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("당첨 번호가 6개보다 적게 입력되면 오류를 발생한다.")
    void generateWinningNumbers_UnderThanSix() {
        final List<Integer> numbers = Arrays.asList(1, 2, 4, 6);
        final int bonusNumber = 7;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 당첨 번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개보다 많이 입력되면 오류를 발생한다.")
    void generateWinningNumbers_OverThanSix() {
        final List<Integer> numbers = Arrays.asList(1, 2, 4, 6, 7, 9, 16);
        final int bonusNumber = 21;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 당첨 번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("당첨 번호중 중복되는 번호가 있으면 오류를 발생한다.")
    void generateWinningNumbers_DuplicateInWinnigNumbers() {
        final List<Integer> numbers = Arrays.asList(1, 16, 4, 6, 9, 16);
        final int bonusNumber = 19;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 당첨번호들 중 중복되는 번호가 있습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스볼 번호가 겹치면 오류를 발생한다.")
    void generateWinningNumbers_DuplicateWithWinningNumbers() {
        final List<Integer> numbers = Arrays.asList(1, 2, 4, 6, 9, 16);
        final int bonusNumber = 16;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 보너스볼 번호가 당첨 번호와 중복됩니다.");
    }
}