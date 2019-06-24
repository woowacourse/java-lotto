package lotto.domain.lotto;

import lotto.domain.WinningLotto;
import lotto.domain.exception.InvalidLottoException;
import lotto.domain.exception.InvalidLottoNumberException;
import lotto.domain.exception.InvalidWinnigLottoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    private static final String BONUS_BALL = "7";
    private String inputWinningLottoNumbers;

    @Test
    void 당첨번호_로또_객체에_잘들어가는지_테스트() {
        inputWinningLottoNumbers = "1,2,3,4,5,6";
        assertDoesNotThrow(() -> new WinningLotto(inputWinningLottoNumbers, BONUS_BALL));
    }

    @Test
    void 당첨번호가_6개_아닐때() {
        inputWinningLottoNumbers = "1,2,3,4,5";
        assertThrows(InvalidLottoException.class, () -> {
            new WinningLotto(inputWinningLottoNumbers, BONUS_BALL);
        });
    }

    @Test
    void 당첨번호가_중복일때() {
        inputWinningLottoNumbers = "1,2,3,4,5,5";
        assertThrows(InvalidLottoException.class, () -> {
            new WinningLotto(inputWinningLottoNumbers, BONUS_BALL);
        });
    }

    @Test
    void 당첨번호에_문자를_입력할때() {
        inputWinningLottoNumbers = "a,2,3,4,5,6";
        assertThrows(InvalidWinnigLottoException.class, () -> {
            new WinningLotto(inputWinningLottoNumbers, BONUS_BALL);
        });
    }

    @Test
    void 당첨번호에_실수를_입력할때() {
        inputWinningLottoNumbers = "1.1,2,3,4,5,6";
        assertThrows(InvalidWinnigLottoException.class, () -> {
            new WinningLotto(inputWinningLottoNumbers, BONUS_BALL);
        });
    }

    @Test
    void 당첨번호가_1에서_45의_정수가_아닐때() {
        inputWinningLottoNumbers = "0,1,2,3,4,5";
        assertThrows(InvalidLottoNumberException.class, () -> {
            new WinningLotto(inputWinningLottoNumbers, BONUS_BALL);
        });
    }
}
