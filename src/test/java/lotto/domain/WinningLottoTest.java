package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {

    @Test
    void 올바른_당첨_로또_생성() {
        assertDoesNotThrow(() ->
                WinningLottoParser.parseWinningLotto("1,2,3,4,5,45", "7"));
    }

    @Test
    void 갯수가_유효하지_않은_당첨_로또_생성_검증() {
        assertThrows(InvalidLottoException.class, () ->
                WinningLottoParser.parseWinningLotto("1,2,3,4,5", "7"));
    }

    @Test
    void 중복된_수가_있는_당첨_로또_생성_검증() {
        assertThrows(InvalidLottoException.class, () ->
                WinningLottoParser.parseWinningLotto("1,2,3,4,5,5","7"));
    }

    @Test
    void 보너스볼이_중복된_당첨_로또_생성_검증() {
        assertThrows(InvalidBonusBallException.class, () ->
                WinningLottoParser.parseWinningLotto("1,2,3,4,5,45","6"));
    }

}
