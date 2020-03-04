package lotto;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {
    @Test
    @DisplayName("당첨 번호 중 1~45까지 범위를 벗어난 번호가 있는지 확인")
    void checkLottoNumberRangeTest() {
        assertThatThrownBy(() -> {
            int[] numbers = {1, 2, 3, 4, 55, 6};
            int bonusNumber = 10;
            new WinningNumber(numbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개 입력되었는지 확인")
    void checkLottoSizeSix() {
        assertThatThrownBy(() -> {
            int[] numbers = {1, 2, 3, 4, 6};
            int bonusNumber = 10;
            new WinningNumber(numbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 1~45까지 범위를 벗어났는지 확인")
    void checkBonusBallLottoNumberRangeTest() {
        assertThatThrownBy(() -> {
            int[] numbers = {1, 2, 3, 4, 5, 6};
            int bonusNumber = 50;
            new WinningNumber(numbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지 수여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되었는지 확인")
    void checkDuplicatedLottoNumberTest() {
        assertThatThrownBy(() -> {
            int[] numbers = {1, 2, 3, 4, 5, 6};
            int bonusNumber = 1;
            new WinningNumber(numbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");

    }
}
