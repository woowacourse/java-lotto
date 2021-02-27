package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinningDrawingMachineTest {

    @Test
    @DisplayName("당첨 번호를 문자로 만들려고 하면 예외가 발생한다.")
    void insertString() {
        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing("a, b, c, 1, 2, 3")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 번호를 입력하면 예외가 발생한다.")
    void insertDuplication() {
        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing("1, 1, 1, 2, 2, 2")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("6개 이외의 당첨번호를 입력하면 예외가 발생한다.")
    void insertInvalidCount() {
        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing("1, 1, 1,")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백을 입력하면 예외가 발생한다.")
    void insertBlank() {
        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing(" ,")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing("")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing(" ")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new LottoWinningDrawingMachine().drawing(",")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호 생성이 성공하면 통과한다.")
    void drawingBonus() {
        LottoWinningDrawingMachine lottoWinningDrawingMachine = new LottoWinningDrawingMachine();
        lottoWinningDrawingMachine.drawing("1, 2, 33, 4, 5, 6");
        LottoNumber bonusNumber = lottoWinningDrawingMachine.bonusDrawing("3");
        assertThat(bonusNumber).isEqualTo(LottoNumber.of("3"));
    }

    @Test
    @DisplayName("보너스 번호 생성시 당첨 번호와 중복되면 예외가 발생한다.")
    void bonusNumberDuplicationCheck() {
        LottoWinningDrawingMachine lottoWinningDrawingMachine = new LottoWinningDrawingMachine();
        lottoWinningDrawingMachine.drawing("1, 2, 3, 4, 5, 6");
        assertThatThrownBy(() -> lottoWinningDrawingMachine.bonusDrawing("6"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}