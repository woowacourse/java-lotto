package lotto;

import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 제대로된_숫자들을_입력받은_경우() {
        assertDoesNotThrow(() -> new WinningLotto("1, 2, 3, 4, 5, 6"));
    }

    @Test
    void 빈_문자열을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto(""));
    }

    @Test
    void 공백을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto(" "));
    }

    @Test
    void 숫자가_아닌_값을_입력받은_경우_예외_발생() {
        assertThrows(InvalidLottoNumbersException.class, () -> new WinningLotto("1, 2, 3, 4, 5, a"));
    }
}
