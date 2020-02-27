package lotto;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {
    @Test
    @DisplayName("당첨번호 중 문자가 입력되었는지 확인")
    void checkNotNumberTest() {
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "d", "6"};
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 넘버는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호 중 1~45까지 범위를 벗어난 번호가 있는지 확인")
    void checkLottoNumberRangeTest() {
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "55", "6"};
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지 수여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 6개 입력되었는지 확인")
    void checkLottoSizeSix() {
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "6"};
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 문자가 입력되었는지 확인")
    void checkBonusBallNotNumber() {
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "가";
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputBonusNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("보너스 넘버는 숫자여야 합니다. 입력한 문자 : %s", "가"));
    }

    @Test
    @DisplayName("보너스 번호가 1~45까지 범위를 벗어났는지 확인")
    void checkBonusBallLottoNumberRangeTest(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "50";
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputBonusNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지 수여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되었는지 확인")
    void checkDuplicatedLottoNumberTest() {
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "1";
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
            winningNumber.inputBonusNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");

    }
}
