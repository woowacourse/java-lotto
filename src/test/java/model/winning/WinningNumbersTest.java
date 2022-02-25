package model.winning;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import model.winning.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    @DisplayName("당첨번호가 6개가 아닌 경우 오류를 발생합니다.")
    void checkNumber_NotValidSize() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertThatThrownBy(() -> new WinningNumbers(numbers, 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호가 저장되는지 확인한다.")
    void generateNumber_Test() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 7;

        WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);
    }
}