package model.lottonumber;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    @DisplayName("당첨 번호가 6개보다 적게 입력되면 오류를 발생한다.")
    void generateWinningNumbers_underThanSix() {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 6);
        int bonusNumber = 7;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 당첨 번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개보다 많이 입력되면 오류를 발생한다.")
    void generateWinningNumbers_overThanSix() {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 6, 7, 9, 16);
        int bonusNumber = 21;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 당첨 번호가 6개가 아닙니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스볼 번호가 겹치면 오류를 발생한다.")
    void generateWinningNumbers_Duplicate() {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 6, 9, 16);
        int bonusNumber = 16;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 보너스볼 숫자가 당첨 숫자와 중복됩니다.");
    }
}