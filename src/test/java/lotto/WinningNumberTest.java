package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {
    @Test
    void 입력된_번호가_문자인지_확인(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "d", "6"};
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 넘버는 숫자여야 합니다."));
    }

    @Test
    void 당첨_로또_번호가_로또의_범위_안_인지_확인(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "55", "6"};
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지 수여야 합니다.");
    }

    @Test
    void 당첨_로또_번호의_개수가_6개인지_확인(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "6"};
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumbers(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    void 보너스_번호_유효성검증(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "가";
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputBonusNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("보너스 넘버는 숫자여야 합니다. 입력한 문자 : %s", "가"));

        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "50";
            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputBonusNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45까지 수여야 합니다.");
    }

    @Test
    void 당첨_번호와_보너스_번호가_중복되는_경우_예외_처리() {
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
