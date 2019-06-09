package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {

    @Test
    void 올바른_당첨_로또_생성() {
        assertDoesNotThrow(() ->
                new WinningLotto(Arrays.asList(1,2,3,4,5,45),new LottoNumber(7)));
    }

    @Test
    void 갯수가_유효하지_않은_당첨_로또_생성_검증() {
        assertThrows(InvalidLottoException.class,
                () -> new WinningLotto(Arrays.asList(1,2,3,4,5),new LottoNumber(7)));
    }

    @Test
    void 중복된_수가_있는_당첨_로또_생성_검증() {
        assertThrows(InvalidLottoException.class, () ->
                new WinningLotto(Arrays.asList(1,2,3,4,5,5),new LottoNumber(5)));
    }

    @Test
    void 보너스볼이_중복된_당첨_로또_생성_검증() {
        assertThrows(InvalidBonusBallException.class, () ->
                new WinningLotto(Arrays.asList(1,2,3,4,5,6),new LottoNumber(6)));
    }

}
